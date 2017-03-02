<#import "/admin/common/base.ftl" as html/>
<@html.html title="编辑会员" bar=message("Admin.menu.member.memberManage") bar_title="编辑会员信息">

<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="update.html" method="post" class="form-horizontal">
            <input type="hidden" name="id" value="${member.id}" />
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
                <span></span>
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户名</label>
                        <div class="col-md-4 ">
                            <span class="help-block">${member.username}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" id="password" name="password"  maxlength="${setting.passwordMaxLength}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">确认密码</label>
                        <div class="col-md-4">
                            <input type="password" class="form-control" name="repassword" maxlength="${setting.passwordMaxLength}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>E-mail</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="email" value="${member.email}"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">昵称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="nickname" value="${member.nickname}"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">会员等级</label>
                        <div class="col-md-4">
                            <select name="memberRankId" class="form-control">
                                <#list memberRanks as memberRank>
                                <option value="${memberRank.id}"<#if memberRank == member.memberRank> selected="selected"</#if>>${memberRank.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">是否启用</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isEnabled" data-on-text="是" data-off-text="否"  <#if member.isEnabled> checked="checked"</#if>>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">积分</label>
                        <div class="col-md-4">
                        ${member.point}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">余额</label>
                        <div class="col-md-4">
                        ${currency(member.balance, true)}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">消费金额</label>
                        <div class="col-md-4">
                        ${currency(member.amount, true)}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">创建时间</label>
                        <div class="col-md-4">
                        ${member.createDate?string("yyyy-MM-dd HH:mm:ss")}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">注册IP</label>
                        <div class="col-md-4">
                        ${member.registerIp}
                        </div>
                    </div>

                    <#if member.isLocked>
                        <div class="form-group">
                            <label class="col-md-3 control-label">是否锁定</label>
                            <div class="col-md-4">
                                <input type="checkbox" class="make-switch" name="isLocked" data-on-text="是" data-off-text="否"  <#if member.isLocked> checked="checked"</#if>>
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