<%--
  Created by IntelliJ IDEA.
  User: Panfuhao
  Date: 2016/10/12
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="net.mshop.util.SpringUtils" %>
<%@page import="net.mshop.service.AdminService" %>
<%@page import="net.mshop.util.WebUtils" %>
<%@page import="net.mshop.entity.Admin" %>
<%
    String base = request.getContextPath();
    ApplicationContext applicationContext = SpringUtils.getApplicationContext();
    if (applicationContext != null) {
        AdminService adminService = SpringUtils.getBean("adminServiceImpl", AdminService.class);
        WebUtils.addCookie(request, response, Admin.LOGIN_TOKEN_COOKIE_NAME, adminService.getLoginToken());
        response.sendRedirect(base + "/admin/login.html");
        return;
    }
%>

