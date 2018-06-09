package pers.robin.revolver.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.robin.revolver.service.UserService;
import pers.robin.revolver.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> list(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return new ResponseEntity<>(userService.getList(map), HttpStatus.OK);
    }

}
