<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.system.systemSetting") bar=message("Admin.menu.system.systemSetting") bar_title="系统、商城基础配置">
<style>
    .filePicker input[type=file] {
        display: none;
    }

</style>
<script type="text/javascript">
    $().ready(function(){
        <@flash_message />
    });
</script>
<div class="row">
                <div class="col-md-12">
                    <form id="inputForm" action="update.html" method="post" class="form-horizontal">
                        <div id="flashMessage" class="alert display-hide">
                            <button class="close" data-close="alert"></button>
                            <span></span>
                        </div>
                        <div class="portlet light portlet-fit bordered">
                            <div class="portlet-body">
                                <div class="tabbable-line boxless margin-bottom-20">
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#tab_1" data-toggle="tab"> 基本设置 </a>
                                        </li>
                                        <li>
                                            <a href="#tab_2" data-toggle="tab"> 图片设置 </a>
                                        </li>
                                        <li>
                                            <a href="#tab_3" data-toggle="tab"> 注册设置 </a>
                                        </li>
                                        <li>
                                            <a href="#tab_4" data-toggle="tab"> 邮件设置 </a>
                                        </li>
                                        <li>
                                            <a href="#tab_5" data-toggle="tab"> 其他设置 </a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">


                                        <div class="tab-pane active" id="tab_1">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>网站名称</label>
                                                <div class="col-md-4 ">
                                                    <input type="text" class="form-control" name="siteName" value="${setting.siteName}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>网站链接</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="siteUrl" value="${setting.siteUrl}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>网站LOGO</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline input-medium" name="logo" value="${setting.logo}" maxlength="200">
                                                    <a class="btn default btn-file filePicker">选择文件</a>
                                                    <a href="${setting.logo}" target="_blank">查看</a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">热门搜索</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="hotSearch" value="${setting.hotSearch}" maxlength="200">
                                                    <span class="help-block">多个内容以(,)分隔</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">联系地址</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="address" value="${setting.address}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">联系电话</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="phone" value="${setting.phone}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">邮政编码</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="zipCode" value="${setting.zipCode}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">E-mail</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="email" value="${setting.email}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">备案编号</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="recordNumber" value="${setting.recordNumber}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否开启网站</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="siteEnabled" data-on-text="是" data-off-text="否" <#if setting.siteEnabled> checked="checked"</#if>>                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>网站关闭信息</label>
                                                <div class="col-md-4">
                                                    <textarea class="form-control" rows="8" name="closeMessage" style="resize: none;">${setting.closeMessage}</textarea>                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane" id="tab_2">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>商品大图(宽*高)</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline input-xsmall" name="largeProductImageWidth" value="${setting.largeProductImageWidth}" maxlength="9" title="宽度：单位像素">
                                                     x&nbsp;
                                                    <input type="text" class="form-control input-inline input-xsmall" name="largeProductImageHeight" value="${setting.largeProductImageHeight}" maxlength="9" title="高度：单位像素">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>商品中图(宽*高)</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline input-xsmall" name="mediumProductImageWidth" value="${setting.mediumProductImageWidth}" maxlength="9" title="宽度：单位像素">
                                                     x&nbsp;
                                                    <input type="text" class="form-control input-inline input-xsmall" name="mediumProductImageHeight" value="${setting.mediumProductImageHeight}" maxlength="9" title="高度：单位像素">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>商品缩略图(宽*高)</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline input-xsmall" name="thumbnailProductImageWidth" value="${setting.thumbnailProductImageWidth}" maxlength="9" title="宽度：单位像素">
                                                     x&nbsp;
                                                    <input type="text" class="form-control input-inline input-xsmall" name="thumbnailProductImageHeight" value="${setting.thumbnailProductImageHeight}" maxlength="9" title="高度：单位像素">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>默认商品大图</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline " name="defaultLargeProductImage" value="${setting.defaultLargeProductImage}" maxlength="200">
                                                    <a class="btn default btn-file filePicker">选择文件</a>
                                                    <a href="${setting.logo}" target="_blank">查看</a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>默认商品中图</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline " name="defaultMediumProductImage" value="${setting.defaultMediumProductImage}" maxlength="200">
                                                    <a class="btn default btn-file filePicker">选择文件</a>
                                                    <a href="${setting.logo}" target="_blank">查看</a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>默认商品缩略图</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline " name="defaultThumbnailProductImage" value="${setting.defaultThumbnailProductImage}" maxlength="200">
                                                    <a class="btn default btn-file filePicker">选择文件</a>
                                                    <a href="${setting.logo}" target="_blank">查看</a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>水印透明度</label>
                                                <div class="col-md-4">
                                                    <div class="input-group input-small">
                                                        <input type="number" class="form-control " name="watermarkAlpha" value="${setting.watermarkAlpha}" maxlength="9" min="0" max="100">
                                                        <span class="input-group-addon">%</span>
                                                    </div>
                                                    <span class="help-block">取值范围: 0-100, 0代表完全透明</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">水印图片</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline " name="watermarkImage" value="${setting.watermarkImage}" maxlength="200">
                                                    <a class="btn default btn-file filePicker">选择文件</a>
                                                    <a href="${setting.logo}" target="_blank">查看</a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">水印位置</label>
                                                <div class="col-md-4">
                                                    <select name="watermarkPosition" class="form-control input-medium">
                                                        <#list watermarkPositions as watermarkPosition>
                                                        <option value="${watermarkPosition}"<#if watermarkPosition == setting.watermarkPosition> selected="selected"</#if>>${message("Setting.WatermarkPosition."+watermarkPosition)}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="tab-pane" id="tab_3">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否开放注册</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="registerEnabled" data-on-text="是" data-off-text="否"  <#if setting.registerEnabled> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">禁止注册用户名</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" name="disabledUsername" value="${setting.disabledUsername}" maxlength="200">
                                                    <span class="help-block">多个用户名以(,)分隔</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>用户名最小长度</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" id="usernameMinLength" name="usernameMinLength" value="${setting.usernameMinLength}" maxlength="3">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>用户名最大长度</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="usernameMaxLength" value="${setting.usernameMaxLength}" maxlength="3">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>密码最小长度</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" id="passwordMinLength" name="passwordMinLength" value="${setting.passwordMinLength}" maxlength="3">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>密码最大长度</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="passwordMaxLength" value="${setting.passwordMaxLength}" maxlength="3">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>注册初始积分</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="registerPoint" value="${setting.registerPoint}" maxlength="9">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>注册协议</label>
                                                <div class="col-md-4">
                                                    <textarea class="form-control" rows="8" name="registerAgreement" style="resize: none;">${setting.registerAgreement}</textarea>
                                                </div>
                                            </div>

                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">验证码类型</label>
                                                    <div class="col-md-4">
                                                        <div class="mt-checkbox-inline">
                                                            <#list captchaTypes as captchaType>
                                                                <label class="mt-checkbox mt-checkbox-outline">
                                                                    <input type="checkbox" name="captchaTypes" value="${captchaType}"<#if setting.captchaTypes?? && setting.captchaTypes?seq_contains(captchaType)> checked="checked"</#if>>${message("Setting.CaptchaType." + captchaType)}
                                                                    <span></span>
                                                                </label>
                                                            </#list>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">账户锁定</label>
                                                    <div class="col-md-4">
                                                        <div class="mt-checkbox-inline">
                                                        <#list accountLockTypes as accountLockType>
                                                            <label class="mt-checkbox mt-checkbox-outline">
                                                                <input type="checkbox" name="accountLockTypes" value="${accountLockType}"<#if setting.accountLockTypes?? && setting.accountLockTypes?seq_contains(accountLockType)> checked="checked"</#if>>${message("Setting.AccountLockType." + accountLockType)}
                                                                <span></span>
                                                            </label>
                                                        </#list>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>连续登录失败最大次数</label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control input-medium" name="failureLoginCount" value="${setting.failureLoginCount}" maxlength="9">
                                                        <span class="help-block">当连续登录失败次数超过设定值时，系统将自动锁定该账号</span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>自动解锁时间</label>
                                                    <div class="col-md-4">
                                                        <input type="text" class="form-control input-medium" name="autoUnlockTime" value="${setting.autoUnlockTime}" maxlength="9">
                                                        <span class="help-block">账号锁定后，自动解除锁定的时间，单位: 分钟，0表示永久锁定</span>
                                                    </div>
                                                </div>

                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>安全密钥有效时间</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="safeKeyExpiryTime" value="${setting.safeKeyExpiryTime}" maxlength="9">
                                                    <span class="help-block">找回密码时，安全密匙的有效时间，单位: 分钟，0表示永久有效</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>上传文件最大限制</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="uploadMaxSize" value="${setting.uploadMaxSize}" maxlength="9">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">允许上传图片扩展名</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="uploadImageExtension" value="${setting.uploadImageExtension}" maxlength="200">
                                                    <span class="help-block">多个扩展名以(,)分隔，留空表示不允许上传</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">允许上传媒体扩展名</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="uploadMediaExtension" value="${setting.uploadMediaExtension}" maxlength="200">
                                                    <span class="help-block">多个扩展名以(,)分隔，留空表示不允许上传</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">允许上传文件扩展名</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="uploadFileExtension" value="${setting.uploadFileExtension}" maxlength="200">
                                                    <span class="help-block">多个扩展名以(,)分隔，留空表示不允许上传</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>图片上传路径</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="imageUploadPath" value="${setting.imageUploadPath}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>媒体上传路径</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="mediaUploadPath" value="${setting.mediaUploadPath}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>文件上传路径</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="fileUploadPath" value="${setting.fileUploadPath}" maxlength="200">
                                                </div>
                                            </div>

                                        </div>


                                        <div class="tab-pane" id="tab_4">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>SMTP服务器地址</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="smtpHost" value="${setting.smtpHost}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>SMTP服务器端口</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="smtpPort" value="${setting.smtpPort}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>SMTP用户名</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="smtpUsername" value="${setting.smtpUsername}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">SMTP密码</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="smtpPassword" value="${setting.smtpPassword}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否开启SSL</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="smtpSSLEnabled" data-on-text="是" data-off-text="否" <#if setting.smtpSSLEnabled> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>发件人邮箱</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-inline input-medium" name="smtpFromMail" value="${setting.smtpFromMail}" maxlength="200">
                                                    <a class="btn btn-default" data-toggle="modal" href="#testEamil">测试邮件</a>
                                                </div>
                                            </div>
                                            <!-- .modal- -->
                                            <div class="modal fade" id="testEamil" tabindex="-1" role="testEamil" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <h4 class="modal-title">测试邮件</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="form-group">
                                                                <div class="col-md-offset-3 col-md-9">
                                                                    <input type="text" class="form-control input-inline " id="toMail" name="toMail"  maxlength="200">
                                                                    <a class="btn green" id="sendEmail">发送邮件</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-outline grey-salsa" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div>
                                                    <!-- /.modal-content -->
                                                </div>
                                                <!-- /.modal-dialog -->
                                            </div>

                                        </div>


                                        <div class="tab-pane" id="tab_5">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>货币符号</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="currencySign" value="${setting.currencySign}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>货币单位</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="currencyUnit" value="${setting.currencyUnit}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>库存警告数</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="stockAlertCount" value="${setting.stockAlertCount}" maxlength="9">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">库存修改时间点</label>
                                                <div class="col-md-4">
                                                    <select name="stockAllocationTime" class="form-control input-medium">
                                                        <#list stockAllocationTimes as stockAllocationTime>
                                                        <option value="${stockAllocationTime}"<#if stockAllocationTime == setting.stockAllocationTime> selected="selected"</#if>>${message("Setting.StockAllocationTime."+stockAllocationTime)}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>默认积分换算比例</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="defaultPointScale" value="${setting.defaultPointScale}" maxlength="7">
                                                    <span class="help-block">系统将根据该比例与销售价自动计算赠送积分数</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否开启评论</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="reviewEnabled" data-on-text="是" data-off-text="否"  <#if setting.reviewEnabled> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否评论审核</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="reviewCheck" data-on-text="是" data-off-text="否"  <#if setting.reviewCheck> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">价格精确位数</label>
                                                <div class="col-md-4">
                                                        <select name="priceScale" class="form-control input-medium">
                                                            <option value="0"<#if setting.priceScale == 0> selected="selected"</#if>>没有小数</option>
                                                            <option value="1"<#if setting.priceScale == 1> selected="selected"</#if>>1位小数</option>
                                                            <option value="2"<#if setting.priceScale == 2> selected="selected"</#if>>2位小数</option>
                                                            <option value="3"<#if setting.priceScale == 3> selected="selected"</#if>>3位小数</option>
                                                        </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">价格精确方式</label>
                                                <div class="col-md-4">
                                                    <select name="roundType" class="form-control input-medium">
                                                        <#list roundTypes as roundType>
                                                        <option value="${roundType}"<#if roundType == setting.priceRoundType> selected="selected"</#if>>${message("Setting.RoundType."+roundType)}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否前台显示市场价</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="showMarketPrice" data-on-text="是" data-off-text="否"  <#if setting.showMarketPrice> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>默认市场价换算比例</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="defaultMarketPriceScale" value="${setting.defaultMarketPriceScale}" maxlength="200">
                                                    <span class="help-block">系统将根据该比列计算市场价格</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否开启发票功能</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="invoiceEnabled" data-on-text="是" data-off-text="否"  <#if setting.invoiceEnabled> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">是否开启含税价</label>
                                                <div class="col-md-4">
                                                    <input type="checkbox" class="make-switch" name="taxPriceEnabled" data-on-text="是" data-off-text="否"  <#if setting.taxPriceEnabled> checked="checked"</#if>>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>税率</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control input-medium" name="taxRate" value="${setting.taxRate}" maxlength="7">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><span class="required" aria-required="true"> * </span>Cookie路径</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="cookiePath" value="${setting.cookiePath}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Cookie作用域</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="cookieDomain" value="${setting.cookieDomain}" maxlength="200">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">快递100KEY</label>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control " name="kuaidi100key" value="${setting.kuaidi100key}" maxlength="200">
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
                                    <!--end tab-content-->
                                </div>
                            </div>
                            <!--portlet-body-->
                        </div>
                    </form>
                </div>
            </div>
            <!--row-->



<script type="text/javascript">
    $().ready(function(){
        var $inputForm = $("#inputForm");
        var $filePicker = $("a.filePicker");
        $filePicker.uploader();
        <#if successMessage?? && successMessage>
            $(".alert-success").show();
            $(".alert-success").delay(3000).hide(0);
        </#if>
        $.validator.addMethod("compareLength",
                function(value, element, param) {
                    return this.optional(element) || $.trim(value) == "" || $.trim($(param).val()) == "" || parseFloat(value) >= parseFloat($(param).val());
                },
                "必须大于等于最小长度"
        );
        $inputForm.validate({
            rules:{
                siteName:"required",
                siteUrl:{
                    required:true,
                    pattern:/^(http:\/\/|https:\/\/).*$/i
                },
                logo:{
                    required:true,
                    pattern:/^(http:\/\/|https:\/\/).*$/i
                },
                email:"email",
                closeMessage:"required",
                largeProductImageWidth: {
                    required: true,
                    integer: true,
                    min: 1
                },
                largeProductImageHeight: {
                    required: true,
                    integer: true,
                    min: 1
                },
                mediumProductImageWidth: {
                    required: true,
                    integer: true,
                    min: 1
                },
                mediumProductImageHeight: {
                    required: true,
                    integer: true,
                    min: 1
                },
                thumbnailProductImageWidth: {
                    required: true,
                    integer: true,
                    min: 1
                },
                thumbnailProductImageHeight: {
                    required: true,
                    integer: true,
                    min: 1
                },
                defaultLargeProductImage: {
                    required: true,
                    pattern: /^(http:\/\/|https:\/\/|\/).*$/i
                },
                defaultMediumProductImage: {
                    required: true,
                    pattern: /^(http:\/\/|https:\/\/|\/).*$/i
                },
                defaultThumbnailProductImage: {
                    required: true,
                    pattern: /^(http:\/\/|https:\/\/|\/).*$/i
                },
                watermarkAlpha: {
                    required: true,
                    digits: true,
                    max: 100
                },
                defaultMarketPriceScale: {
                    required: true,
                    min: 0,
                    decimal: {
                        integer: 3,
                        fraction: 3
                    }
                },
                usernameMinLength: {
                     required: true,
                     integer: true,
                     min: 1,
                     max: 117
                 },
                 usernameMaxLength: {
                       required: true,
                       integer: true,
                       min: 1,
                       max: 117,
                       compareLength: "#usernameMinLength"
                   },
                    passwordMinLength: {
                      required: true,
                      integer: true,
                      min: 1,
                      max: 117
                  },
                  passwordMaxLength: {
                      required: true,
                      integer: true,
                      min: 1,
                      max: 117,
                      compareLength: "#passwordMinLength"
                  },
                   registerPoint: {
                      required: true,
                      integer: true,
                      min: 0
                  },
                  registerAgreement: "required",
                  failureLoginCount:{
                      required: true,
                      integer: true,
                      min: 1
                  },
                  autoUnlockTime:{
                      required: true,
                      digits: true
                  },
                  uploadMaxSize: {
                      required: true,
                      digits: true
                  },
                  imageUploadPath: "required",
                  mediaUploadPath: "required",
                  fileUploadPath: "required",
                  smtpFromMail: {
                      required: true,
                      email: true
                  },
                  smtpHost: "required",
                  smtpPort: {
                      required: true,
                      digits: true
                  },
                  smtpUsername: "required",
                  currencySign: "required",
                  currencyUnit: "required",
                  stockAlertCount: {
                      required: true,
                      digits: true
                  },
                  defaultPointScale: {
                      required: true,
                      min: 0,
                      decimal: {
                          integer: 3,
                          fraction: 3
                      }
                  },
                  taxRate: {
                      required: true,
                      min: 0,
                      decimal: {
                          integer: 3,
                          fraction: 3
                      }
                  },
                cookiePath: "required"
            },
            invalidHandler: function() {
                $.message("error","您还有选项未正确填写, 请检查.");
            }

        });


    });
</script>
</@html.html>