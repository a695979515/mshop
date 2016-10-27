<#import "/admin/common/base.ftl" as html/>
<@html.html title="编辑管理员" bar=message("Admin.menu.system.admin") bar_title="编辑管理员信息">

<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="update.html" method="post" class="form-horizontal">
            <input type="hidden" name="id" value="${admin.id}" />
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button> 您还有选项未正确填写, 请检查.
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户名</label>
                        <div class="col-md-4 ">
                            <span class="help-block">${admin.username}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" id="password" name="password"  maxlength="20" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">确认密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" name="repassword"  maxlength="20" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>E-mail</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="email" value="${admin.email}"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>角色</label>
                        <div class="col-md-4">
                            <div class="mt-checkbox-inline">
                            <#list roles as role>
                                <label class="mt-checkbox mt-checkbox-outline">
                                    <input type="checkbox" class="" name="roleIds" value="${role.id}"<#if admin.roles?seq_contains(role)> checked="checked"</#if> />${role.name}
                                    <span></span>
                                </label>
                            </#list>
                                </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">姓名</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="name" value="${admin.name}"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">部门</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="department" value="${admin.department}"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">是否启用</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isEnabled" data-on-text="是" data-off-text="否"  <#if admin.isEnabled> checked="checked"</#if>>
                        </div>
                    </div>
                    <#if admin.isLock>
                        <div class="form-group">
                            <label class="col-md-3 control-label">是否锁定</label>
                            <div class="col-md-4">
                                <input type="checkbox" class="make-switch" name="isLock" data-on-text="是" data-off-text="否"  <#if admin.isLock> checked="checked"</#if>>
                            </div>
                        </div>
                    </#if>
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
                password:{
                    minlength:4
                },
                rePassword:{
                    equalTo:"#password"
                },
                email:{
                    required:true,
                    email:true
                },
                roleIds:"required"
            },

            invalidHandler: function() {
                $(".alert-danger").show();
                $(".alert-danger").delay(3000).hide(0);
            }
        });
    });
</script>
</@html.html>