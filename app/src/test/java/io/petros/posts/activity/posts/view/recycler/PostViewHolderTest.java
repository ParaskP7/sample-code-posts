package io.petros.posts.activity.posts.view.recycler;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.R;
import io.petros.posts.RobolectricGeneralTestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostViewHolderTest extends RobolectricGeneralTestHelper {

    private PostViewHolder postViewHolder;
    @Mock private OnViewClickListener onViewClickListenerMock;

    @Before
    public void setUp() {
        setUpMocks();
        setUpHolder();
    }

    private void setUpHolder() {
        final View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_view_post, null);
        postViewHolder = new PostViewHolder(itemView);
        postViewHolder.datastore = datastoreMock;
    }

    @Test
    public void bindTest() {
        when(datastoreGetActionsMock.user(post.getUserId())).thenReturn(user);

        postViewHolder.bind(post, onViewClickListenerMock);

        verify(datastoreGetActionsMock, times(1)).user(post.getUserId());
        verifyNoMoreInteractions(datastoreGetActionsMock);
        assertThat(postViewHolder.itemView.hasOnClickListeners()).isTrue();
        assertThat(postViewHolder.circleImageView.getDrawable()).isInstanceOf(BitmapDrawable.class);
        assertThat(postViewHolder.titleTextView.getText()).isEqualTo(post.getTitle());
    }

}
