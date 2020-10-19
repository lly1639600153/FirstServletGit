package controller.admin;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet",urlPatterns = "/userDeleteServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String id = req.getParameter("id");
        //将这个id传递到UserDao的一个方法
        UserDao userDao=new UserDao();
        int row = userDao.deleteUserInfo(id);
        if (row>0){
            //重定向
                resp.sendRedirect("/userSelectServlet");
        }else {
            //转发
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            System.out.println("你网不好，删除失败！");
        }


    }
}
