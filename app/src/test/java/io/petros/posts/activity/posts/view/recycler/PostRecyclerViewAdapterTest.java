package io.petros.posts.activity.posts.view.recycler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.List;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;
import io.petros.posts.model.Post;

import static io.petros.posts.util.WhiteboxTestUtilities.ALL_POSTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostRecyclerViewAdapterTest extends RobolectricGeneralTestHelper {

    private static final int POSITION = 0;

    private PostRecyclerViewAdapter postRecyclerViewAdapter;
    @Mock private OnViewClickListener onViewClickListenerMock;
    @Mock private PostViewHolder holderMock;
    @Mock private List<Post> postsMock;

    @Before
    public void setUp() {
        setUpMocks();
        postRecyclerViewAdapter = new PostRecyclerViewAdapter(onViewClickListenerMock);
        Whitebox.setInternalState(postRecyclerViewAdapter, ALL_POSTS, posts);
    }

    @Test
    public void onBindViewHolderTest() {
        postRecyclerViewAdapter.onBindViewHolder(holderMock, POSITION);

        verify(holderMock, times(1)).bind(posts.get(POSITION), onViewClickListenerMock);
        verifyNoMoreInteractions(holderMock);
    }

    @Test
    public void getItemCountTest() {
        final int itemCount = postRecyclerViewAdapter.getItemCount();

        assertThat(itemCount).isEqualTo(posts.size());
    }

    @Test
    public void reloadAdapterTest() {
        Whitebox.setInternalState(postRecyclerViewAdapter, ALL_POSTS, postsMock);

        postRecyclerViewAdapter.reloadAdapter(posts);

        verify(postsMock, times(1)).clear();
        verify(postsMock, times(1)).addAll(posts);
        verifyNoMoreInteractions(postsMock);
    }

    @Test
    public void getItemTest() {
        final Post post = postRecyclerViewAdapter.getItem(POSITION);

        assertThat(post).isEqualTo(posts.get(POSITION));
    }

}
