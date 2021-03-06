package controller.admin;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
管理员登录
 */
@WebServlet(name = "AdminToLoginServlet",urlPatterns = "/loginServlet")
public class AdminToLoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //处理中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //根据管理员的账号 查询管理员信息（账号和密码）
        UserDao userdao=new UserDao();
        User user = userdao.selectAdminInfo(username);
        //对于管理员登录 我们只需要判断密码和账号即可
        if(user.getUserName().equals(username)&&user.getUserPassword().equals(password)){
            //用户名和密码都匹配上了 去首页 同时绑定用户名到session
            HttpSession session = req.getSession();//默认生命时长30分钟
            session.setAttribute("loginName",username);
            //将session绑定到
            //跳转到主页 转发
            req.getRequestDispatcher("/resources/admin/AdminTemp.jsp").forward(req, resp);

        }else{
            //用户名和密码错误  将错误信息显示在登录页面
            req.setAttribute("error","用户名或者密码错误！");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
}
