package app.urbanist.cloud;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class CloudImageExtractor {

    private static final String QUERY_PATH_SEPARATOR = "?";

    private static final String QUERY_PARAMETER_SEPARATOR = "&";

    private static final String FILE_ID_PARAMETER = "fileid=";

    private static final String AUTH_PARAMETER = "auth=";

    private static final String CODE_PARAMETER = "code=";

    private static final String LIST_FILE_URL
            = "https://api.pcloud.com/getfilepublink";

    private static final String DOWNLOAD_FILE_URL
            = "https://api.pcloud.com/getpublinkdownload";

    private final HttpRequestExecutor httpRequestExecutor;

    private final CloudAuthorizationService cloudAuthorizationService;

    public CloudImageExtractor(HttpRequestExecutor httpRequestExecutor, CloudAuthorizationService cloudAuthorizationService) {
        this.httpRequestExecutor = httpRequestExecutor;
        this.cloudAuthorizationService = cloudAuthorizationService;
    }

    public String getImageUrl(String imageId) throws IOException {
        String fileId = imageId.substring(1);
        Gson gson = new Gson();
        String accessToken = this.cloudAuthorizationService.getAccessToken();

        String fileListJsonResult = this.httpRequestExecutor.executeGetRequest(
                LIST_FILE_URL
                        + QUERY_PATH_SEPARATOR
                        + FILE_ID_PARAMETER
                        + fileId
                        + QUERY_PARAMETER_SEPARATOR
                        + AUTH_PARAMETER
                        + accessToken).body().string();

        if (gson.fromJson(fileListJsonResult, Map.class).get("error") != null) return null;

        String fileCode = gson.fromJson(fileListJsonResult, Map.class).get("code").toString();

        String fileDownloadJsonResult = this.httpRequestExecutor.executeGetRequest(
                DOWNLOAD_FILE_URL
                        + QUERY_PATH_SEPARATOR
                        + CODE_PARAMETER
                        + fileCode).body().string();

        Map<String, Object> fileDownloadData = gson.fromJson(fileDownloadJsonResult, Map.class);

        String filePath = fileDownloadData.get("path").toString();
        String host = ((ArrayList<String>) fileDownloadData.get("hosts")).get(0);

        return "https://" + host + filePath;
    }
}
