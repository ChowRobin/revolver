package pers.robin.revolver.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.UserService;
import pers.robin.revolver.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取全体用户列表
     * @param request
     * @return
     */
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> list(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return new ResponseEntity<>(userService.getList(map), HttpStatus.OK);
    }

    /**
     * 获取单个用户
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOne(@PathVariable("id")Integer id) {
        User user = userService.read(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> create(@RequestBody User user) {
        try {
            List<String> msgList;
            JSONObject jsonObject = new JSONObject();
            msgList = userService.checkUser(user);
            if (msgList.size() == 0) {
                userService.create(user);
                msgList.add("create successful.");
            }
            jsonObject.put("msg", msgList);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 更新用户信息
     * @param id
     * @param user
     * @return
     */
    @PostMapping(value = "/{id}/update")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody User user) {
        try {
            user.setId(id);
            userService.update(user);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
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
