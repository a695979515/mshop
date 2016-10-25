<#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] />
<div class="page-sidebar-wrapper">

    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->

        <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
            <li class="sidebar-toggler-wrapper hide">
                <div class="sidebar-toggler">
                    <span></span>
                </div>
            </li>

            <li class="nav-item start active open">
                <a href="${base}/admin/common/main.html" class="nav-link nav-toggle">
                    <i class="icon-home"></i>
                    <span class="title">主页</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li class="nav-item  ">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-diamond"></i>
                    <span class="title">商品</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">商品管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">库存管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">商品分类</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">商品参数</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">商品属性</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">规格管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">品牌管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">到货通知</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item  ">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-puzzle"></i>
                    <span class="title">订单</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">订单管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">收款管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">退款管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">发货管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">退货管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">发货点管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">快递单模版</span>
                        </a>
                    </li>

                </ul>
            </li>
            <li class="nav-item  ">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-settings"></i>
                    <span class="title">会员</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                                        <span class="title">会员管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                                        <span class="title">会员等级</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">会员注册项</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                                        <span class="title">积分管理
                                            </span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                                        <span class="title">预存款</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">评论管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">消息配置</span>
                        </a>
                    </li>

                </ul>
            </li>
            <li class="nav-item  ">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-bulb"></i>
                    <span class="title">内容</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">导航管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">文章管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">文章分类</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">标签管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">友情链接</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">广告位</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">广告管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">模版管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">主题设置</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">缓存管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">静态化管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">索引管理</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item  ">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-briefcase"></i>
                    <span class="title">营销</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">促销管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">优惠卷管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">SEO管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link nav-toggle">
                            <span class="title">Sitemap管理</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item  ">
                <a href="#" class="nav-link nav-toggle">
                    <i class="icon-wallet"></i>
                    <span class="title">统计</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">访问统计</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">统计设置</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">会员统计</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">订单统计</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">会员排名</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">商品排名</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item  ">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="icon-bar-chart"></i>
                    <span class="title">系统</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item  ">
                        <a href="${base}/admin/setting/edit.html" class="nav-link ">
                            <span class="title">系统设置</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">地区管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">支付方式</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">配送方法</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">物流公司</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">支付插件</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">存储插件</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">登录插件</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="${base}/admin/admin/list.html" class="nav-link ">
                            <span class="title">管理员</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">角色管理</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">发送消息</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">消息列表</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">草稿箱</span>
                        </a>
                    </li>
                    <li class="nav-item  ">
                        <a href="#" class="nav-link ">
                            <span class="title">日志管理</span>
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
