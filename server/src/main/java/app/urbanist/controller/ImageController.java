package app.urbanist.controller;

import app.urbanist.cloud.CloudImageUploader;
import app.urbanist.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images/upload")
    public String images(@RequestParam(name = "file") MultipartFile file) {
        return this.imageService.uploadFile(file);
    }
}
