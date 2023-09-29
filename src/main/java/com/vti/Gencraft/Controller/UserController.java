package com.vti.Gencraft.Controller;

import com.vti.Gencraft.DTO.Response.BaseResponse;
import com.vti.Gencraft.DTO.Response.UserResponse.UserResponse;
import com.vti.Gencraft.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generate")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/Explore")
    public ResponseEntity<BaseResponse> getAllUser(){
        List<UserResponse> users = userService.getAll();
        BaseResponse res = new BaseResponse(200,"Thành công",users);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
