package domain.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermecardoso on 11/12/17.
 */

public class Shot {

    private long id;
    private String title;
    private String description;
    private int width;
    private int height;
    @SerializedName("createdAt")
    private int createdAt;
    @SerializedName("views_count")
    private int viewsCount;
    @SerializedName("commentsCount")
    private int commentsCount;

    public Shot(long id, String title, String description, int width, int height, int created_at, int views_count, int comments_count) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.width = width;
        this.height = height;
        this.createdAt = created_at;
        this.viewsCount = views_count;
        this.commentsCount = comments_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int views_count) {
        this.viewsCount = views_count;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int comments_count) {
        this.commentsCount = comments_count;
    }
}
