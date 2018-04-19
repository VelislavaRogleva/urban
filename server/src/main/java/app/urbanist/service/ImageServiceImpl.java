package app.urbanist.service;

import app.urbanist.cloud.CloudImageExtractor;
import app.urbanist.cloud.CloudImageUploader;
import app.urbanist.entity.Image;
import app.urbanist.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final CloudImageUploader cloudImageUploader;
    private final CloudImageExtractor cloudImageExtractor;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(CloudImageUploader cloudImageUploader, CloudImageExtractor cloudImageExtractor, ImageRepository imageRepository) {
        this.cloudImageUploader = cloudImageUploader;
        this.cloudImageExtractor = cloudImageExtractor;
        this.imageRepository = imageRepository;
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

    @Override
    public boolean save(Image image) {
        this.imageRepository.save(image);
        return true;
    }
}
