package app.urbanist.controller;

import app.urbanist.cloud.CloudImageUploader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class ImageController {

    private final CloudImageUploader cloudImageUploader;

    public ImageController(CloudImageUploader cloudImageUploader) {
        this.cloudImageUploader = cloudImageUploader;
    }

    @PostMapping("/images/upload")
    public String images(@RequestParam(name = "file") MultipartFile file) {

        String imageId = "";

        try {
            imageId = this.cloudImageUploader.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageId;
    }
}
