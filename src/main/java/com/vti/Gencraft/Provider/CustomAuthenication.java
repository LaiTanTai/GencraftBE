package com.vti.Gencraft.Provider;
import com.vti.Gencraft.Entity.UserEntity;
import com.vti.Gencraft.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

// annotation Service để đánh dấu trong IOC rằng đây là một
@Service
public class CustomAuthenication implements AuthenticationProvider {
//    Khi ta cấu hình mặc định cho Spring Boot thì lớp triển khai interface cua PasswordEncoder là lớp BCryptPasswordEncoder
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<UserEntity> users = userRepository.findAllUsername(username);
        if(users != null) {
            for (UserEntity u : users) {
                if(passwordEncoder.matches(password,u.getPassword())){
//                    Đăng nhập thành công
//                    Việc trả về UsernamePasswordSuthenticationToken là một định dạng chuẩn trong việc xác thực
                    return new UsernamePasswordAuthenticationToken(u.getId(),null);
                }
            }
        }
        return null;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        // Khai báo loại chứng thực sử dụng để so sánh
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
