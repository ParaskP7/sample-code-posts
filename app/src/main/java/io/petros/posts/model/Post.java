package io.petros.posts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    public static final String USER_ID = "userId";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String BODY = "body";

    @SerializedName(USER_ID)
    @Expose
    private Integer userId;

    @SerializedName(ID)
    @Expose
    private Integer id;

    @SerializedName(TITLE)
    @Expose
    private String title;

    @SerializedName(BODY)
    @Expose
    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

}
