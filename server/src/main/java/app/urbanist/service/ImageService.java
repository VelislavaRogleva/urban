package app.urbanist.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {


    String uploadFile(MultipartFile file);

    String getDownloadLink(String imageId);
}
