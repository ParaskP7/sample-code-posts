package io.petros.posts.activity.posts.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.petros.posts.GeneralTestHelper;
import io.petros.posts.activity.posts.model.PostsModel;
import io.petros.posts.activity.posts.view.PostsView;
import io.petros.posts.service.detector.InternetAvailabilityDetector;
import io.petros.posts.service.retrofit.RetrofitService;
import io.reactivex.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PostsPresenterImplTest extends GeneralTestHelper {

    private static final boolean PULL_TO_REFRESH = true;

    private PostsPresenterImpl postsPresenter;
    @Mock private PostsModel postsModelMock;
    @Mock private RetrofitService retrofitServiceMock;
    @Mock private InternetAvailabilityDetector internetAvailabilityDetectorMock;
    @Mock private PostsView postsViewMock;

    @Before
    public void setUp() {
        setUpMocks();
        postsPresenter = new PostsPresenterImpl(postsModelMock, retrofitServiceMock, rxSchedulers,
                internetAvailabilityDetectorMock);
    }

    @Test
    public void attachViewTest() {
        postsPresenter.attachView(postsViewMock);

        assertThat(postsPresenter.getView()).isEqualTo(postsViewMock);
    }

    @Test(expected = NullPointerException.class)
    public void givenTheViewIsNotAttached_WhenGetViewIsCalled_ThenAnExceptionIsThrown() {
        postsPresenter.getView();
    }

    @Test
    public void getViewTest() {
        postsPresenter.attachView(postsViewMock);

        assertThat(postsPresenter.getView()).isEqualTo(postsViewMock);
    }

    @Test
    public void loadPostUserAndPostTestForUsersError() throws InterruptedException {
        postsPresenter.attachView(postsViewMock);
        when(internetAvailabilityDetectorMock.isAvailable()).thenReturn(true);
        when(retrofitServiceMock.users()).thenReturn(Observable.error(observableErrorThrowable));

        postsPresenter.loadPosts(PULL_TO_REFRESH);
        Thread.sleep(100);

        verify(postsViewMock, times(1)).showLoading(PULL_TO_REFRESH);
        verify(internetAvailabilityDetectorMock, times(1)).isAvailable();
        verifyNoMoreInteractions(internetAvailabilityDetectorMock);
        verify(retrofitServiceMock, times(1)).users();
        verifyNoMoreInteractions(retrofitServiceMock);
        verify(postsViewMock, times(1)).showError(observableErrorThrowable, false);
        verifyNoMoreInteractions(postsViewMock);
    }

    @Test
    public void loadPostUserAndPostTestForPostsError() throws InterruptedException {
        postsPresenter.attachView(postsViewMock);
        when(internetAvailabilityDetectorMock.isAvailable()).thenReturn(true);
        when(retrofitServiceMock.users()).thenReturn(Observable.just(users));
        when(postsModelMock.saveUsers(users)).thenReturn(true);
        when(retrofitServiceMock.posts()).thenReturn(Observable.error(observableErrorThrowable));

        postsPresenter.loadPosts(PULL_TO_REFRESH);
        Thread.sleep(100);

        verify(postsViewMock, times(1)).showLoading(PULL_TO_REFRESH);
        verify(internetAvailabilityDetectorMock, times(1)).isAvailable();
        verifyNoMoreInteractions(internetAvailabilityDetectorMock);
        verify(retrofitServiceMock, times(1)).users();
        verify(postsModelMock, times(1)).saveUsers(users);
        verifyNoMoreInteractions(postsModelMock);
        verify(retrofitServiceMock, times(1)).posts();
        verifyNoMoreInteractions(retrofitServiceMock);
        verify(postsViewMock, times(1)).showError(observableErrorThrowable, false);
        verifyNoMoreInteractions(postsViewMock);
    }

    @Test
    public void loadPostUserAndPostTestForResponse() throws InterruptedException {
        postsPresenter.attachView(postsViewMock);
        when(internetAvailabilityDetectorMock.isAvailable()).thenReturn(true);
        when(retrofitServiceMock.users()).thenReturn(Observable.just(users));
        when(postsModelMock.saveUsers(users)).thenReturn(true);
        when(retrofitServiceMock.posts()).thenReturn(Observable.just(posts));

        postsPresenter.loadPosts(PULL_TO_REFRESH);
        Thread.sleep(100);

        verify(postsViewMock, times(1)).showLoading(PULL_TO_REFRESH);
        verify(internetAvailabilityDetectorMock, times(1)).isAvailable();
        verifyNoMoreInteractions(internetAvailabilityDetectorMock);
        verify(retrofitServiceMock, times(1)).users();
        verify(postsModelMock, times(1)).saveUsers(users);
        verifyNoMoreInteractions(postsModelMock);
        verify(retrofitServiceMock, times(1)).posts();
        verifyNoMoreInteractions(retrofitServiceMock);
        verify(postsViewMock, times(1)).setData(posts);
        verify(postsViewMock, times(1)).showContent();
        verifyNoMoreInteractions(postsViewMock);
    }

}
