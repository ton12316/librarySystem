package cn.itcast.servlet;

import cn.itcast.dao.AdminsDao;
import cn.itcast.dao.UsersDao;
import cn.itcast.domain.Admin;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码和响应方式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取登录页面提交的数据
        String loginName = request.getParameter("username");
        String loginPassword = request.getParameter("password");
        String userType = request.getParameter("userType"); // 获取用户类型

        try {
            if ("user".equals(userType)) {
                // 普通用户登录
                UsersDao usersDao = new UsersDao();
                User user = usersDao.findUserByUsernameAndPassword(loginName, loginPassword);

                if (user != null) {
                    response.sendRedirect(request.getContextPath() + "/showBooksServlet");
                    return;
                } else {
                    request.setAttribute("errorMessage", "用户名或密码错误，请重试！");
                }
            } else if ("admin".equals(userType)) {
                // 管理员登录
                AdminsDao adminDao = new AdminsDao();
                Admin admin = adminDao.findAdminByUsernameAndPassword(loginName, loginPassword);

                if (admin != null) {
                    response.sendRedirect(request.getContextPath() + "/showBooksServlet");
                    return;
                } else {
                    request.setAttribute("errorMessage", "用户名或密码错误，请重试！");
                }
            } else {
                request.setAttribute("errorMessage", "未知用户类型！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "系统异常，请稍后再试！");
        }

        // 转发到 login.jsp 并显示错误信息
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
