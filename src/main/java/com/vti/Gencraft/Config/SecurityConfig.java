package com.vti.Gencraft.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//Thông báo cho IOC biết đây là lớp cấu hình
@Configuration
//Thông báo cho Spring security để bật cấu hình bảo mật
@EnableWebSecurity
public class SecurityConfig {

}
