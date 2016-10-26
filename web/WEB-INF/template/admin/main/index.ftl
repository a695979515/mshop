<#import "/admin/common/base.ftl" as html/>
<@html.html title=message("Admin.menu.home") bar=message("Admin.menu.home") bar_title="系统信息、概要">

            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 blue" href="#">
                        <div class="visual">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="1,349">0</span>件
                            </div>
                            <div class="desc"> 上架商品</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 red" href="#">
                        <div class="visual">
                            <i class="fa fa-bullhorn"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="125">0</span>件
                            </div>
                            <div class="desc"> 库存警报数</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 green" href="#">
                        <div class="visual">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="549">0</span>个
                            </div>
                            <div class="desc"> 会员总数</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 purple" href="#">
                        <div class="visual">
                            <i class="fa fa-bar-chart-o"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="89"></span>￥
                            </div>
                            <div class="desc"> 预存款</div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="row">
                <div class="col-md-6">
                    <!-- BEGIN BORDERED TABLE PORTLET-->
                    <div class="portlet light portlet-fit bordered">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-settings font-grey-mint"></i>
                                <span class="caption-subject font-grey-mint sbold uppercase">系统配置</span>
                            </div>

                        </div>
                        <div class="portlet-body">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <thead>
                                    <tr class="uppercase">
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td> 系统名称:</td>
                                        <td> ${systemName}</td>
                                    </tr>
                                    <tr>
                                        <td> 系统版本:</td>
                                        <td> ${systemVersion}</td>
                                    </tr>
                                    <tr>
                                        <td> JDK版本:</td>
                                        <td> ${javaVersion}</td>
                                    </tr>
                                    <tr>
                                        <td> JRE路径:</td>
                                        <td> ${javaHome}</td>
                                    </tr>
                                    <tr>
                                        <td> 操作系统名称:</td>
                                        <td> ${osName}</td>
                                    </tr>
                                    <tr>
                                        <td> 操作系统架构:</td>
                                        <td> ${osArch}</td>
                                    </tr>
                                    <tr>
                                        <td> Servlet版本:</td>
                                        <td> ${serverInfo}</td>
                                    </tr>
                                    <tr>
                                        <td> Servlet版本:</td>
                                        <td> ${servletVersion}</td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- END BORDERED TABLE PORTLET-->

                </div>
                <div class="col-md-6"></div>
            </div>


<script src="${base}/resources/admin/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
<script src="${base}/resources/admin/counterup/jquery.counterup.min.js" type="text/javascript"></script>
</@html.html>