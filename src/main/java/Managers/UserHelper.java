package Managers;

import Models.User;

public class UserHelper extends DriverBasedHelper implements IUserHelper{

    public UserHelper(AppManager manager) {
        super(manager.getDriver());
    }

    @Override
    public void loginAs(User user) {
         pages.loginPage
                 .inputUsername(user.getUsername())
                 .inputPassword(user.getPassword())
                 .loginButtonClick();
    }

    @Override
    public boolean verifyAuth() {
        return pages.accountPage.verifyAccountPage();
    }
}
