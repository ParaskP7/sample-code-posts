package io.petros.posts.activity.posts.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.petros.posts.R;
import io.petros.posts.model.Post;
import timber.log.Timber;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostViewHolder> implements RecyclerViewAdapter {

    private final OnViewClickListener onViewClickListener;

    private final List<Post> allPosts;

    public PostRecyclerViewAdapter(final OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
        allPosts = new ArrayList<>();
    }

    @Override
    public PostViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_post, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {
        holder.bind(getItem(position), onViewClickListener);
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    @Override
    public void reloadAdapter(final List<Post> posts) {
        Timber.d("Reloading post recycler view adapter.");
        allPosts.clear();
        allPosts.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public Post getItem(int position) {
        return allPosts.get(position);
    }

}
