package io.petros.posts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    public static final String POST_ID = "postId";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String BODY = "body";

    @SerializedName(POST_ID)
    @Expose
    private Integer postId;

    @SerializedName(ID)
    @Expose
    private Integer id;

    @SerializedName(NAME)
    @Expose
    private String name;

    @SerializedName(EMAIL)
    @Expose
    private String email;

    @SerializedName(BODY)
    @Expose
    private String body;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(final Integer postId) {
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

}
