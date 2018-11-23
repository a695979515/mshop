<#import "/admin/common/base.ftl" as html/>
<@html.html title="添加会员等级" bar=message("Admin.menu.member.memberLevel") bar_title="添加会员等级信息">

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
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>等级名称</label>
                        <div class="col-md-4 ">
                            <input type="text" name="name" class="form-control" maxlength="200"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>优惠比列</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="scale" value="1" maxlength="7"
                                   autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>消费金额</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="amount" name="amount" maxlength="16"
                                   autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">是否默认</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isDefault" data-on-text="是"
                                   data-off-text="否">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">是否特殊</label>
                        <div class="col-md-4">
                            <input type="checkbox" class="make-switch" name="isSpecial" id="isSpecial" data-on-text="是"
                                   data-off-text="否">
                        </div>
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
                                <input type="submit" class="btn green" value="确定">
                                <input type="button" class="btn btn-outline grey-salsa" value="返回"
                                       onclick="history.back(); return false;">
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $().ready(function () {
        var $inputForm = $("#inputForm");
        var $amount = $("#amount");
        var $isSpecial = $("#isSpecial");

        <@flash_message />

        // 是否特殊
        $isSpecial.click(function() {
            if ($(this).prop("checked")) {
                $amount.val("").prop("disabled", true);
            } else {
                $amount.prop("disabled", false);
            }
        });
        $inputForm.validate({
            rules: {
                name: {
                    required: true,
                    remote: {
                        url: "check_name.html",
                        cache: false
                    }
                },
                scale: {
                    required: true,
                    min: 0,
                    decimal: {
                        integer: 3,
                        fraction: 3
                    }
                },
                amount: {
                    required: true,
                    min: 0,
                    decimal: {
                        integer: 12,
                        fraction: ${setting.priceScale}
                    },
                    remote: {
                        url: "check_amount.html",
                        cache: false
                    }
                }
            },
            messages: {
                name: {
                    remote: "已存在"
                },
                amount: {
                    remote: "已存在"
                }
            },

            invalidHandler: function () {
                $.message("error", "您还有选项未正确填写, 请检查.");
            }
        });
    });
</script>
</@html.html>