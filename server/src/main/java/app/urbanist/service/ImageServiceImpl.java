package app.urbanist.service;

import app.urbanist.cloud.CloudImageExtractor;
import app.urbanist.cloud.CloudImageUploader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final CloudImageUploader cloudImageUploader;
    private final CloudImageExtractor cloudImageExtractor;

    public ImageServiceImpl(CloudImageUploader cloudImageUploader, CloudImageExtractor cloudImageExtractor) {
        this.cloudImageUploader = cloudImageUploader;
        this.cloudImageExtractor = cloudImageExtractor;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String imageId = "";

        try {
            imageId = this.cloudImageUploader.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageId;
    }

    @Override
    public String getDownloadLink(String imageId) {
        String downloadLink = "";
        try {
           downloadLink = this.cloudImageExtractor.getImageUrl(imageId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downloadLink;
    }
}
