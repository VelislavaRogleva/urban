package app.urbanist.model.view;

import java.util.Date;
import java.util.List;

public class ReportDetailsModel {

    private String title;

    private String content;

    private Date publishedOn;

    private List<ImageViewModel> images;

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
}
