package pers.robin.revolver.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.robin.revolver.bean.ResultBean;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.AuthenticationService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    private static final Logger logger = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("")
    public ResultBean<String> login(@RequestBody User user) {
        return new ResultBean<String>(authenticationService.getToken(user));
    }

}
