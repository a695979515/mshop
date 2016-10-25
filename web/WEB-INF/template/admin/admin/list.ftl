<#import "/admin/common/base.ftl" as html/>
<@html.html title="管理员" bar="管理员" bar_title="添加、修改管理员">

<#list page.content as admin>
    ${admin.username}
</#list>
</@html.html>