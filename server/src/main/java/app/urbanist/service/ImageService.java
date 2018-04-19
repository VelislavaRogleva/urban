package app.urbanist.service;

import app.urbanist.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {


    String uploadFile(MultipartFile file);

    String getDownloadLink(String imageId);

    boolean save(Image image);
}
