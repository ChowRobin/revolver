package pers.robin.revolver.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.robin.revolver.exception.CheckException;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.AuthenticationService;
import pers.robin.revolver.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserService userService;

    @Override
    public String getToken(User user) {
        checkUser(user);
        String token = "";
        try {
            token = JWT.create().withAudience(user.getUserName()).sign(Algorithm.HMAC256(user.getPassword()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public Boolean checkUser(User user) {
        User userInDB = userService.findByUserName(user.getUserName());
        List<String> msgList = new ArrayList<>();
        if (userInDB == null) {
            msgList.add("The username has not signed up.");
        } else if (!userService.comparePassword(user, userInDB)) {
            msgList.add("password is incorrect.");
        }
        if (msgList.size() == 0) {
            return true;
        } else {
            throw new CheckException(StringUtils.join(msgList.toArray()));
        }
    }
}
