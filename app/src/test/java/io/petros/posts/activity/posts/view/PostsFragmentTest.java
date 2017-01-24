package io.petros.posts.activity.posts.view;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;
import io.petros.posts.activity.posts.presenter.PostsPresenter;
import io.petros.posts.activity.posts.view.recycler.PostRecyclerViewAdapter;

import static io.petros.posts.util.WhiteboxTestUtilities.POST_RECYCLER_VIEW_ADAPTER;
import static io.petros.posts.util.WhiteboxTestUtilities.PROGRESS_DIALOG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostsFragmentTest extends RobolectricGeneralTestHelper {

    private static final boolean PULL_TO_REFRESH = true;

    private PostsFragment postsFragment;
    @Mock private PostsPresenter postsPresenterMock;
    @Mock private RecyclerView postsRecyclerViewMock;
    @Mock private PostRecyclerViewAdapter postRecyclerViewAdapterMock;
    @Mock private ProgressDialog progressDialogMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpFragment();
    }

    private void setUpFragment() {
        postsFragment = new PostsFragment();
        SupportFragmentTestUtil.startFragment(postsFragment);
        postsFragment.setPresenter(postsPresenterMock);
        postsFragment.postsRecyclerView = postsRecyclerViewMock;
        Whitebox.setInternalState(postsFragment, POST_RECYCLER_VIEW_ADAPTER, postRecyclerViewAdapterMock);
        Whitebox.setInternalState(postsFragment, PROGRESS_DIALOG, progressDialogMock);
    }

    @Test
    public void fragmentNotNullTest() {
        assertThat(postsFragment).isNotNull();
    }

    @Test
    public void fragmentInjectsNotNullTest() {
        assertThat(postsFragment.postsPresenter).isNotNull();
    }

    @Test
    public void fragmentStringsNotNullTest() {
        assertThat(postsFragment.loadingMessage).isNotNull();
    }

    @Test
    public void fragmentViewsNotNullTest() {
        assertThat(postsFragment.postsRecyclerView).isNotNull();
    }

    @Test
    public void loadDataTest() {
        postsFragment.loadData(PULL_TO_REFRESH);

        verify(postsPresenterMock, times(1)).loadPosts(PULL_TO_REFRESH);
        verifyNoMoreInteractions(postsPresenterMock);
    }

    // VIEW // **************************************************************************************************************

    @Test
    public void notifyUserAboutInternetUnavailabilityTest() {
        postsFragment.showLoading(PULL_TO_REFRESH);

        final ProgressDialog progressDialog = (ProgressDialog) Whitebox.getInternalState(postsFragment, PROGRESS_DIALOG);
        assertThat(progressDialog.isShowing()).isTrue();
    }

    @Test
    public void setDataTest() {
        postsFragment.setData(posts);

        verify(postRecyclerViewAdapterMock, times(1)).reloadAdapter(posts);
        verifyNoMoreInteractions(postRecyclerViewAdapterMock);
    }

    @Test
    public void showContentTest() {
        postsFragment.showContent();

        verify(progressDialogMock, times(1)).dismiss();
        verifyNoMoreInteractions(progressDialogMock);
    }

    @Test
    public void showErrorTest() {
        postsFragment.showError(new Throwable(), false);

        verify(progressDialogMock, times(1)).dismiss();
        verifyNoMoreInteractions(progressDialogMock);
    }

    @Test
    public void getErrorMessageTest() {
        final String errorMessage = postsFragment.getErrorMessage(new Throwable(), false);

        assertThat(errorMessage).isEqualTo("Error while loading posts!");
    }

    // SWIPE REFRESH // *****************************************************************************************************

    @Test
    public void onRefreshTest() {
        postsFragment.onRefresh();

        verify(postsPresenterMock, times(1)).loadPosts(PULL_TO_REFRESH);
        verifyNoMoreInteractions(postsPresenterMock);
    }

}
