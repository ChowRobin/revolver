package pers.robin.revolver.service;

import pers.robin.revolver.model.User;

public interface AuthenticationService {

    String getToken(User user);
}
