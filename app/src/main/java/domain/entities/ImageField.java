package domain.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilhermecardoso on 11/14/17.
 */

public class ImageField {
    @SerializedName("hidpi")
    private String hidpi;
    @SerializedName("normal")
    private String normal;
    @SerializedName("teaser")
    private String teaser;


    public ImageField(String hidpi, String normal, String teaser) {
        this.hidpi = hidpi;
        this.normal = normal;
        this.teaser = teaser;
    }

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }
}
