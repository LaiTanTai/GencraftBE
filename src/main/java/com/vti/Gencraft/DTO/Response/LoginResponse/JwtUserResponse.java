package com.vti.Gencraft.DTO.Response.LoginResponse;

import java.util.List;

public class JwtUserResponse {
    private String username;
    private String jwt;
    private List<String> urls;

    public JwtUserResponse(String username, String jwt, List<String> urls) {
        this.username = username;
        this.jwt = jwt;
        this.urls = urls;
    }

    public List<String> getUrls() {
        return this.urls;
    }

    public void setId(List<String> urls) {
        this.urls = urls;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
