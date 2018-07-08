package pers.robin.revolver.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.AuthenticationService;

import java.io.UnsupportedEncodingException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public String getToken(User user) {
        String token = "";
        try {
            token = JWT.create().withAudience(user.getUserName()).sign(Algorithm.HMAC256(user.getPassword()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }
}
