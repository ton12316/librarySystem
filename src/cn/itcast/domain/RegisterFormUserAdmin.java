package cn.itcast.domain;

import cn.itcast.dao.AdminsDao;
import cn.itcast.dao.UsersDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterFormUserAdmin {
    private String name;
    private String password;
    private String password2;

    private Map<String, String> errors = new HashMap<String, String>();

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


    public boolean valildate(String userType) {
        boolean flag = true;
        if( name == null || name.trim().equals("")) {
            errors.put("name", "请输入姓名.");
            flag = false;
        }
        if( password == null || password.trim().equals("")) {
            errors.put("password", "请输入密码");
            flag = false;
        }else if (password.length() > 12 || password.length() < 6){
            errors.put("password", "请输入6-12个字符.");
            flag = false;
        }

        if( password != null && !password.equals(password2)) {
            errors.put("password2", "两次输入密码不匹配");
            flag = false;
        }

        // 通过 UsersDao 或 AdminsDao 查询数据库，检查用户名是否已存在
        boolean usernameExists = false;

        // 检查普通用户
        if ("user".equals(userType)) {
            UsersDao usersDao = new UsersDao();
            usernameExists = usersDao.isUsernameExist(name);
        }
        // 检查管理员
        else if ("admin".equals(userType)) {
            AdminsDao adminsDao = new AdminsDao();
            usernameExists = adminsDao.isUsernameExist(name);
        }

        // 如果用户名已存在，则提示并跳转回注册页面
        if (usernameExists) {
            errors.put("name", "用户已存在");
            flag = false;
        }


        return flag;
    }

    public void setErrorMsg(String err, String errMsg){
        if(err != null && (errMsg != null)) {
            errors.put(err, errMsg);
        }
    }

    public Map<String, String> getErrors(){
        return errors;
    }


}
