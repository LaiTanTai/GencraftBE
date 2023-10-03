package com.vti.Gencraft.Controller;
import com.vti.Gencraft.DTO.Response.BaseResponse;
import com.vti.Gencraft.Service.IImageService;
import com.vti.Gencraft.Thread.UserContext;
import com.vti.Gencraft.utils.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${image.folder.path}") // Đường dẫn thư mục chứa hình ảnh, được đặt trong file application.properties
    private String imageDirectory;
    @Autowired
    private IImageService imageService;
    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllNameImage(){
        List<String> imageNames = imageService.getAllImage();
        BaseResponse res = new BaseResponse(200,"Thành công",imageNames);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse> getImageByUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = (String) authentication.getPrincipal();
        BaseResponse res = new BaseResponse(200,"Thành Công",imageService.getImageByUserID(Integer.parseInt(id)));
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @PostMapping("/upload")
    public ResponseEntity<BaseResponse> uploadImage(@RequestParam String imageUrl) {
//      có nhiều cách để lấy dữ liệu từ việc chứng thực
//      cách 1
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = (String) authentication.getPrincipal();
//        cách 2 dùng thread
        BaseResponse res = imageService.uploadImage(imageUrl, Integer.parseInt(id));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
