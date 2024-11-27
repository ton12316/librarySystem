package cn.itcast;

import cn.itcast.dao.UsersDao;
import cn.itcast.dao.AdminsDao;
import cn.itcast.domain.Admin;
import cn.itcast.domain.RegisterFormUserAdmin;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userType = request.getParameter("userType");

        RegisterFormUserAdmin formBean = new RegisterFormUserAdmin() ;
        formBean.setName(username) ;
        formBean.setPassword (password) ;
        formBean.setPassword2 (confirmPassword) ;

        boolean success = false;

        if (! formBean.valildate(userType)) {
            request.setAttribute("formBean", formBean);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }


        // 根据 userType 判断注册的用户类型
        if ("user".equals(userType)) {
            // 创建 User 对象并插入到 Users 表
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            UsersDao usersDao = new UsersDao();
            success = usersDao.insert(user);
        } else if ("admin".equals(userType)) {
            // 创建 Admin 对象并插入到 Admins 表
            Admin admin = new Admin();
            admin.setAdmin_name(username);
            admin.setPassword(password);

            AdminsDao adminsDao = new AdminsDao();
            success = adminsDao.insert(admin);
        }

        // 返回结果
        if (success) {
            response.getWriter().println("注册成功！<a href='login.jsp'>点击返回登录</a>");
        } else {
            response.getWriter().println("注册失败，请重试！");
        }
    }
}

