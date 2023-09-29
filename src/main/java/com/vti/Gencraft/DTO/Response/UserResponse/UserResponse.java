package com.vti.Gencraft.DTO.Response.UserResponse;

import java.util.List;

public class UserResponse {
    private String username;
    private String avatar;
    private List<String> url;

    public UserResponse(String username, String avatar, List<String> url) {
        this.username = username;
        this.avatar = avatar;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
