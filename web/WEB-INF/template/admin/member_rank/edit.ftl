<#import "/admin/common/base.ftl" as html/>
<@html.html title="编辑会员等级" bar=message("Admin.menu.member.memberLevel") bar_title="编辑会员等级信息">

<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="update.html" method="post" class="form-horizontal">
            <input type="hidden" name="id" value="${memberRank.id}" />
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
                <span></span>
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>等级名称</label>
                        <div class="col-md-4 ">
                            <input type="text" name="name" class="form-control" maxlength="200" value="${memberRank.name}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>优惠比列</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="scale"  value="${memberRank.scale}" maxlength="7" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>消费金额</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="amount" name="amount" value="${memberRank.amount}"  maxlength="16" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">是否默认</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isDefault" data-on-text="是" data-off-text="否" <#if memberRank.isDefault>checked="checked"</#if>>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">是否特殊</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isSpecial" data-on-text="是" data-off-text="否" <#if memberRank.isSpecial>checked="checked"</#if>>
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

            },

            invalidHandler: function() {
                $.message("error","您还有选项未正确填写, 请检查.");
            }
        });
    });
</script>
</@html.html>