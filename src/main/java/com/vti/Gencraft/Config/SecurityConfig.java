package com.vti.Gencraft.Config;

import com.vti.Gencraft.Filter.JwtFilter;
import com.vti.Gencraft.Provider.CustomAuthenication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//Thông báo cho IOC biết đây là lớp cấu hình
@Configuration
//Thông báo cho Spring security để bật cấu hình bảo mật
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private CustomAuthenication customAuthenication;
//    cấu hình bảo mật của Spring security thông qua việc cấu hình lớp
//    AuthenticationManager có tham số truyền vào là HttpSecurity
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
//              dùng getSharedObject để truy xuất đến lớp SuthenticationManagerBuilder để cấu hình
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                việc định nghĩa các loại authentication nằm ở đây
                .authenticationProvider(customAuthenication)
//              phương thức này được triển khai cuối cùng để đánh dấu cho kết thúc của việc cấu hình lớp Authenticate
//              nó sẽ trả về một đối tượng kiểu Authenticate
                .build();
    }
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http.cors().and()
                .csrf().disable() // Tắt cấu hình liên quan tới tấn công csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Khai báo không sử dụng session trong project
                .and()
                .authorizeHttpRequests() //Quy định lại các rule liên quan đến chứng thực cho link được gọi
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/generate/**")
                .authenticated() //Tất cả các link còn lại đều phải chứng thực
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cho tất cả các URL

        return source;
    }
}
