<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>index</title>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/admin/jquery/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>


    <script type="text/javascript">
        $().ready(function(){
            var $loginForm = $("#loginFrom");
            var $enPassword = $("#enPassword");
            var $username = $("#username");
            var $password = $("#password");
            var $captcha = $("#captcha");

            $loginForm.submit(function(){
                $enPassword.val($password.val());
            });

        });
    </script>
</head>
<body>
<form id="loginFrom" action="login.html" method="post">
    <input type="hidden" name="enPassword" id="enPassword" value="">
    账户：<input type="text" name="username" id="username" value="">
    <br>
    密码：<input type="password" name="password" id="password" value="">
    <br>
    验证码：<input type="text" name="captcha" id="captcha" value="" maxlength="4" autocomplete="off">
    <input type="hidden" name="captchaId" value="1234"/>
    <br>
    <input type="submit" value="提交">
</form>

</body>
</html>