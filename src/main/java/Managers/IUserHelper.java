package Managers;

import Models.User;

import javax.jws.soap.SOAPBinding;

public interface IUserHelper {
    void loginAs(User user);
    boolean verifyAuth();
}
