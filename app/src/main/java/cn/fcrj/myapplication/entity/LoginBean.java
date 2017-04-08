package cn.fcrj.myapplication.entity;

/**
 * Created by zht on 2017/04/07 9:59
 */

public class LoginBean {

    private String name;
    private String pwd;

    public LoginBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

