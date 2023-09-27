package com.vti.Gencraft.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class Jwt {
    @Value("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9")
    private String secret;

}
