package app.urbanist.model.view;

import java.util.Date;
import java.util.List;

public class ReportDetailsModel {

    private Long id;

    private String title;

    private String content;

    private Date publishedOn;

    private String username;

    private List<ImageViewModel> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public List<ImageViewModel> getImages() {
        return images;
    }

    public void setImages(List<ImageViewModel> images) {
        this.images = images;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
