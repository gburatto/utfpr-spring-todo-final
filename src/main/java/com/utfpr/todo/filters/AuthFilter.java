package com.utfpr.todo.filters;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.utfpr.todo.clean.infra.model.UserModel;
import com.utfpr.todo.clean.infra.repository.UserModelJpaRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserModelJpaRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || path.trim().isEmpty()) {
            path = request.getServletPath();
        }

        if (path.equals("/createUser")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");

        //System.out.println(authorization);

        if (authorization == null || !authorization.startsWith("Basic")) {
            response.setStatus(401);
            return;
        }

        String token = authorization.substring("Basic".length()).trim();

        System.out.println(token);

        Base64.Decoder decoder = Base64.getDecoder();

        String decodedToken = new String(decoder.decode(token));

        //System.out.println(decodedToken);

        String[] parts = decodedToken.split(":");

        String username = parts[0];
        String password = parts[1];

        //System.out.println(username);
        //System.out.println(password);

        UserModel user = userRepository.findByUsername(username);

        if (user == null || !BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
            response.setStatus(401);
            return;
        }

        System.out.println(user.getId());

        request.setAttribute("userId", user.getId());

        filterChain.doFilter(request, response);

    }

}
