package pers.robin.revolver.service;

import pers.robin.revolver.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List getList(Map<String, Object> map);

    User read(Integer id);

    User findByUserName(String userName);

    Boolean checkUser(User user);

    Integer create(User user);

    void update(User user);

    Boolean delete(Integer id);

    boolean comparePassword(User user, User userInDB);
}
