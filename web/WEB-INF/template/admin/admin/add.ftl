<#import "/admin/common/base.ftl" as html/>
<@html.html title="添加管理员" bar=message("Admin.menu.system.admin") bar_title="添加管理员">
<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="save.html" method="post" class="form-horizontal">
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>用户名</label>
                        <div class="col-md-4 ">
                            <span class="help-block">${admin.username}</span>
                            <input type="text" class="form-control"  name="username"  maxlength="20" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" id="password" name="password"  maxlength="20" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>确认密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" name="rePassword"  maxlength="20" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>E-mail</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="email"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>角色</label>
                        <div class="col-md-4">
                            <div class="mt-checkbox-inline">
                                <#list roles as role>
                                    <label class="mt-checkbox mt-checkbox-outline">
                                        <input type="checkbox" class="" name="roleIds" value="${role.id}"/>${role.name}
                                        <span></span>
                                    </label>
                                </#list>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">姓名</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="name"   maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">部门</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="department"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">是否启用</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isEnabled" data-on-text="是" data-off-text="否"  checked="checked">
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
                                <input type="submit" class="btn green" value="确定">
                                <input type="button" class="btn btn-outline grey-salsa" value="返回" onclick="history.back(); return false;">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $().ready(function(){
        var $inputForm = $("#inputForm");
        $inputForm.validate({
            rules:{
                username:{
                    required:true,
                    pattern:/^[0-9a-zA-Z_\u4e00-\u9fa5]+$/,
                    minlength:2,
                    remote:{
                        url:"check_username.html",
                        cache:false
                    }
                },
                password:{
                    required:true,
                    minlength:4
                },
                rePassword:{
                    required:true,
                    equalTo:"#password"
                },
                email:{
                    required:true,
                    email:true
                },
                roleIds:"required"
            },
            message:{
                username:{
                    pattern:"不合法字符",
                    remote:"用户名已存在"
                }
            },
            invalidHandler: function() {
                $.message("error","您还有选项未正确填写, 请检查.");
            }
        });
    });
</script>
</@html.html>