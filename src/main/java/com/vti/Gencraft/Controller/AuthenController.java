package com.vti.Gencraft.Controller;
import com.vti.Gencraft.DTO.Request.SignupRequest;
import com.vti.Gencraft.DTO.Response.BaseResponse;
import com.vti.Gencraft.DTO.Response.LoginResponse.JwtUserResponse;
import com.vti.Gencraft.Service.IUserService;
import com.vti.Gencraft.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AuthenController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private IUserService userService;
//    một annotation để ánh xạ các yêu cầu HTTP đến các phương thức xử lý
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> Login(@RequestBody SignupRequest request){
        UsernamePasswordAuthenticationToken token =(UsernamePasswordAuthenticationToken) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        String jwt =jwtHelper.generateToken(token.getName());
        List<String> url = userService.getOneUserByIdReturnImageName(Integer.parseInt(token.getName()));
        //        Gán dữ liệu cho đối tượng trả về
        JwtUserResponse user = new JwtUserResponse(request.getUsername(),jwt,url);
        BaseResponse res = new BaseResponse(200,"Thành công",user);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> Register(@RequestBody SignupRequest request){
        boolean checkUser = userService.checkUser(request.getUsername(),request.getPassword());
        if(checkUser){
            BaseResponse baseResponse = new BaseResponse(200,"Đăng kí thành công");
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
        BaseResponse baseResponse = new BaseResponse(400,"Đăng kí thất bại");
        return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
    }

}
