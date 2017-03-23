<#import "/admin/common/base.ftl" as html/>
<@html.html title="添加配送方式" bar=message("Admin.menu.system.shipMethod") bar_title="添加配送方式信息">
<style>
    .filePicker input[type=file] {
        display: none;
    }
</style>
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
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>名称</label>
                        <div class="col-md-4 ">
                            <input type="text" class="form-control"  name="name"  maxlength="20" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">默认物流公司</label>
                        <div class="col-md-4">
                            <select class="form-control" name="defaultDeliveryCorpId">
                                <#list deliveryCorps as deliveryCorp>
                                <option value="${deliveryCorp.id}">${deliveryCorp.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>首重量</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="defaultFirstPrice"  maxlength="20" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>续重量</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="defaultContinuePrice"  maxlength="20" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">图标</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control input-inline " name="icon"  maxlength="200">
                            <a class="btn default btn-file filePicker">选择文件</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">介绍</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="description"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">排序</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="order"  maxlength="20">
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
        var $filePicker = $("#filePicker");

        <@flash_message />

        $filePicker.uploader();
        $inputForm.validate({
            rules:{
                name: "required",
                firstWeight: {
                    required: true,
                    digits: true
                },
                continueWeight: {
                    required: true,
                    integer: true,
                    min: 1
                },
                defaultFirstPrice: {
                    required: true,
                    min: 0,
                    decimal: {
                        integer: 12,
                        fraction: ${setting.priceScale}
                    }
                },
                defaultContinuePrice: {
                    required: true,
                    min: 0,
                    decimal: {
                        integer: 12,
                        fraction: ${setting.priceScale}
                    }
                },
                icon: {
                    pattern: /^(http:\/\/|https:\/\/|\/).*$/i
                },
                order: "digits"
            },

            invalidHandler: function() {
                $.message("error","您还有选项未正确填写, 请检查.");
            }
        });
    });
</script>
</@html.html>