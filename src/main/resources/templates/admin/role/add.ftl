<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.system.role") bar=message("Admin.menu.system.role") bar_title="添加角色权限信息">
<link href="${base}/resources/admin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/admin/jstree/dist/jstree.min.js" type="text/javascript"></script>
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
                        <label class="col-md-3 control-label"><span class="required"
                                                                    aria-required="true"> * </span>名称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="name" maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">描述</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="description" maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required"
                                                                    aria-required="true"> * </span>权限</label>
                        <div class="col-md-4">
                            <div id="menuTree"></div>
                            <div id="authoritiesInput">
                                <input type="hidden" name="authoritiesTemp" >
                            </div>
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
        $.validator.addMethod("compareMethod",
                function(param) {
                    return ($("#menuTree").jstree().get_checked(true)).length>0;
                },
                "必须选择权限"
        );
        // 表单验证
        $inputForm.validate({
            rules: {
                name: "required",
                authoritiesTemp: {
                    compareMethod: "#menuTree"
                }
            },
            invalidHandler: function () {
                $.message("error", "您还有选项未正确填写, 请检查.");
            },
            submitHandler: function (form) {
                addAuthorities();
                form.submit();
            }

        });
        function addAuthorities () {
            $authorities =  $("input[name='authorities']");
            $authorities.remove();
            var a = $('#menuTree').jstree().get_checked(true);
            $authoritiesInput = $("#authoritiesInput");
            var inputHtml = "";
            if (a.length > 0) {
                for (var i = 0; i < a.length; i++) {
                    var b = a[i];
                    if ((b.id).indexOf("admin:") > -1) {
                        inputHtml = "<input type='hidden' name='authorities' value='" + b.id + "'>";
                        $authoritiesInput.append(inputHtml);
                    }
                }
            }
        }
        $('#menuTree').jstree({
            'plugins': ["wholerow", "checkbox", "types"],
            'core': {
                "themes": {
                    "responsive": false
                },
                'dblclick_toggle': false,//禁用双击打开下级菜单
                'data': [{
                    "state": {
                        "opened": true
                    },
                    "text": "导航权限树",
                    "icon": "icon-home",
                    "children": [
                        {
                            "text": "${message("Admin.menu.product")}",
                            "icon": "icon-layers",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:productManage",
                                    "text": "${message("Admin.menu.product.productManage")}"

                                },
                                {
                                    "id": "admin:stockManage",
                                    "text": "${message("Admin.menu.product.stockManage")}"

                                },
                                {
                                    "id": "admin:productCategory",
                                    "text": "${message("Admin.menu.product.productCategory")}"

                                },
                                {
                                    "id": "admin:productParameter",
                                    "text": "${message("Admin.menu.product.productParameter")}"

                                },
                                {
                                    "id": "admin:productAttributes",
                                    "text": "${message("Admin.menu.product.productAttributes")}"

                                },
                                {
                                    "id": "admin:specificationManage",
                                    "text": "${message("Admin.menu.product.specificationManage")}"

                                },
                                {
                                    "id": "admin:brandManage",
                                    "text": "${message("Admin.menu.product.brandManage")}"

                                },
                                {
                                    "id": "admin:arrivalNotice",
                                    "text": "${message("Admin.menu.product.arrivalNotice")}"

                                }
                            ]
                        },
                        {
                            "text": "${message("Admin.menu.order")}",
                            "icon": "icon-basket-loaded",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:orderManage",
                                    "text": "${message("Admin.menu.order.orderManage")}"

                                },
                                {
                                    "id": "admin:receiptManage",
                                    "text": "${message("Admin.menu.order.receiptManage")}"

                                },
                                {
                                    "id": "admin:refundManage",
                                    "text": "${message("Admin.menu.order.refundManage")}"

                                },
                                {
                                    "id": "admin:shipManage",
                                    "text": "${message("Admin.menu.order.shipManage")}"

                                },
                                {
                                    "id": "admin:returnManage",
                                    "text": "${message("Admin.menu.order.returnManage")}"

                                },
                                {
                                    "id": "admin:shipPlaceManage",
                                    "text": "${message("Admin.menu.order.shipPlaceManage")}"

                                },
                                {
                                    "id": "admin:expressSingleTemplate",
                                    "text": "${message("Admin.menu.order.expressSingleTemplate")}"

                                }]
                        },
                        {
                            "text": "${message("Admin.menu.member")}",
                            "icon": "icon-user",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:memberManage",
                                    "text": "${message("Admin.menu.member.memberManage")}"
                                },
                                {
                                    "id": "admin:memberLevel",
                                    "text": "${message("Admin.menu.member.memberLevel")}"

                                },
                                {
                                    "id": "admin:pointManage",
                                    "text": "${message("Admin.menu.member.pointManage")}"

                                },
                                {
                                    "id": "admin:pre-deposit",
                                    "text": "${message("Admin.menu.member.pre-deposit")}"

                                },
                                {
                                    "id": "admin:reviewManage",
                                    "text": "${message("Admin.menu.member.reviewManage")}"

                                },
                                {
                                    "id": "admin:noticeManage",
                                    "text": "${message("Admin.menu.member.noticeManage")}"

                                }]
                        },
                        {
                            "text": "${message("Admin.menu.content")}",
                            "icon": "icon-note",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:articleManage",
                                    "text": "${message("Admin.menu.content.articleManage")}"

                                },
                                {
                                    "id": "admin:links",
                                    "text": "${message("Admin.menu.content.links")}"

                                },
                                {
                                    "id": "admin:advertisingManage",
                                    "text": "${message("Admin.menu.content.advertisingManage")}"

                                },
                                {
                                    "id": "admin:templateManage",
                                    "text": "${message("Admin.menu.content.templateManage")}"

                                },
                                {
                                    "id": "admin:cacheManage",
                                    "text": "${message("Admin.menu.content.cacheManage")}"

                                },
                                {
                                    "id": "admin:staticManage",
                                    "text": "${message("Admin.menu.content.staticManage")}"

                                },
                                {
                                    "id": "admin:indexManage",
                                    "text": "${message("Admin.menu.content.indexManage")}"

                                }
                            ]
                        },
                        {
                            "text": "${message("Admin.menu.marketing")}",
                            "icon": "icon-magic-wand",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:promotionManage",
                                    "text": "${message("Admin.menu.marketing.promotionManage")}"

                                },
                                {
                                    "id": "admin:couponManage",
                                    "text": "${message("Admin.menu.marketing.couponManage")}"

                                },

                                {
                                    "id": "admin:seoManage",
                                    "text": "${message("Admin.menu.marketing.seoManage")}"

                                },
                                {
                                    "id": "admin:sitemapManage",
                                    "text": "${message("Admin.menu.marketing.sitemapManage")}"

                                }]
                        },
                        {
                            "text": "${message("Admin.menu.statistics")}",
                            "icon": "icon-bar-chart",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:statisticsSetting",
                                    "text": "${message("Admin.menu.statistics.statisticsSetting")}"

                                },
                                {
                                    "id": "admin:visitStatistics",
                                    "text": "${message("Admin.menu.statistics.visitStatistics")}"

                                },
                                {
                                    "id": "admin:memberStatistics",
                                    "text": "${message("Admin.menu.statistics.memberStatistics")}"

                                },
                                {
                                    "id": "admin:orderStatistics",
                                    "text": "${message("Admin.menu.statistics.orderStatistics")}"

                                },
                                {
                                    "id": "admin:memberRanking",
                                    "text": "${message("Admin.menu.statistics.memberRanking")}"

                                },
                                {
                                    "id": "admin:productRanking",
                                    "text": "${message("Admin.menu.statistics.productRanking")}"

                                }
                            ]
                        },
                        {
                            "text": "${message("Admin.menu.system")}",
                            "icon": "icon-settings",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id": "admin:systemSetting",
                                    "text": "${message("Admin.menu.system.systemSetting")}"

                                },
                                {
                                    "id": "admin:areaSetting",
                                    "text": "${message("Admin.menu.system.areaSetting")}"

                                },
                                {
                                    "id": "admin:paymentMethod",
                                    "text": "${message("Admin.menu.system.paymentMethod")}"

                                },
                                {
                                    "id": "admin:shipMethod",
                                    "text": "${message("Admin.menu.system.shipMethod")}"

                                },
                                {
                                    "id": "admin:logisticsCompany",
                                    "text": "${message("Admin.menu.system.logisticsCompany")}"

                                },
                                {
                                    "id": "admin:paymentPlugin",
                                    "text": "${message("Admin.menu.system.paymentPlugin")}"

                                },
                                {
                                    "id": "admin:storagePlugin",
                                    "text": "${message("Admin.menu.system.storagePlugin")}"

                                },
                                {
                                    "id": "admin:loginPlugin",
                                    "text": "${message("Admin.menu.system.loginPlugin")}"

                                },
                                {
                                    "id": "admin:admin",
                                    "text": "${message("Admin.menu.system.admin")}"

                                },
                                {
                                    "id": "admin:role",
                                    "text": "${message("Admin.menu.system.role")}"

                                },
                                {
                                    "id": "admin:log",
                                    "text": "${message("Admin.menu.system.log")}"

                                }
                            ]
                        }]
                }
                ]
            },
            "types": {
                "default": {
                    "icon": "false"
                }
            }
        });


    });
</script>
</@html.html>