package app.urbanist.model.binding;

import javax.validation.constraints.NotEmpty;

public class ImageUploadModel {

    private String caption;

    @NotEmpty
    private String file;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
