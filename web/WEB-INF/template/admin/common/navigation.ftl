<#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] />
<div class="page-sidebar-wrapper">

    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->

        <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
            data-slide-speed="200" style="padding-top: 20px">
            <li class="sidebar-toggler-wrapper hide">
                <div class="sidebar-toggler">
                    <span></span>
                </div>
            </li>

            <li class="nav-item <#if bar == message("Admin.menu.home")> active open</#if>">
                <a href="${base}/admin/common/main.html" class="nav-link nav-toggle">
                    <i class="icon-home"></i>
                    <span class="title">${message("Admin.menu.home")}</span>
                    <span class="selected"></span>
                </a>
            </li>

        <#list ["admin:productManage", "admin:stockManage", "admin:productCategory", "admin:productParameter", "admin:productAttributes", "admin:specificationManage", "admin:brand", "admin:brandManage","admin:arrivalNotice"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item  <#if (message("Admin.menu.product.productManage")+message("Admin.menu.product.stockManage")+message("Admin.menu.product.productCategory")+message("Admin.menu.product.productParameter")+
                message("Admin.menu.product.productAttributes")+message("Admin.menu.product.specificationManage")+message("Admin.menu.product.brandManage")+message("Admin.menu.product.arrivalNotice"))?contains(bar)> active open</#if>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="icon-layers"></i>
                        <span class="title">${message("Admin.menu.product")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:productManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.productManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.productManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:stockManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.stockManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.stockManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:productCategory">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.productCategory")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.productCategory")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:productParameter">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.productParameter")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.productParameter")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:productAttributes">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.productAttributes")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.productAttributes")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:specificationManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.specificationManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.specificationManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:brandManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.brandManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.brandManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:arrivalNotice">
                            <li class="nav-item  <#if bar==message("Admin.menu.product.arrivalNotice")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.product.arrivalNotice")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>
        <#list ["admin:orderManage", "admin:receiptManage", "admin:refundManage", "admin:shipManage", "admin:returnManage", "admin:shipPlaceManage", "admin:expressSingleTemplate"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item  <#if (message("Admin.menu.order.orderManage")+message("Admin.menu.order.receiptManage")+message("Admin.menu.order.refundManage")+message("Admin.menu.order.shipManage")+
                message("Admin.menu.order.returnManage")+message("Admin.menu.order.shipPlaceManage")+message("Admin.menu.order.expressSingleTemplate"))?contains(bar)> active open</#if>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="icon-basket-loaded"></i>
                        <span class="title">${message("Admin.menu.order")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:orderManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.orderManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.orderManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:receiptManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.receiptManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.receiptManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:refundManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.refundManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.refundManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:shipManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.shipManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.shipManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:returnManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.returnManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.returnManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:shipPlaceManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.shipPlaceManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.shipPlaceManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:expressSingleTemplate">
                            <li class="nav-item  <#if bar==message("Admin.menu.order.expressSingleTemplate")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.order.expressSingleTemplate")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>

                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>
        <#list ["admin:memberManage", "admin:memberLevel", "admin:pointManage", "admin:pre-deposit", "admin:reviewManage", "admin:noticeManage"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item  <#if (message("Admin.menu.member.memberManage")+message("Admin.menu.member.memberLevel")+message("Admin.menu.member.pointManage")+message("Admin.menu.member.pre-deposit")+
                message("Admin.menu.member.reviewManage")+message("Admin.menu.member.noticeManage"))?contains(bar)> active open</#if>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="icon-user"></i>
                        <span class="title">${message("Admin.menu.member")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:memberManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.member.memberManage")> active open</#if>">
                                <a href="${base}/admin/member/list.html" class="nav-link ">
                                    <span class="title">${message("Admin.menu.member.memberManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:memberLevel">
                            <li class="nav-item  <#if bar==message("Admin.menu.member.memberLevel")> active open</#if>">
                                <a href="${base}/admin/member_rank/list.html" class="nav-link ">
                                    <span class="title">${message("Admin.menu.member.memberLevel")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:pointManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.member.pointManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.member.pointManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:pre-deposit">
                            <li class="nav-item  <#if bar==message("Admin.menu.member.pre-deposit")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.member.pre-deposit")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:reviewManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.member.reviewManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.member.reviewManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:noticeManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.member.noticeManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.member.noticeManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>
        <#list ["admin:articleManage", "admin:links", "admin:advertisingManage", "admin:templateManage", "admin:cacheManage", "admin:staticManage", "admin:indexManage"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item  <#if (message("Admin.menu.content.articleManage")+message("Admin.menu.content.links")+message("Admin.menu.content.advertisingManage")+message("Admin.menu.content.templateManage")+
                message("Admin.menu.content.cacheManage")+message("Admin.menu.content.staticManage")+message("Admin.menu.content.indexManage"))?contains(bar)> active open</#if>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="icon-note"></i>
                        <span class="title">${message("Admin.menu.content")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:articleManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.articleManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.articleManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:links">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.links")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.links")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:advertisingManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.advertisingManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.advertisingManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:templateManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.templateManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.templateManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:cacheManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.cacheManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.cacheManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:staticManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.staticManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.staticManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:indexManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.content.indexManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.content.indexManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>
        <#list ["admin:promotionManage", "admin:couponManage", "admin:seoManage", "admin:sitemapManage"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item  <#if (message("Admin.menu.marketing.promotionManage")+message("Admin.menu.marketing.couponManage")+message("Admin.menu.marketing.seoManage")+message("Admin.menu.marketing.sitemapManage"))?contains(bar)> active open</#if>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="icon-magic-wand"></i>
                        <span class="title">${message("Admin.menu.marketing")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:promotionManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.marketing.promotionManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.marketing.promotionManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:couponManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.marketing.couponManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.marketing.couponManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:seoManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.marketing.seoManage")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.marketing.seoManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:sitemapManage">
                            <li class="nav-item  <#if bar==message("Admin.menu.marketing.sitemapManage")> active open</#if>">
                                <a href="#" class="nav-link nav-toggle">
                                    <span class="title">${message("Admin.menu.marketing.sitemapManage")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>
        <#list ["admin:statisticsSetting", "admin:visitStatistics", "admin:memberStatistics", "admin:orderStatistics", "admin:memberRanking", "admin:productRanking"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item  <#if (message("Admin.menu.statistics.visitStatistics")+message("Admin.menu.statistics.statisticsSetting")+message("Admin.menu.statistics.memberStatistics")+message("Admin.menu.statistics.orderStatistics")+
                message("Admin.menu.statistics.memberRanking")+message("Admin.menu.statistics.productRanking"))?contains(bar)> active open</#if>">
                    <a href="#" class="nav-link nav-toggle">
                        <i class="icon-bar-chart"></i>
                        <span class="title">${message("Admin.menu.statistics")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:statisticsSetting">
                            <li class="nav-item  <#if bar==message("Admin.menu.statistics.statisticsSetting")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.statistics.statisticsSetting")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:visitStatistics">
                            <li class="nav-item  <#if bar==message("Admin.menu.statistics.visitStatistics")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.statistics.visitStatistics")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:memberStatistics">
                            <li class="nav-item  <#if bar==message("Admin.menu.statistics.memberStatistics")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.statistics.memberStatistics")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:orderStatistics">
                            <li class="nav-item  <#if bar==message("Admin.menu.statistics.orderStatistics")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.statistics.orderStatistics")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:memberRanking">
                            <li class="nav-item  <#if bar==message("Admin.menu.statistics.memberRanking")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.statistics.memberRanking")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:productRanking">
                            <li class="nav-item  <#if bar==message("Admin.menu.statistics.productRanking")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.statistics.productRanking")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>
        <#list ["admin:systemSetting", "admin:areaSetting", "admin:paymentMethod", "admin:shipMethod", "admin:logisticsCompany", "admin:paymentPlugin", "admin:storagePlugin", "admin:loginPlugin","admin:admin","admin:role","admin:log"] as permission>
            <@shiro.hasPermission name = permission>
                <li class="nav-item <#if (message("Admin.menu.system.systemSetting")+message("Admin.menu.system.areaSetting")+message("Admin.menu.system.paymentMethod")+message("Admin.menu.system.shipMethod")+
                message("Admin.menu.system.logisticsCompany")+message("Admin.menu.system.paymentPlugin")+message("Admin.menu.system.storagePlugin")+message("Admin.menu.system.loginPlugin")+
                message("Admin.menu.system.admin")+message("Admin.menu.system.role")+message("Admin.menu.system.log"))?contains(bar)> active open</#if>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="icon-settings"></i>
                        <span class="title">${message("Admin.menu.system")}</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <@shiro.hasPermission name="admin:systemSetting">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.systemSetting")> active open</#if>">
                                <a href="${base}/admin/setting/edit.html" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.systemSetting")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:areaSetting">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.areaSetting")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.areaSetting")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:paymentMethod">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.paymentMethod")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.paymentMethod")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:shipMethod">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.shipMethod")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.shipMethod")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:logisticsCompany">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.logisticsCompany")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.logisticsCompany")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:paymentPlugin">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.paymentPlugin")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.paymentPlugin")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:storagePlugin">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.storagePlugin")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.storagePlugin")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:loginPlugin">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.loginPlugin")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.loginPlugin")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:admin">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.admin")> active open</#if>">
                                <a href="${base}/admin/admin/list.html" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.admin")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:systemSetting">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.role")> active open</#if>">
                                <a href="${base}/admin/role/list.html" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.role")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="admin:log">
                            <li class="nav-item  <#if bar==message("Admin.menu.system.log")> active open</#if>">
                                <a href="#" class="nav-link ">
                                    <span class="title">${message("Admin.menu.system.log")}</span>
                                </a>
                            </li>
                        </@shiro.hasPermission>
                    </ul>
                </li>
                <#break />
            </@shiro.hasPermission>
        </#list>

        </ul>
        <!-- END SIDEBAR MENU -->
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
</div>
