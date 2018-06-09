package pers.robin.revolver.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.robin.revolver.dao.UserDao;
import pers.robin.revolver.service.UserService;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Object getList(Map<String, Object> map) {
        return userDao.getList(map);
    }
}
