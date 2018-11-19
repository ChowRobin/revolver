package pers.robin.revolver.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.robin.revolver.dao.UserDao;
import pers.robin.revolver.exception.CheckException;
import pers.robin.revolver.model.User;
import pers.robin.revolver.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List getList(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userDao.getList(map);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public Boolean checkUser(User user) {
        String pattern = "%s can not be null.";
        List<String> msgList = new ArrayList<String>();
        List<String> columenNotNullList = new ArrayList<String>();
        columenNotNullList.add("userName");
        columenNotNullList.add("name");
        columenNotNullList.add("password");
        columenNotNullList.add("email");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(user);
        for (String key : columenNotNullList) {
            if (jsonObject.get(key) == null) {
                msgList.add(String.format(pattern, key));
            }
        }
        if (this.findByUserName(user.getUserName()) != null) {
            msgList.add("this username has used.");
        }
        if (msgList.size() == 0) {
            return true;
        } else {
            throw new CheckException(StringUtils.join(msgList.toArray()));
        }
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public Integer create(User user) {
        checkUser(user);
        userDao.create(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public Boolean delete(Integer id) {
        userDao.delete(id);
        if (userDao.read(id) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean comparePassword(User user, User userInDB) {
        return user.getPassword().equals(userInDB.getPassword());
    }
}
