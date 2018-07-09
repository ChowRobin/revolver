package pers.robin.revolver.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.AuthenticationService;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class AuthenticationServiceImplTest {

    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @Test
    public void getToken() {
        User user = new User();
        user.setUserName("John");
        user.setPassword("12345678");
        String token = null;
        try {
            token = JWT.create().withAudience(user.getUserName()).sign(Algorithm.HMAC256(user.getPassword()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String token1 = authenticationService.getToken(user);
        Assert.assertEquals(token, token1);
    }
}