package Managers;

public class AccountHelper extends DriverBasedHelper implements IAccountHelper {

    public AccountHelper(AppManager manager) {
        super(manager.getDriver());
    }

    @Override
    public boolean search(String search) {
        pages.accountPage.searchSomething(search);
        return pages.accountPage.searchingResult().contains(search);
    }

    @Override
    public AccountHelper goToAccountPage() {
        pages.accountPage.goToAccountPage();
        return this;
    }
}
