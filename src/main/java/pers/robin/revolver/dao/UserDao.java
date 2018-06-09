package pers.robin.revolver.dao;

import pers.robin.revolver.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> getList(Map<String, Object> map);
}
