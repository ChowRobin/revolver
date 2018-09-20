package pers.robin.revolver.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.robin.revolver.model.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    List<User> getList(Map<String, Object> map);

    User read(Integer id);

    User findByUserName(String userName);

    Integer create(User user);

    void update(User user);

    void delete(Integer id);

}
