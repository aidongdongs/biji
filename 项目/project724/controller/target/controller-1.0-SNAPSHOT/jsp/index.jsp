 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>hello,SpringMVC!</h1>
 <h1>realName(key:string) --> ${string}</h1>
 <h1>realName(key:realName) --> ${realName}</h1>
 <h1>realName(key:currentUser) --> ${currentUser.realName}</h1>
 <h1>realName(key:user) --> ${user.realName}</h1>
</body>
</html>