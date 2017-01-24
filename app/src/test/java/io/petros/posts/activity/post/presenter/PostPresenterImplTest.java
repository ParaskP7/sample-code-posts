package io.petros.posts.activity.post.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import io.petros.posts.PreconfiguredRobolectricTestRunner;
import io.petros.posts.RobolectricGeneralTestHelper;
import io.petros.posts.activity.post.model.PostModel;
import io.petros.posts.activity.post.view.PostView;
import io.petros.posts.service.detector.InternetAvailabilityDetector;
import io.petros.posts.service.retrofit.RetrofitService;
import io.reactivex.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(PreconfiguredRobolectricTestRunner.class)
public class PostPresenterImplTest extends RobolectricGeneralTestHelper {

    private PostPresenterImpl postPresenter;
    @Mock private PostModel postModelMock;
    @Mock private RetrofitService retrofitServiceMock;
    @Mock private InternetAvailabilityDetector internetAvailabilityDetectorMock;
    @Mock private PostView postViewMock;

    @Before
    public void setUp() {
        setUpMocks();
        postPresenter = new PostPresenterImpl(postModelMock, retrofitServiceMock, rxSchedulers,
                internetAvailabilityDetectorMock);
    }

    @Test
    public void attachViewTest() {
        postPresenter.attachView(postViewMock);

        assertThat(postPresenter.getView()).isEqualTo(postViewMock);
    }

    @Test
    public void isViewAttachedTest() {
        assertThat(postPresenter.isViewAttached()).isEqualTo(false);

        postPresenter.attachView(postViewMock);

        assertThat(postPresenter.isViewAttached()).isEqualTo(true);
    }

    @Test(expected = NullPointerException.class)
    public void givenTheViewIsNotAttached_WhenGetViewIsCalled_ThenAnExceptionIsThrown() {
        postPresenter.getView();
    }

    @Test
    public void getViewTest() {
        postPresenter.attachView(postViewMock);

        final PostView postView = postPresenter.getView();

        assertThat(postView).isEqualTo(postViewMock);
    }

    @Test
    public void loadPostUserAndPostTestForError() throws InterruptedException {
        postPresenter.attachView(postViewMock);
        when(postModelMock.getUser(user.getId())).thenReturn(user);
        when(internetAvailabilityDetectorMock.isAvailable()).thenReturn(true);
        when(retrofitServiceMock.comments(post.getId())).thenReturn(Observable.error(observableErrorThrowable));

        postPresenter.loadPostUserAndPost(getExtras(post));
        Thread.sleep(100);

        verify(postModelMock, times(1)).getUser(user.getId());
        verifyNoMoreInteractions(postModelMock);
        verify(postViewMock, times(1)).loadPostUser(user.getEmail(), user.getName(), user.getUsername());
        verify(internetAvailabilityDetectorMock, times(1)).isAvailable();
        verifyNoMoreInteractions(internetAvailabilityDetectorMock);
        verify(retrofitServiceMock, times(1)).comments(post.getId());
        verifyNoMoreInteractions(retrofitServiceMock);
        verify(postViewMock, times(1)).showError();
        verifyNoMoreInteractions(postViewMock);
    }

    @Test
    public void loadPostUserAndPostTestForResponse() throws InterruptedException {
        postPresenter.attachView(postViewMock);
        when(postModelMock.getUser(user.getId())).thenReturn(user);
        when(internetAvailabilityDetectorMock.isAvailable()).thenReturn(true);
        when(retrofitServiceMock.comments(post.getId())).thenReturn(Observable.just(comments));

        postPresenter.loadPostUserAndPost(getExtras(post));
        Thread.sleep(100);

        verify(postModelMock, times(1)).getUser(user.getId());
        verifyNoMoreInteractions(postModelMock);
        verify(postViewMock, times(1)).loadPostUser(user.getEmail(), user.getName(), user.getUsername());
        verify(internetAvailabilityDetectorMock, times(1)).isAvailable();
        verifyNoMoreInteractions(internetAvailabilityDetectorMock);
        verify(retrofitServiceMock, times(1)).comments(post.getId());
        verifyNoMoreInteractions(retrofitServiceMock);
        verify(postViewMock, times(1)).loadPost(post.getTitle(), post.getBody(), String.valueOf(comments.size()));
        verifyNoMoreInteractions(postViewMock);
    }


}
