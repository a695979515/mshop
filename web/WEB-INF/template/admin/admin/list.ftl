<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.system.admin") bar=message("Admin.menu.system.admin") bar_title="添加、修改管理员">

<div class="row">
    <div class="col-md-12">
        <div class="btn-group">
            <a class="btn btn-default btn-outline" href="add.html">
                <i class="fa fa-plus"></i> 添加
            </a>
            <button type="button" id="deleteButton" class="btn btn-default btn-outline">
                <i class="fa fa-trash"></i> 删除
            </button>
            <button type="button" id="refreshButton" class="btn btn-default btn-outline">
                <i class="fa fa-refresh"></i> 刷新
            </button>
        </div>
        <div class="table-scrollable">
            <table class="table table-hover table-light">
                <thead>
                <tr>
                    <th>
                        <label class="mt-checkbox mt-checkbox-outline">
                            <input type="checkbox" id="selectAll"/>
                            <span></span>
                        </label>
                    </th>
                    <th> 用户名</th>
                    <th> 姓名</th>
                    <th> 部门</th>
                    <th> E-mail</th>
                    <th> 最后登录日期</th>
                    <th> 最后登录IP</th>
                    <th> 帐号状态</th>
                    <th> 创建日期</th>
                    <th> 操作</th>
                </tr>
                </thead>
                <tbody>
                    <#list page.content as admin>
                    <tr>
                        <td>
                            <label class="mt-checkbox mt-checkbox-outline">
                                <input type="checkbox" name="ids" value="${admin.id}"/>
                                <span></span>
                            </label>
                        </td>
                        <td> ${admin.username}</td>
                        <td> ${admin.name}</td>
                        <td> ${admin.department}</td>
                        <td> ${admin.email}</td>
                        <td>
                            <#if admin.loginDate??>
                                <span title="${admin.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.loginDate}</span>
                            <#else>
                                -
                            </#if>
                        </td>
                        <td> ${admin.loginIp}</td>
                        <td>
                            <#if !admin.isEnabled>
                                <span class="label label-sm label-danger"> 禁用 </span>
                            <#elseif admin.isLocked>
                                <span class="label label-sm label-danger"> 锁定 </span>
                            <#else>
                                <span class="label label-sm label-success"> 正常 </span>
                            </#if>
                        </td>
                        <td><span title="${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.createDate}</span>
                        </td>
                        <td>
                            <a href="edit.html?id=${admin.id}" class="btn btn-sm ">
                                <i class="fa fa-edit"></i>
                                编辑
                            </a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>



</@html.html>