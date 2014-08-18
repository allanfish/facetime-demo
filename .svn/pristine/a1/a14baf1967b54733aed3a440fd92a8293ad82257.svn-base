<html>
<title>hello for user list</title>
<body>
<table>
<tr>
 <td>usrename</td>
 <td>password</td>
 <td>age</td>
</tr>
<#list userList as user>
	<tr>
		<td>${user.username}</td>
		<td>${user.password}</td>
		<td>
			// ÅĞ¶Ïuser.ageÊÇ·ñ´æÔÚ
			<#if user.age??>
				${user.age}
			<#else>
				unknow age
			</#if>
		</td>
	</tr>
</#list>
<table>
<body>
</html>