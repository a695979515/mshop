<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>index</title>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/admin/jquery/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/jsbn.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/prng4.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/rng.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/rsa.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/base64.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>


    <script type="text/javascript">
        $().ready(function(){
            var $loginForm = $("#loginFrom");
            var $enPassword = $("#enPassword");
            var $username = $("#username");
            var $password = $("#password");
            var $captcha = $("#captcha");
            var $captchaImage = $("#captchaImage");
           /* $captchaImage.click(function(){
                $captchaImage.attr("src","common/captcha.html/captchaId=${captchaId}&timestamp="+new Date().getTime());
            });*/

            $loginForm.submit(function(){
                var rsaKey = new RSAKey();
                rsaKey.setPublic(b64tohex("${modulus}"),b64tohex("${exponent}"));
                var enPassword = hex2b64(rsaKey.encrypt($password.val()));
                $enPassword.val(enPassword);
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

    <img id="captchaImage" class="captchaImage" src="common/captcha.html?captchaId=${captchaId}" title="${message("admin.captcha.imageTitle")}" />

    <input type="hidden" name="captchaId" value="${captchaId}"/>
    <br>
    <input type="checkbox" id="isRememberUsername" value="true" />记住用户名
    <input type="submit" value="提交">
</form>

</body>
</html>