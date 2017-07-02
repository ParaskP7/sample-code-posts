package io.petros.posts.activity.posts.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.petros.posts.R;
import io.petros.posts.activity.posts.presenter.PostsPresenter;
import io.petros.posts.activity.posts.view.recycler.OnPostViewClickListener;
import io.petros.posts.activity.posts.view.recycler.PostRecyclerViewAdapter;
import io.petros.posts.app.PostsApplication;
import io.petros.posts.model.Post;

public class PostsFragment extends MvpLceFragment<SwipeRefreshLayout, List<Post>, PostsView, PostsPresenter>
        implements PostsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject PostsPresenter postsPresenter;

    @BindString(R.string.dialog__loading__message) String loadingMessage;

    @BindView(R.id.posts_fragment__posts_recycler_view) RecyclerView postsRecyclerView;
    @VisibleForTesting PostRecyclerViewAdapter postRecyclerViewAdapter;

    @VisibleForTesting ProgressDialog progressDialog;

    // LIFECYCLE // *********************************************************************************************************

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_posts, container, false);
        PostsApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        setSwipeRefreshView();
        setRecyclerView();
        loadData(false);
    }

    private void setSwipeRefreshView() {
        contentView.setOnRefreshListener(this);
    }

    private void setRecyclerView() {
        final OnPostViewClickListener onPostClickListenerImpl = new OnPostViewClickListener(getActivity());
        postRecyclerViewAdapter = new PostRecyclerViewAdapter(onPostClickListenerImpl);
        postsRecyclerView.setAdapter(postRecyclerViewAdapter);
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        presenter.loadPosts(pullToRefresh);
    }

    @Override
    public PostsPresenter createPresenter() {
        return postsPresenter;
    }

    @Override
    public void onDestroyView() {
        postsPresenter.detachView(false);
        super.onDestroyView();
    }

    // VIEW // **************************************************************************************************************

    @Override
    public void notifyUserAboutUsersUnavailability() {
        PostsApplication.snackbar(getActivity()).warn(R.string.snackbar__text__cannot_save_users);
    }

    @Override
    public void notifyUserAboutInternetUnavailability() {
        contentView.setRefreshing(false);
        PostsApplication.snackbar(getActivity()).warn(R.string.snackbar__text__internet_not_available);
    }

    @Override
    public void showLoading(final boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(R.string.dialog__loading__title);
        progressDialog.setMessage(loadingMessage);
        progressDialog.show();
    }

    @Override
    public void setData(final List<Post> posts) {
        postRecyclerViewAdapter.reloadAdapter(posts);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        progressDialog.dismiss();
    }

    @Override
    public void showError(final Throwable throwable, final boolean pullToRefresh) {
        super.showError(throwable, pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
        progressDialog.dismiss();
        PostsApplication.snackbar(getActivity()).warn(R.string.snackbar__text__error_getting_posts);
    }

    @Override
    protected String getErrorMessage(final Throwable throwable, final boolean pullToRefresh) {
        return "Error while loading posts!";
    }

    // SWIPE REFRESH // *****************************************************************************************************

    @Override
    public void onRefresh() {
        loadData(true);
    }

}
