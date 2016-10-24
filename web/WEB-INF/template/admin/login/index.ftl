<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <title>index</title>
    <link href="${base}/resources/admin/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/admin/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/admin/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/admin/css/plugins.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/admin/css/login.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base}/resources/admin/jquery/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/jsbn.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/prng4.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/rng.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/rsa.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/base64.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>


    <script type="text/javascript">
        $().ready(function () {
            var $loginForm = $("#loginFrom");
            var $enPassword = $("#enPassword");
            var $username = $("#username");
            var $password = $("#password");
            var $captcha = $("#captcha");
            var $captchaImage = $("#captchaImage");
            var $close = $("#close");
            var $failureMessage = $("#failureMessage");
            var $forgetPassword = $("#forget-password");
            var $backBtn = $("#back-btn");
            var $isRememberUsername = $("#isRememberUsername");
            //登录失败显示消息
        <#if failureMessage??>
            $("#alert").attr("style", "display:block");
            $failureMessage.text("${failureMessage.content}");
        </#if>

            //cookie存在不存在
            if(getCookie("adminUsername")!=null){
                $isRememberUsername.prop("checked",true);
                $username.val(getCookie("adminUsername"));
                $password.focus();
            }else{
                $isRememberUsername.prop("checked",false);
                $username.focus();
            }

            //消息框点击消失
            $close.click(function () {
                $("#alert").removeAttr("style");
            });
            //忘记密码
            $forgetPassword.click(function () {
                $(".login-form").attr("style", "display:none");
                $(".forget-form").attr("style", "display:block");
            });
            //返回按钮
            $backBtn.click(function () {
                $(".login-form").attr("style", "display:block");
                $(".forget-form").attr("style", "display:none");
            });
            /**
             * 点击验证码图片
             */
            $captchaImage.click(function () {
                $captchaImage.attr("src", "common/captcha.html?captchaId=${captchaId}&timestamp = " + new Date().getTime());
            });

            $loginForm.submit(function () {
                if($username.val()==""){
                    $("#alert").attr("style", "display:block");
                    $failureMessage.text("请输入您的用户名");
                    return false;
                }
                if($password.val()==""){
                    $("#alert").attr("style", "display:block");
                    $failureMessage.text("请输入您的密码");
                    return false;
                }
                if($captcha.val()==""){
                    $("#alert").attr("style", "display:block");
                    $failureMessage.text("请输入您的验证码");
                    return false;
                }
                if($isRememberUsername.prop("checked")){
                    addCookie("adminUsername",$username.val(),{expires:7*24*60*60});
                }else{
                    removeCookie("adminUsername")
                }

                var rsaKey = new RSAKey();
                rsaKey.setPublic(b64tohex("${modulus}"), b64tohex("${exponent}"));
                var enPassword = hex2b64(rsaKey.encrypt($password.val()));
                $enPassword.val(enPassword);
            });

        });
    </script>
</head>
<body class="login">
<h2 class="logo"><h2 style="color:#fff;text-align: center;">MSHOP</span></h2>
    <div class="content">
        <form id="loginFrom" action="login.html" method="post" class="login-form">
            <div class="form-title">
                <span class="form-title">欢迎.</span>
                <span class="form-subtitle">请登录.</span>
            </div>

            <div class="alert alert-danger display-hide" id="alert">
                <span class="close" id="close"></span>
                <span id="failureMessage"></span>
            </div>

            <input type="hidden" name="enPassword" id="enPassword" value="">
            <input type="hidden" name="captchaId" value="${captchaId}"/>

            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">用户名</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off"
                       placeholder="用户名" name="username" id="username"/>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
                       placeholder="密码" id="password"/></div>
            <div class="form-inline margin-bottom-15">
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">验证码</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text"
                           autocomplete="off" maxlength="4"
                           placeholder="验证码" name="captcha" id="captcha"/>
                </div>
                <div class="form-group" style="float: right;">
                    <img id="captchaImage" style="float: right;" src="common/captcha.html?captchaId=${captchaId}"
                         title="点击更换验证码"/>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn red btn-block uppercase">登 录</button>
            </div>

            <div class="form-actions">
                <div class="pull-left">
                    <label class="rememberme mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="isRememberUsername" id="isRememberUsername"/> 记住用户名
                        <span></span>
                    </label>
                </div>
                <div class="pull-right forget-password-block">
                    <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
                </div>
            </div>
        </form>

        <form class="forget-form" action="${base}/admin/common/forget.html" method="post">
            <div class="form-title">
                <span class="form-title">忘记密码?</span>
                <span class="form-subtitle">发送邮件重置密码.</span>
            </div>
            <div class="form-group">
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email"
                       name="email"/></div>
            <div class="form-actions">
                <button type="button" id="back-btn" class="btn btn-default">返回</button>
                <button type="submit" class="btn btn-primary uppercase pull-right">发送邮件</button>
            </div>
        </form>
    </div>
</body>
</html>