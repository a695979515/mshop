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

            <li class="nav-item  <#if (message("Admin.menu.product.productManage")+message("Admin.menu.product.stockManage")+message("Admin.menu.product.productCategory")+message("Admin.menu.product.productParameter")+
            message("Admin.menu.product.productAttributes")+message("Admin.menu.product.specificationManage")+message("Admin.menu.product.brandManage")+message("Admin.menu.product.arrivalNotice"))?contains(bar)> active open</#if>">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-diamond"></i>
                    <span class="title">${message("Admin.menu.product")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.product.productManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.productManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.stockManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.stockManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.productCategory")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.productCategory")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.productParameter")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.productParameter")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.productAttributes")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.productAttributes")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.specificationManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.specificationManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.brandManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.brandManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.product.arrivalNotice")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.product.arrivalNotice")}</span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item  <#if (message("Admin.menu.order.orderManage")+message("Admin.menu.order.receiptManage")+message("Admin.menu.order.refundManage")+message("Admin.menu.order.shipManage")+
            message("Admin.menu.order.returnManage")+message("Admin.menu.order.shipPlaceManage")+message("Admin.menu.order.expressSingleTemplate"))?contains(bar)> active open</#if>">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-puzzle"></i>
                    <span class="title">${message("Admin.menu.order")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.order.orderManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.orderManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.order.receiptManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.receiptManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.order.refundManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.refundManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.order.shipManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.shipManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.order.returnManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.returnManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.order.shipPlaceManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.shipPlaceManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.order.expressSingleTemplate")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.order.expressSingleTemplate")}</span>
                        </a>
                    </li>

                </ul>
            </li>

            <li class="nav-item  <#if (message("Admin.menu.member.memberManage")+message("Admin.menu.member.memberLevel")+message("Admin.menu.member.pointManage")+message("Admin.menu.member.pre-deposit")+
            message("Admin.menu.member.reviewManage")+message("Admin.menu.member.noticeManage"))?contains(bar)> active open</#if>">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-settings"></i>
                    <span class="title">${message("Admin.menu.member")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.member.memberManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.member.memberManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.member.memberLevel")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.member.memberLevel")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.member.pointManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.member.pointManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.member.pre-deposit")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.member.pre-deposit")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.member.reviewManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.member.reviewManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.member.noticeManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.member.noticeManage")}</span>
                        </a>
                    </li>

                </ul>
            </li>

            <li class="nav-item  <#if (message("Admin.menu.content.articleManage")+message("Admin.menu.content.links")+message("Admin.menu.content.advertisingManage")+message("Admin.menu.content.templateManage")+
            message("Admin.menu.content.cacheManage")+message("Admin.menu.content.staticManage")+message("Admin.menu.content.indexManage"))?contains(bar)> active open</#if>">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-bulb"></i>
                    <span class="title">${message("Admin.menu.content")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.content.articleManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.articleManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.content.links")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.links")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.content.advertisingManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.advertisingManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.content.templateManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.templateManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.content.cacheManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.cacheManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.content.staticManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.staticManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.content.indexManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.content.indexManage")}</span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item  <#if (message("Admin.menu.marketing.promotionManage")+message("Admin.menu.marketing.couponManage")+message("Admin.menu.marketing.seoManage")+message("Admin.menu.marketing.sitemapManage"))?contains(bar)> active open</#if>">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-briefcase"></i>
                    <span class="title">${message("Admin.menu.marketing")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.marketing.promotionManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.marketing.promotionManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.marketing.couponManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.marketing.couponManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.marketing.seoManage")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.marketing.seoManage")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.marketing.sitemapManage")> active open</#if>">
                        <a href="#" class="nav-link nav-toggle">
                            <span class="title">${message("Admin.menu.marketing.sitemapManage")}</span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item  <#if (message("Admin.menu.statistics.visitStatistics")+message("Admin.menu.statistics.statisticsSetting")+message("Admin.menu.statistics.memberStatistics")+message("Admin.menu.statistics.orderStatistics")+
            message("Admin.menu.statistics.memberRanking")+message("Admin.menu.statistics.productRanking"))?contains(bar)> active open</#if>">
                <a href="#" class="nav-link nav-toggle">
                    <i class="icon-wallet"></i>
                    <span class="title">${message("Admin.menu.statistics")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.statistics.visitStatistics")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.statistics.visitStatistics")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.statistics.statisticsSetting")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.statistics.statisticsSetting")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.statistics.memberStatistics")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.statistics.memberStatistics")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.statistics.orderStatistics")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.statistics.orderStatistics")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.statistics.memberRanking")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.statistics.memberRanking")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.systemSetting")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.statistics.productRanking")}</span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item <#if (message("Admin.menu.system.systemSetting")+message("Admin.menu.system.areaSetting")+message("Admin.menu.system.paymentMethod")+message("Admin.menu.system.shipMethod")+
            message("Admin.menu.system.logisticsCompany")+message("Admin.menu.system.paymentPlugin")+message("Admin.menu.system.storagePlugin")+message("Admin.menu.system.loginPlugin")+
            message("Admin.menu.system.admin")+message("Admin.menu.system.role")+message("Admin.menu.system.log"))?contains(bar)> active open</#if>">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-bar-chart"></i>
                    <span class="title">${message("Admin.menu.system")}</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  <#if bar==message("Admin.menu.system.systemSetting")> active open</#if>">
                        <a href="${base}/admin/setting/edit.html" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.systemSetting")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.areaSetting")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.areaSetting")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.paymentMethod")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.paymentMethod")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.shipMethod")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.shipMethod")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.logisticsCompany")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.logisticsCompany")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.paymentPlugin")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.paymentPlugin")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.storagePlugin")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.storagePlugin")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.loginPlugin")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.loginPlugin")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.admin")> active open</#if>">
                        <a href="${base}/admin/admin/list.html" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.admin")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.role")> active open</#if>">
                        <a href="${base}/admin/role/list.html" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.role")}</span>
                        </a>
                    </li>
                    <li class="nav-item  <#if bar==message("Admin.menu.system.log")> active open</#if>">
                        <a href="#" class="nav-link ">
                            <span class="title">${message("Admin.menu.system.log")}</span>
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
        <!-- END SIDEBAR MENU -->
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
</div>
