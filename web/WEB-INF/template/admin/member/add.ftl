<#import "/admin/common/base.ftl" as html/>
<@html.html title="添加会员" bar=message("Admin.menu.member.memberManage") bar_title="添加会员信息">

<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="save.html" method="post" class="form-horizontal">
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
                <span></span>
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>用户名</label>
                        <div class="col-md-4 ">
                            <input type="text" name="username" class="form-control" maxlength="${setting.usernameMaxLength}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" id="password" name="password"  maxlength="${setting.passwordMaxLength}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>确认密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" name="repassword"  maxlength="${setting.passwordMaxLength}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>E-mail</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="email"   maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">昵称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="nickname"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">会员等级</label>
                        <div class="col-md-4">
                            <select name="memberRankId" class="form-control input-medium">
                                <#list memberRanks as memberRank>
                                <option value="${memberRank.id}"<#if memberRank == member.memberRank> selected="selected"</#if>>${memberRank.name}</option>
                                </#list>
                            </select>
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
                username: {
                    required: true,
                    pattern: /^[0-9a-zA-Z_\u4e00-\u9fa5]+$/,
                    minlength: ${setting.usernameMinLength},
                    remote: {
                        url: "check_username.html",
                        cache: false
                    }
                },
                password:{
                    minlength:${setting.passwordMinLength}
                },
                rePassword:{
                    equalTo:"#password"
                },
                email:{
                    required:true,
                    email:true,
                    remote: {
                        url: "check_email.html?previousEmail=${member.email?url}",
                        cache: false
                    }
                }
            },

            invalidHandler: function() {
                $.message("error","您还有选项未正确填写, 请检查.");
            }
        });
    });
</script>
</@html.html>