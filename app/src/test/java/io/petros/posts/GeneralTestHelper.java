package io.petros.posts;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.petros.posts.datastore.Datastore;
import io.petros.posts.datastore.DatastoreAddActions;
import io.petros.posts.datastore.DatastoreGetActions;
import io.petros.posts.datastore.DatastoreSaveActions;
import io.petros.posts.datastore.DatastoreUpdateActions;
import io.petros.posts.model.Comment;
import io.petros.posts.model.Post;
import io.petros.posts.model.User;
import io.petros.posts.util.rx.RxSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public class GeneralTestHelper {

    // RxJava specific fields.
    protected RxSchedulers rxSchedulers = getTestRxSchedulers();
    protected Throwable observableErrorThrowable = new RuntimeException("Test emulated error!");

    // Datastore specific fields.
    @Mock protected Datastore datastoreMock;
    @Mock protected DatastoreSaveActions datastoreSaveActionsMock;
    @Mock protected DatastoreAddActions datastoreAddActionsMock;
    @Mock protected DatastoreGetActions datastoreGetActionsMock;
    @Mock protected DatastoreUpdateActions datastoreUpdateActionsMock;

    // Model specific fields.
    protected User user = getTestUser();
    protected List<User> users = getTestUsers();
    protected Post post = getTestPost();
    protected List<Post> posts = getTestPosts();
    protected Comment comment = getTestComment();
    protected List<Comment> comments = getTestComments();

    // MOCKS // *************************************************************************************************************

    protected void setUpMocks() {
        MockitoAnnotations.initMocks(this);
        setUpDatastoreMocks();
    }

    private void setUpDatastoreMocks() {
        when(datastoreMock.save()).thenReturn(datastoreSaveActionsMock);
        when(datastoreMock.add()).thenReturn(datastoreAddActionsMock);
        when(datastoreMock.get()).thenReturn(datastoreGetActionsMock);
        when(datastoreMock.update()).thenReturn(datastoreUpdateActionsMock);
    }

    // RX JAVA // ***********************************************************************************************************

    private RxSchedulers getTestRxSchedulers() {
        return new RxSchedulers(Schedulers.single(), Schedulers.single(), Schedulers.single(),
                Schedulers.single());
    }

    // USER // **************************************************************************************************************

    private User getTestUser() {
        final User testUser = new User();
        testUser.setId(1);
        testUser.setName("Leanne Graham");
        testUser.setUsername("Bret");
        testUser.setEmail("Sincere@april.biz");
        return testUser;
    }

    private List<User> getTestUsers() {
        final List<User> testUsers = new ArrayList<>(1);
        testUsers.add(user);
        return testUsers;
    }

    // POST // **************************************************************************************************************

    private Post getTestPost() {
        final Post testPost = new Post();
        testPost.setUserId(1);
        testPost.setId(1);
        testPost.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        testPost.setBody("quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae "
                + "ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");
        return testPost;
    }

    private List<Post> getTestPosts() {
        final List<Post> testPosts = new ArrayList<>(1);
        testPosts.add(post);
        return testPosts;
    }

    // COMMENT // **************************************************************************************************************

    private Comment getTestComment() {
        final Comment testComment = new Comment();
        testComment.setPostId(1);
        testComment.setId(1);
        testComment.setEmail("Eliseo@gardner.biz");
        testComment.setBody("laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor "
                + "quam autem quasi\\nreiciendis et nam sapiente accusantium");
        return testComment;
    }

    private List<Comment> getTestComments() {
        final List<Comment> testComments = new ArrayList<>(1);
        testComments.add(comment);
        return testComments;
    }

}
