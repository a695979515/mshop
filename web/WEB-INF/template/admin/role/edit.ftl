<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.system.role") bar=message("Admin.menu.system.role") bar_title="编辑角色权限信息">
<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="save.html" method="post" class="form-horizontal">
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
            </div>
            <div class="portlet light portlet-fit bordered">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>名称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="name" value="${role.name}"  maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">描述</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="description" value="${role.description}"  maxlength="200">
                        </div>
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
        </form>
    </div>
</div>