package com.vti.Gencraft.Filter;

import com.vti.Gencraft.Thread.UserContext;
import com.vti.Gencraft.utils.CustomUserDetail;
import com.vti.Gencraft.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// Mỗi request cần chứng thực sẽ nhảy vào đây
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            // Lấy giá trị của header có key là Authorization
            String header = request.getHeader("Authorization");
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                Claims claims = jwtHelper.decodeToken(token);
                if(claims != null){
                    // Tạo chứng thực cho Security
                    String userId = claims.getSubject();
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userId, null,AuthorityUtils.createAuthorityList(userId));
                    context.setAuthentication(userToken);
                }
            }
        }catch (Exception e){

        }

        filterChain.doFilter(request, response);
    }
}
