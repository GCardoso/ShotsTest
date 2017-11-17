package domain.entities;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guilhermecardoso on 11/12/17.
 */

public class Shot {

    private long id;
    private String title;
    private String description;
    private int width;
    private int height;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("views_count")
    private int viewsCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("images")
    private ImageField images;

    public Shot(long id, String title, String description, int width, int height, String created_at, int views_count, int comments_count, ImageField images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.width = width;
        this.height = height;
        this.createdAt = created_at;
        this.viewsCount = views_count;
        this.commentsCount = comments_count;
        this.images = images;
    }

    public Shot() {

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
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

    public ImageField getImages() {
        return images;
    }

    public void setImages(ImageField images) {
        this.images = images;
    }

    public String getFormattedViews() {
        return String.valueOf(this.viewsCount) + " Views";
    }

    public String getFormattedCreatedDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date createdAt;
        try {
            createdAt = format.parse(this.createdAt);

            format = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss");
        } catch (ParseException e) {
            createdAt = new Date();
        }
        return format.format(createdAt);
    }

    public String getFormattedCommentsCount() {
        return String.valueOf(this.commentsCount) + " Comments";
    }

    public Spanned getFormattedDescription() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(this.description, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(this.description);
        }
    }
}
