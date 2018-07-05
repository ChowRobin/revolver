package pers.robin.revolver.service;

import pers.robin.revolver.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List getList(Map<String, Object> map);

    User read(Integer id);

    void create(User user);

    void update(User user);

    void delete(Integer id);
}
