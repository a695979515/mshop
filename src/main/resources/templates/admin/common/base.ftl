<#macro html title bar bar_title>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <#include "/admin/common/common_css.ftl"/>
    <#include "/admin/common/common_js.ftl"/>
    <title>MSHOP后台管理系统-${title}</title>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white <#--page-sidebar-fixed-->">
<#--顶部菜单-->
    <#include "/admin/common/header.ftl"/>
<#--内容-->
<div class="page-container">
<#--左边菜单-->
    <#include "/admin/common/navigation.ftl"/>
    <div class="page-content-wrapper">
        <div class="page-content">
        <#-- 面包屑-->
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <a href="${base}/admin/common/main.html">主页</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <span>${bar}</span>
                    </li>
                </ul>
            </div>
            <h3 class="page-title"> ${bar}
                <small>${bar_title}</small>
            </h3>

            <#nested/>
        </div>
    </div>
</div>

    <#include "/admin/common/foot.ftl"/>
</body>
</html>
</#macro>