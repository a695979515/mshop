<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.member.memberManage") bar=message("Admin.menu.member.memberManage") bar_title="查看会员列表">
<script src="${base}/resources/admin/js/list.js" type="text/javascript"></script>
<script type="text/javascript">
    $().ready(function(){
        <@flash_message />
    });
</script>
<div class="row">
    <div class="col-md-12">

        <form id="listForm" action="list.html" method="get">
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
                <span></span>
            </div>
            <div class="btn-group">
                <a class="btn btn-default btn-outline" href="add.html">
                    <i class="fa fa-plus"></i> 添加
                </a>
                <button type="button" id="deleteButton" class="btn btn-default btn-outline" disabled="disabled">
                    <i class="fa fa-trash"></i> 删除
                </button>
                <div class="btn-group ">
                    <button type="button" class="btn btn-default">每页显示</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-menu" role="menu" id="pageSizeMenu">
                        <li <#if page.pageSize == 10 >class="active"</#if>>
                            <a href="javascript:;"> 10 </a>
                        </li>
                        <li <#if page.pageSize == 20 >class="active"</#if>>
                            <a href="javascript:;"> 20 </a>
                        </li>
                        <li <#if page.pageSize == 50 >class="active"</#if>>
                            <a href="javascript:;"> 50 </a>
                        </li>
                        <li <#if page.pageSize == 100 >class="active"</#if>>
                            <a href="javascript:;"> 100 </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="btn-group" style="float:right;">
                <div class="input-group input-medium">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">&nbsp;<span class="caret"></span></button>
                        <ul class="dropdown-menu" id="searchPropertyOption">
                            <li val="username" <#if page.searchProperty == "username"> class="active"</#if>>
                                <a href="javascript:;">用户名</a>
                            </li>
                            <li val="email" <#if page.searchProperty == "email"> class="active"</#if>>
                                <a href="javascript:;">邮件</a>
                            </li>
                            <li val="name" <#if page.searchProperty == "nickname"> class="active"</#if>>
                                <a href="javascript:;">昵称</a>
                            </li>
                        </ul>
                    </div><!-- /btn-group -->
                    <input type="text" class="form-control" id="searchValue" name="searchValue" placeholder="选择条件搜索"
                           value="${page.searchValue}" maxlength="200"/>
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-default " id="searchPropertySelect">&nbsp;<span
                                class="glyphicon glyphicon-search"></span>&nbsp;</button>

                    </div><!-- /btn-group -->
                </div><!-- /input-group -->
            </div>
            <div class="table-scrollable">
                <table class="table table-hover table-light ">
                    <thead>
                    <tr>
                        <th>
                            <label class="mt-checkbox mt-checkbox-outline">
                                <input type="checkbox" id="selectAll"/>
                                <span></span>
                            </label>
                        </th>
                        <th> 用户名</th>
                        <th> 会员等级</th>
                        <th> 昵称</th>
                        <th> E-mail</th>
                        <th> 创建日期</th>
                        <th> 帐号状态</th>
                        <th> 操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list page.content as member>
                        <tr>
                            <td>
                                <label class="mt-checkbox mt-checkbox-outline">
                                    <input type="checkbox" name="ids" value="${member.id}"/>
                                    <span></span>
                                </label>
                            </td>
                            <td> ${member.username}</td>
                            <td> ${member.memberRank.name}</td>
                            <td> ${member.nickname}</td>
                            <td> ${member.email}</td>

                            <td><span
                                    title="${member.createDate?string("yyyy-MM-dd HH:mm:ss")}">${member.createDate}</span>
                            </td>
                            <td>
                                <#if !member.isEnabled>
                                    <span class="label label-sm label-danger"> 禁用 </span>
                                <#elseif member.isLocked>
                                    <span class="label label-sm label-danger"> 锁定 </span>
                                <#else>
                                    <span class="label label-sm label-success"> 正常 </span>
                                </#if>
                            </td>
                            <td>
                                <a href="view.html?id=${member.id}" class="btn btn-sm ">
                                    <i class="fa fa-eye"></i>
                                    查看
                                </a>
                                <a href="edit.html?id=${member.id}" class="btn btn-sm ">
                                    <i class="fa fa-edit"></i>
                                    编辑
                                </a>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
            <@pagination pageNumber = page.pageNumber totalPages = page.totalPages>
                <#include "/admin/common/pagination.ftl"/>
            </@pagination>
        </form>
    </div>
</div>



</@html.html>