package com.vti.Gencraft.DTO.Response.LoginResponse;

import java.util.List;

public class JwtUserResponse {
    private String username;
    private String jwt;
    private List<String> url;

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public JwtUserResponse(String username, String jwt, List<String> url) {
        this.username = username;
        this.jwt = jwt;
        this.url = url;
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
