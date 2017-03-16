<#import "/admin/common/base.ftl" as html/>
<@html.html title="编辑物流公司" bar=message("Admin.menu.system.logisticsCompany") bar_title="编辑物流公司信息">
<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="update.html" method="post" class="form-horizontal">
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
                <span></span>
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>名称</label>
                        <div class="col-md-4 ">
                            <input type="text" class="form-control"  name="name"  maxlength="20" value="${deliveryCorp.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">网址</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control"name="url"  maxlength="200" value="${deliveryCorp.url}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">代码</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="code"  maxlength="20" value="${deliveryCorp.code}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">排序</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="order" value="${deliveryCorp.order}"  maxlength="20">
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
                name:"required",
                url:{
                    pattern: /^(http:\/\/|https:\/\/|ftp:\/\/|mailto:|\/|#).*$/i
                },
                order:"digits"
            },
            message:{
                url:{
                    pattern:"不合法字符"
                }
            },
            invalidHandler: function() {
                $.message("error","您还有选项未正确填写, 请检查.");
            }
        });
    });
</script>
</@html.html>