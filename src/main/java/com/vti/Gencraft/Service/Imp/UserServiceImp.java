package com.vti.Gencraft.Service.Imp;

import com.vti.Gencraft.DTO.Response.UserResponse.UserResponse;
import com.vti.Gencraft.Entity.ImageEntity;
import com.vti.Gencraft.Entity.UserEntity;
import com.vti.Gencraft.Repository.UserRepository;
import com.vti.Gencraft.Service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUser(String username) {
        List<UserEntity> list = userRepository.findAllUsername(username);
        return list;
    }
    public UserEntity getOneUser(String username){
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }
    @Override
    public boolean checkUser(String username,String password) {
        UserEntity user = getOneUser(username);
        boolean check = false;
        if(user==null){
            addUser(username,password);
            check = true;
        }else{
            if(passwordEncoder.matches(password,user.getPassword())){
                check = false;
            }else{
                addUser(username,password);
                check = true;
            }
        }
        return check;
    }
    @Override
    public boolean addUser(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<String> getOneUserByIdReturnImageName(int id) {
        UserEntity user = userRepository.findById(id);
        List<String> url = new ArrayList<>();
        for(ImageEntity i : user.getListImage()){
            url.add(i.getUrl());
        }
        return url;
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserEntity> users = userRepository.findAll();
        List<UserResponse> res = new ArrayList<>();
        for (UserEntity u: users) {
            List<String> url = new ArrayList<>();
            for (ImageEntity i:u.getListImage()) {
                url.add(i.getUrl());
            }
            UserResponse userRes = new UserResponse(u.getUsername(),u.getAvatar(),url);
            res.add(userRes);
        }
        return res;
    }
}
