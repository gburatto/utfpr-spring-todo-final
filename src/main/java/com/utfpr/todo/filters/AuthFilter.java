package com.utfpr.todo.filters;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.utfpr.todo.users.UserModel;
import com.utfpr.todo.users.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        

        String servletPath = request.getServletPath();

        System.out.println(servletPath);

        if (servletPath.equals("/users")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");

        System.out.println(authorization);

        if (authorization == null || !authorization.startsWith("Basic")) {
            response.setStatus(401);
            return;
        }

        String token = authorization.substring("Basic".length()).trim();

        System.out.println(token);

        Base64.Decoder decoder = Base64.getDecoder();

        String decodedToken = new String(decoder.decode(token));

        System.out.println(decodedToken);

        String[] parts = decodedToken.split(":");

        String username = parts[0];
        String password = parts[1];

        System.out.println(username);
        System.out.println(password);

        UserModel user = userRepository.findByUsername(username);

        if (user == null || !BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
            response.setStatus(401);
            return;
        }

        filterChain.doFilter(request, response);

    }

}
