<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--<%@ page import="cn.com.qst.model.User" %>--%>

    <%@ page import="java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath()%>/"/>
    <link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="resources/bootstrap-3.3.7-dist/js/jquery-1.9.1.min.js"></script>
    <script src="resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>后台首页</title></head>

  <link type="text/css" rel="stylesheet" href="css/style.css">
<body>

    <center>

        <table border="0" cellpadding="0" cellspacing="0" width="650" height="300">
           	<tr><td align="center">用户id</td><td>用户名</td><td>密码</td><td>操作</td></tr>
           	<%--<%
           		int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
           	%>--%>
           	
           	<c:forEach items="${list}" var="u">
                <tr><td align="center">${u.id}</td><td>${u.userName}</td><td>${u.userPassword}</td><td><a  href="selectUserByIdForUpdateServlet?id=${u.id}">修改</a>|<a href="userDeleteServlet?id=${u.id}">删除</a></td></tr>
           	</c:forEach>
           	
           <%--	<tr><td colspan="3" align="center"><a href="userSelectServlet?currentPage=<%=currentPage-1%>">上一页</a>
           		|<a href="userSelectServlet?currentPage=<%=currentPage+1%>">下一页</a></td>
           	</tr>--%>
        </table>
    </center>
</body>
</html>