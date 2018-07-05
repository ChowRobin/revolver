package pers.robin.revolver.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.robin.revolver.dao.UserDao;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.UserService;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List getList(Map<String, Object> map) {
        return userDao.getList(map);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }
}
