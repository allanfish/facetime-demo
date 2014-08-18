<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.springframework.context.*"%>
<%@ page import="org.springframework.web.context.support.*"%>
<%@ page import="com.mchange.v2.c3p0.*"%>
<%@ page import="java.sql.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <%
   ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
   ComboPooledDataSource dataSource=(ComboPooledDataSource) ctx.getBean("dataSource", com.mchange.v2.c3p0.ComboPooledDataSource.class);
   Connection conn = dataSource.getConnection();
   %>
   test,heheheheh
  </body>
</html>
