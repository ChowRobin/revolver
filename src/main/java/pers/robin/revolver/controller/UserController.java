package pers.robin.revolver.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.robin.revolver.bean.ResultBean;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.UserService;
import pers.robin.revolver.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
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
    public ResultBean<Collection<User>> getAll(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return new ResultBean<Collection<User>> (userService.getList(map));
    }

    /**
     * 获取单个用户
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResultBean<User> getOne(@PathVariable("id")Integer id) {
        return new ResultBean<>(userService.read(id));
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public ResultBean<Integer> create(@RequestBody User user) {
        return new ResultBean<Integer>(userService.create(user));
    }

    /**
     * 更新用户信息
     * @param id
     * @param user
     * @return
     */
    @PostMapping(value = "/{id}/update")
    public ResultBean<Boolean> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return new ResultBean<Boolean>(true);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}/delete")
    public ResultBean<Boolean> delete(@PathVariable Integer id) {
        return new ResultBean<Boolean>(userService.delete(id));
    }

}
