package pers.robin.revolver.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.AuthenticationService;
import pers.robin.revolver.service.UserService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    private static final Logger logger = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Object> login(@RequestBody User user) {
        User userInDB = userService.findByUserName(user.getUserName());
        JSONObject jsonObject = new JSONObject();
        if (userInDB == null) {
            jsonObject.put("msg", "The username has not signed up.");
        } else if (!userService.comparePassword(user, userInDB)) {
            jsonObject.put("msg", "password is incorrect.");
        } else {
            String token = authenticationService.getToken(user);
            jsonObject.put("token", token);
            jsonObject.put("user", userInDB);
        }
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

}
