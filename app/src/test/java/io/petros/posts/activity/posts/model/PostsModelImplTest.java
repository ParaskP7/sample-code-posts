package io.petros.posts.activity.posts.model;

import org.junit.Before;
import org.junit.Test;

import io.petros.posts.GeneralTestHelper;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PostsModelImplTest extends GeneralTestHelper {

    private PostsModelImpl postsModel;

    @Before
    public void setUp() {
        setUpMocks();
        postsModel = new PostsModelImpl(datastoreMock);
    }

    @Test
    public void getUserTest() {
        postsModel.saveUsers(users);

        verify(datastoreSaveActionsMock, times(1)).users(users);
        verifyNoMoreInteractions(datastoreSaveActionsMock);
    }

}
