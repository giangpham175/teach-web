<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/teacher.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container2 border-color" style="background: url(img/restaurant.png);">
        <div class="login">
            <h2>Login</h2>
            <form action="taikhoan" method="post">
                <input type="hidden" name="actionname" value="login"/>
                <input type="text" name="tentaikhoan" class="user active" placeholder="Tên tài khoản" />
                <input type="password" name="password" class="lock active" placeholder="Password" />
                
                <div class="login-bwn">
                    <input type="submit" value="Sign in" />
                </div>
           </form>
        </div>
    </div>
    </body>
</html>