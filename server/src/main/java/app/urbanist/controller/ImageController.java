package app.urbanist.controller;

import app.urbanist.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images/upload")
    public ResponseEntity<?> images(@RequestParam(name = "file") MultipartFile file) {

        String fileId = this.imageService.uploadFile(file);

        if (fileId == null) return new ResponseEntity<>("Unsupported media type", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(fileId, HttpStatus.OK);
    }
}
