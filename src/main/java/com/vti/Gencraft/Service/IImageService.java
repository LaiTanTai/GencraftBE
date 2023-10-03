package com.vti.Gencraft.Service;
import com.vti.Gencraft.DTO.Response.BaseResponse;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    List<String> getAllImage();
    List<String> getImageByUserID(int id);
    BaseResponse uploadImage(String url, int id);
}
