package com.vti.Gencraft.Service.Imp;

import com.vti.Gencraft.DTO.Response.BaseResponse;
import com.vti.Gencraft.Entity.ImageEntity;
import com.vti.Gencraft.Entity.UserEntity;
import com.vti.Gencraft.Repository.ImageRepository;
import com.vti.Gencraft.Repository.UserRepository;
import com.vti.Gencraft.Service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<String> getAllImage() {
        List<ImageEntity> list = imageRepository.findAll();
        List<String> name = new ArrayList<>();
        for (ImageEntity i: list) {
            name.add(i.getUrl());
        }
        return name;
    }

    @Override
    public List<String> getImageByUserID(int id) {
        List<ImageEntity> list = imageRepository.findImageByUserId(id);
        List<String> url = new ArrayList<>();
        for(ImageEntity i : list){
            url.add(i.getUrl());
        }
        return url;
    }
    @Override
    public BaseResponse uploadImage(String url, int id) {
        UserEntity u = userRepository.findById(id);
        ImageEntity i = new ImageEntity();
        i.setUrl(url);
        i.setUserEntity(u);
        try{
            imageRepository.save(i);
            return new BaseResponse(200,"Lưu Thành Công",null);
        }catch(Exception e){
            return new BaseResponse(200,"Lưu Thành Công",e);
        }
    }
}
