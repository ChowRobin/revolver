package pers.robin.revolver.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.UserService;
import pers.robin.revolver.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @param request
     * @return
     */
    @GetMapping(produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> list(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return new ResponseEntity<>(userService.getList(map), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Object> getOne(@PathVariable("id")Integer id) {
        User user = userService.read(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody User user) {
        try {
            userService.create(user);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/update")
    @ResponseBody
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody User user) {
        try {
            user.setId(id);
            userService.update(user);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
