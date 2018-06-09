package pers.robin.revolver.service;

import org.springframework.stereotype.Service;
import pers.robin.revolver.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Object getList(Map<String, Object> map);
}
