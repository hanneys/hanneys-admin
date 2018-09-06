package ngom.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by hanaijun on 2018/8/30
 */
public interface UploadImageService {
    Map<String,Object> uploadImages(MultipartFile file, String path, Long userId);
}
