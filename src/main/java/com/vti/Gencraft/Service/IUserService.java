package com.vti.Gencraft.Service;
import com.vti.Gencraft.DTO.Response.UserResponse.UserResponse;
import com.vti.Gencraft.Entity.UserEntity;
import java.util.List;

public interface IUserService {
    List<UserEntity> getAllUser(String username);
    boolean checkUser(String username,String password);
    boolean addUser(String username,String password);
    List<String> getOneUserByIdReturnImageName(int id);
    List<UserResponse> getAll();
}
