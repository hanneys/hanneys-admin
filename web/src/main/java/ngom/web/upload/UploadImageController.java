package ngom.web.upload;

import ngom.exception.ErrorInfo;
import ngom.exception.NgomAdminException;
import ngom.upload.UploadImageService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hanaijun on 2018/8/6
 */
@RestController
@RequestMapping("upload")
public class UploadImageController extends BaseController {





    @Autowired
    private UploadImageService uploadImageService;


    /**
     * @api {POST} /upload/uploadImages 上传图片
     * @apiGroup base
     * @apiVersion 1.0.1
     * @apiDescription 上传图片
     * @apiParam {MultipartFile} file 文件
     * @apiParam {Integer} type 0:
     * @apiParamExample {json} 请求示例：
     *{
     *      "type":"123"
     *      }
     * @apiSuccess {String} success true:正确   false:错误
     * @apiSuccess {String} data 回显url
     * @apiSuccessExample {json} 返回样例:
     *               {
     *     "data": "http://192.168.8.12:8080/pictures/1533535610276_123.png",
     *     "success": true
     * }
     */
    @RequestMapping(value = "uploadImages",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam(value = "type",required = true) Integer type, HttpSession httpSession){
        Long userId= Long.valueOf(httpSession.getAttribute("userId").toString());
        String[] locations={"/article/image","/"};
        if(locations.length<type){
            throw new NgomAdminException(ErrorInfo.UPLOADE_IMG_LOCATION_ERR_CODE,ErrorInfo.UPLOADE_IMG_LOCATION_ERR_INFO);
        }
        return uploadImageService.uploadImages(file,locations[type],userId);
    }


}
