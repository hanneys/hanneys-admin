package ngom.domain.article;

public class Image {
    private String type="IMAGE";
    private String url;
    private String height;
    private String width;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
