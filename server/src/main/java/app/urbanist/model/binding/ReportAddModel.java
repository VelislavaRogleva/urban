package app.urbanist.model.binding;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ReportAddModel {

    @NotEmpty
    private String location;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private List<ImageUploadModel> images;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<ImageUploadModel> getImages() {
        return images;
    }

    public void setImages(List<ImageUploadModel> images) {
        this.images = images;
    }
}
