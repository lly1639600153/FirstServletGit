package dao;
/*
操作数据层
*/

import pojo.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //封装一个方法根据管理员名字查询管理员的方法
    public User selectAdminInfo(String name){
        Connection conn = DBUtil.getConn();
        String sql = "select * from tb_user where USER_NAME=?";
        User user = new User();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                //取出数据的每个字段值
                Integer id=rs.getInt("ID");
                String userName = rs.getString("USER_NAME");
                String pwd = rs.getString("USER_PASSWORD");
                Integer userType = rs.getInt("USER_TYPE");
                Integer userState = rs.getInt("USER_STATE");

                //将去除的数据封装到对象
                user= new User();
                user.setId(id);
                user.setUserName(userName);
                user.setUserPassword(pwd);
                user.setUserType(userType);
                user.setUserState(userState);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;

    }

    //查询用户信息
    public List<User> userSelect(String id) throws SQLException {
        Connection conn = DBUtil.getConn();
        List<User> list=new ArrayList<>();
        String sql="";
        User user = new User();
        if (id==null||id.equals("")){
            sql="select * from tb_user ";

        }else {
            sql="select * from tb_user where id="+id;
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        //执行
        ResultSet rs = ps.executeQuery();
        //遍历结果集
        while (rs.next()){
            //取出数据的每个字段值
            Integer id1=rs.getInt("ID");
            String userName = rs.getString("USER_NAME");
            String pwd = rs.getString("USER_PASSWORD");
            Integer userType = rs.getInt("USER_TYPE");
            Integer userState = rs.getInt("USER_STATE");

            //将去除的数据封装到对象
            user= new User();
            user.setId(id1);
            user.setUserName(userName);
            user.setUserPassword(pwd);
            user.setUserType(userType);
            user.setUserState(userState);
            list.add(user);
        }
        return list;
    }

    //根据id删除用户
    public int deleteUserInfo(String id){
        int row=0;
        //获取连接对象
        Connection conn = DBUtil.getConn( );
        //编写sql
        String sql = "delete from tb_user where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            //执行
             row = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //根据id查询用户信息
    public User SelectUserInfoById(String id){
        Connection conn = DBUtil.getConn();
        User user = new User();
        String sql= "select * from tb_user where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Integer id1=rs.getInt("ID");
                String userName = rs.getString("USER_NAME");
                String pwd = rs.getString("USER_PASSWORD");
                Integer userType = rs.getInt("USER_TYPE");
                Integer userState = rs.getInt("USER_STATE");

                //将去除的数据封装到对象
                user= new User();
                user.setId(id1);
                user.setUserName(userName);
                user.setUserPassword(pwd);
                user.setUserType(userType);
                user.setUserState(userState);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return user;
    }

    //根据id修改用户信息
    public int updateUserInfoById(User user){
        int row=0;
        Connection conn = DBUtil.getConn();
        String sql="update tb_user set user_password=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUserPassword());
            ps.setInt(2,user.getId());
            row=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }
}
