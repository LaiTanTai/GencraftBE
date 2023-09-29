package com.vti.Gencraft.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class Jwt {
    @Value("${jwt.secret.key}")
    private String secret;

}
