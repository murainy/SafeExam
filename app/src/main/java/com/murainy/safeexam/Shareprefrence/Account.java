package com.murainy.safeexam.Shareprefrence;

/**
 * Created by murainy on 2015/12/13.
 */
public class Account {

    private String account;
    private String password;
    private boolean autologin;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutologin() {
        return autologin;
    }

    public void setAutologin(boolean autologin) {
        this.autologin = autologin;
    }
}