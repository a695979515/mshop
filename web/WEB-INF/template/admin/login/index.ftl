<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>index</title>
</head>
<body>
<form id="loginFrom" action="login" method="post">
    账户：<input type="text" name="username" value="">
    <br>
    密码：<input type="password" name="password" value="">
    <br>
    验证码：<input type="text" name="captcha" value="" maxlength="4" autocomplete="off">
    <input type="hidden" name="captchaId" value="1234"/>
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>