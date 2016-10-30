<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.system.role") bar=message("Admin.menu.system.role") bar_title="编辑角色权限信息">
<link href="${base}/resources/admin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
<script src="${base}/resources/admin/jstree/dist/jstree.min.js" type="text/javascript"></script>
<div class="row">
    <div class="col-md-12">
        <form id="inputForm" action="update.html" method="post" class="form-horizontal">
            <div id="flashMessage" class="alert display-hide">
                <button class="close" data-close="alert"></button>
            </div>
            <div class="portlet light portlet-fit bordered">
                <input type="hidden" name="id" value="${role.id}">
                <div class="portlet-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required"
                                                                    aria-required="true"> * </span>名称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="name" value="${role.name}" maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">描述</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="description" value="${role.description}"
                                   maxlength="200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required"
                                                                    aria-required="true"> * </span>权限</label>
                        <div class="col-md-4">
                            <div id="menuTree"></div>
                            <div id="authoritiesInput">
                                <input type="hidden" name="authorities" id="authoritiesTemp">
                            </div>
                        </div>
                    </div>



                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
                                <input type="submit" class="btn green" value="确定"
                                       <#if role.isSystem>disabled="disabled" title="系统内置的权限不允许修改" </#if>>
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
        $.validator.addMethod("compareMethod",
                function(param) {
                    return ($("#menuTree").jstree().get_checked(true)).length>0;
                },
                "必须选择权限"
        );
        var $inputForm=$("#inputForm");
        // 表单验证
        $inputForm.validate({
            rules: {
                name: "required",
                authorities: {
                    compareMethod: "#menuTree"
                }
            },
            invalidHandler: function () {
                $.message("error", "您还有选项未正确填写, 请检查.");
            }
        });
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
                    "icon":"icon-home",
                    "children": [
                        {
                            "text": "${message("Admin.menu.product")}",
                            "icon": "icon-layers",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {
                                    "id":"admin:productManage",
                                    <#if role.authorities?seq_contains("admin:productManage")>
                                    "state": {
                                        "selected": true
                                    },
                                    </#if>
                                    "text": "${message("Admin.menu.product.productManage")}"

                                },
                                {
                                    "id":"admin:stockManage",
                                    <#if role.authorities?seq_contains("admin:stockManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.product.stockManage")}"

                                },
                                {
                                    "id":"admin:productCategory",
                                    <#if role.authorities?seq_contains("admin:productCategory")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.product.productCategory")}"

                                },
                                {
                                    "id":"admin:productParameter",
                                    <#if role.authorities?seq_contains("admin:productParameter")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.product.productParameter")}"

                                },
                                {
                                    "id":"admin:productAttributes",
                                    <#if role.authorities?seq_contains("admin:productAttributes")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.product.productAttributes")}"

                                },
                                {
                                    "id":"admin:specificationManage",
                                    <#if role.authorities?seq_contains("admin:specificationManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.product.specificationManage")}"

                                },
                                {
                                    "id":"admin:brandManage",
                                    <#if role.authorities?seq_contains("admin:brandManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.product.brandManage")}"

                                },
                                {
                                    "id":"admin:arrivalNotice",
                                    <#if role.authorities?seq_contains("admin:arrivalNotice")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
                                    "id":"admin:orderManage",
                                    <#if role.authorities?seq_contains("admin:orderManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.order.orderManage")}"

                                },
                                {
                                    "id":"admin:receiptManage",
                                    <#if role.authorities?seq_contains("admin:receiptManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.order.receiptManage")}"

                                },
                                {
                                    "id":"admin:refundManage",
                                    <#if role.authorities?seq_contains("admin:refundManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.order.refundManage")}"

                                },
                                {
                                    "id":"admin:shipManage",
                                    <#if role.authorities?seq_contains("admin:shipManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.order.shipManage")}"

                                },
                                {
                                    "id":"admin:returnManage",
                                    <#if role.authorities?seq_contains("admin:returnManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.order.returnManage")}"

                                },
                                {
                                    "id":"admin:shipPlaceManage",
                                    <#if role.authorities?seq_contains("admin:shipPlaceManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.order.shipPlaceManage")}"

                                },
                                {
                                    "id":"admin:expressSingleTemplate",
                                    <#if role.authorities?seq_contains("admin:expressSingleTemplate")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
                                    "id":"admin:memberManage",
                                    <#if role.authorities?seq_contains("admin:memberManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.member.memberManage")}"
                                },
                                {
                                    "id":"admin:memberLevel",
                                    <#if role.authorities?seq_contains("admin:memberLevel")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.member.memberLevel")}"

                                },
                                {
                                    "id":"admin:pointManage",
                                    <#if role.authorities?seq_contains("admin:pointManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.member.pointManage")}"

                                },
                                {
                                    "id":"admin:pre-deposit",
                                    <#if role.authorities?seq_contains("admin:pre-deposit")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.member.pre-deposit")}"

                                },
                                {
                                    "id":"admin:reviewManage",
                                    <#if role.authorities?seq_contains("admin:reviewManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.member.reviewManage")}"

                                },
                                {
                                    "id":"admin:noticeManage",
                                    <#if role.authorities?seq_contains("admin:noticeManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
                                    "id":"admin:articleManage",
                                    <#if role.authorities?seq_contains("admin:articleManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.content.articleManage")}"

                                },
                                {
                                    "id":"admin:links",
                                    <#if role.authorities?seq_contains("admin:links")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.content.links")}"

                                },
                                {
                                    "id":"admin:advertisingManage",
                                    <#if role.authorities?seq_contains("admin:advertisingManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.content.advertisingManage")}"

                                },
                                {
                                    "id":"admin:templateManage",
                                    <#if role.authorities?seq_contains("admin:templateManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.content.templateManage")}"

                                },
                                {
                                    "id":"admin:cacheManage",
                                    <#if role.authorities?seq_contains("admin:cacheManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.content.cacheManage")}"

                                },
                                {
                                    "id":"admin:staticManage",
                                    <#if role.authorities?seq_contains("admin:staticManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.content.staticManage")}"

                                },
                                {
                                    "id":"admin:indexManage",
                                    <#if role.authorities?seq_contains("admin:indexManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
                                    "id":"admin:promotionManage",
                                    <#if role.authorities?seq_contains("admin:promotionManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.marketing.promotionManage")}"

                                },
                                {
                                    "id":"admin:couponManage",
                                    <#if role.authorities?seq_contains("admin:couponManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.marketing.couponManage")}"

                                },

                                {
                                    "id":"admin:seoManage",
                                    <#if role.authorities?seq_contains("admin:seoManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.marketing.seoManage")}"

                                },
                                {
                                    "id":"admin:sitemapManage",
                                    <#if role.authorities?seq_contains("admin:sitemapManage")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
                                    "id":"admin:statisticsSetting",
                                    <#if role.authorities?seq_contains("admin:statisticsSetting")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.statistics.statisticsSetting")}"

                                },
                                {
                                    "id":"admin:visitStatistics",
                                    <#if role.authorities?seq_contains("admin:visitStatistics")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.statistics.visitStatistics")}"

                                },
                                {
                                    "id":"admin:memberStatistics",
                                    <#if role.authorities?seq_contains("admin:memberStatistics")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.statistics.memberStatistics")}"

                                },
                                {
                                    "id":"admin:orderStatistics",
                                    <#if role.authorities?seq_contains("admin:orderStatistics")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.statistics.orderStatistics")}"

                                },
                                {
                                    "id":"admin:memberRanking",
                                    <#if role.authorities?seq_contains("admin:memberRanking")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.statistics.memberRanking")}"

                                },
                                {
                                    "id":"admin:productRanking",
                                    <#if role.authorities?seq_contains("admin:productRanking")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
                                    "id":"admin:systemSetting",
                                    <#if role.authorities?seq_contains("admin:systemSetting")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.systemSetting")}"

                                },
                                {
                                    "id":"admin:areaSetting",
                                    <#if role.authorities?seq_contains("admin:areaSetting")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.areaSetting")}"

                                },
                                {
                                    "id":"admin:paymentMethod",
                                    <#if role.authorities?seq_contains("admin:paymentMethod")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.paymentMethod")}"

                                },
                                {
                                    "id":"admin:shipMethod",
                                    <#if role.authorities?seq_contains("admin:shipMethod")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.shipMethod")}"

                                },
                                {
                                    "id":"admin:logisticsCompany",
                                    <#if role.authorities?seq_contains("admin:logisticsCompany")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.logisticsCompany")}"

                                },
                                {
                                    "id":"admin:paymentPlugin",
                                    <#if role.authorities?seq_contains("admin:paymentPlugin")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.paymentPlugin")}"

                                },
                                {
                                    "id":"admin:storagePlugin",
                                    <#if role.authorities?seq_contains("admin:storagePlugin")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.storagePlugin")}"

                                },
                                {
                                    "id":"admin:loginPlugin",
                                    <#if role.authorities?seq_contains("admin:loginPlugin")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.loginPlugin")}"

                                },
                                {
                                    "id":"admin:admin",
                                    <#if role.authorities?seq_contains("admin:admin")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.admin")}"

                                },
                                {
                                    "id":"admin:role",
                                    <#if role.authorities?seq_contains("admin:role")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
                                    "text": "${message("Admin.menu.system.role")}"

                                },
                                {
                                    "id":"admin:log",
                                    <#if role.authorities?seq_contains("admin:log")>
                                        "state": {
                                            "selected": true
                                        },
                                    </#if>
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
        $inputForm.submit(function () {
            var a =$('#menuTree').jstree().get_checked(true);
            $authoritiesInput = $("#authoritiesInput");
            var inputHtml="";
            for(var i=0;i<a.length;i++)
            {
                var b = a[i];
                if((b.id).indexOf("admin:")>-1){
                    inputHtml = "<input type='hidden' name='authorities' value='"+b.id+"'>";
                    $authoritiesInput.append(inputHtml);
                }
            }
        });

    });
</script>
</@html.html>