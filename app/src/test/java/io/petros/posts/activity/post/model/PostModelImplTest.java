package io.petros.posts.activity.post.model;

import org.junit.Before;
import org.junit.Test;

import io.petros.posts.GeneralTestHelper;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PostModelImplTest extends GeneralTestHelper {

    private PostModelImpl postModel;

    @Before
    public void setUp() {
        setUpMocks();
        postModel = new PostModelImpl(datastoreMock);
    }

    @Test
    public void getUserTest() {
        postModel.getUser(user.getId());

        verify(datastoreGetActionsMock, times(1)).user(user.getId());
        verifyNoMoreInteractions(datastoreGetActionsMock);
    }

}
