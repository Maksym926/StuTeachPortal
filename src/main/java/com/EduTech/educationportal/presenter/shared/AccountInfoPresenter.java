package com.EduTech.educationportal.presenter.shared;

import com.EduTech.educationportal.interfaces.presenter.AccountInfoPresenterInterface;
import com.EduTech.educationportal.interfaces.view.AccountInfoViewInterface;

public class AccountInfoPresenter implements AccountInfoPresenterInterface   {
    AccountInfoViewInterface view;
    public AccountInfoPresenter(AccountInfoViewInterface view){
        this.view = view;
        view.setPresenter(this);
    }
}
