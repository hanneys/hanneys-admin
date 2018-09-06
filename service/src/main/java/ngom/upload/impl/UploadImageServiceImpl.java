package ngom.upload.impl;

import ngom.exception.ErrorInfo;
import ngom.exception.NgomAdminException;
import ngom.upload.UploadImageService;
import ngom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanaijun on 2018/8/30
 */
@Service
public class UploadImageServiceImpl implements UploadImageService {


    @Value("${info.image.fixed-url}")
    private String imageUrl;

    @Value("${info.image.base-url}")
    private String baseUrl;

    @Value("${info.image.picture-url}")
    private String prictureUrl;


    /**
     * 上传图片
     * @param file
     * @param path
     * @param userId
     * @return
     */
    @Override
    public Map<String,Object> uploadImages(MultipartFile file, String path, Long userId){

        Map<String, Object> map = new HashMap<>();


        if(StringUtils.isEmpty(file)){
            throw new NgomAdminException(ErrorInfo.UPLOADE_IMG_EMPTY_ERR_CODE, ErrorInfo.UPLOADE_IMG_EMPTY_ERR_INFO);
        }
        if(StringUtils.isEmpty(path)){
            throw new NgomAdminException(ErrorInfo.UPLOADE_IMG_LOCATION_ERR_CODE, ErrorInfo.UPLOADE_IMG_LOCATION_ERR_INFO);
        }

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            // 图片宽度,单位px
            Integer width = image.getWidth();
            // 图片高度,单位px
            Integer height= image.getHeight();
            map.put("width",width);
            map.put("height",height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newFileName=IdWorker.getIdWorker().nextId() +"_"+userId;

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String allowSuffixs = "gif,jpg,jpeg,bmp,png,ico";
        if(!allowSuffixs.contains(suffix)){
            // 图片格式验证
            throw new NgomAdminException(ErrorInfo.UPLOADE_IMG_ERR_CODE,ErrorInfo.UPLOADE_IMG_ERR_INFO);
        }
        // /usr/local/pictures/ngom-cms/infomation
        String filePath = imageUrl + path;


        // 图片 newFileName+"."+suffix
        File targetFile = new File(filePath);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            String name=newFileName+"."+suffix;
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + File.separator + name));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String image=newFileName+"."+suffix;
        // 图片url
        String url= baseUrl+prictureUrl+path+File.separator+image;
        map.put("url",url);
        return map;
    }




}
