package io.petros.posts.service.retrofit;

import java.util.List;

import io.petros.posts.model.Comment;
import io.petros.posts.model.Post;
import io.petros.posts.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("/posts")
    Observable<List<Post>> posts();

    @GET("/users")
    Observable<List<User>> users();

    @GET("/comments")
    Observable<List<Comment>> comments(@Query("postId") int postId);

}
