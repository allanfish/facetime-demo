<html>
<title>hello for user list</title>
<body>
<table>
<tr>
 <td>usrename</td>
 <td>password</td>
 <td>age</td>
</tr>
<#list keySet as userKey>
	<tr>
		<td>${userMap[userKey].username}</td>
		<td>${userMap[userKey].password}</td>
		<td>
			<#if userMap[userKey].age??>
				${userMap[userKey].age}
			<#else>
				unknow age
			</#if>
		</td>
	</tr>
</#list>
<table>
<body>
</html>