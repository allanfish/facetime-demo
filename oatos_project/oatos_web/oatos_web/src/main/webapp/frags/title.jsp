<%@ page contentType ="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name ="keywords" content="oatOS,燕麦,OS">
<meta name="description" content="oatOS,燕麦,OS">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<title>OATOS</title>
<script type='text/javascript'>
if(util.isNet()){
	document.write("<link rel='icon' href='images/favicon2.ico?t=" + oatOSVersion + "' type='image/x-icon' \/>");
}else{
	document.write("<link rel='icon' href='images/favicon.ico?t=" + oatOSVersion + "' type='image/x-icon' \/>");
}
</script>
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<c:set var="loc" value="zh_CN"/>
<c:if test="${!(empty param.locale)}">
  <c:set var="loc" value="${param.locale}"/>
</c:if>
<fmt:setLocale value="${loc}" />