/**
 * Created by Panfuhao on 2016/10/27.
 */
$().ready(function () {
    var $listForm = $("#listForm");
    var $deleteButton = $("#deleteButton");
    var $ids = $(".table-scrollable input[name='ids']");
    var $selectAll = $("#selectAll");
    var $scrollable = $(".table-scrollable");
    var $pageSizeMenu = $("#pageSizeMenu li");
    var $pageSize = $("#pageSize");
    var $pageNumber = $("#pageNumber");
    /**
     * 删除按钮
     */
    $deleteButton.click(function () {
        var $this = $(this);
        if ($this.hasClass("disabled")) {
            return false;
        }
        var $checkedIds = $(".table-scrollable input[name='ids']:enabled:checked");
        bootbox.dialog({
            message: "你确定要删除选中的选项吗？",
            title: "警告",
            buttons: {
                success: {
                    label: "确定",
                    className: "btn green",
                    callback: function () {
                        $.ajax({
                            url: "delete.html",
                            type: "POST",
                            data: $checkedIds.serialize(),
                            dataTypes: "json",
                            cache: false,
                            success: function (data) {
                                var datas = JSON.parse(data)
                                if (datas.type == "success") {
                                    $checkedIds.closest("tr").remove();
                                    if ($scrollable.find("tr").size() <= 0) {
                                        setTimeout(function () {
                                            location.reload(true);
                                        }, 3000);
                                    }
                                }
                                $deleteButton.attr("disabled", true);
                                $selectAll.prop("checked", false);
                                $checkedIds.prop("checked", false);
                            }
                        });
                    }
                },
                cancel: {
                    label: "取消",
                    className: "btn btn-outline grey-salsa",
                    callback: function () {

                    }
                }
            }
        });
    });

    /**
     * 选中全部复选框
     */
    $selectAll.click(function () {
        var $this = $(this);
        var $enabledIds = $(".table-scrollable input[name='ids']:enabled");
        if ($this.prop("checked")) {
            $enabledIds.prop("checked", true);
            if ($enabledIds.filter(":checked").size() > 0) {
                $deleteButton.attr("disabled", false);
            } else {
                $deleteButton.attr("disabled", true);
            }
        } else {
            $enabledIds.prop("checked", false);
            $deleteButton.attr("disabled", true);
        }
    });
    /**
     * 复选框点击事件
     */
    $ids.click(function () {
        var $this = $(this);
        if ($this.prop("checked")) {
            $deleteButton.attr("disabled", false);
        } else {
            if ($(".table-scrollable input[name='ids']:enabled:checked").size() > 0) {
                $deleteButton.attr("disabled", false);
            } else {
                $deleteButton.attr("disabled", true);
            }
        }
    });
    /**
     * 选择每页显示记录数
     */
    $pageSizeMenu.click(function () {
        $pageSize.val($.trim($(this).text()));
        $pageNumber.val("1");
        $listForm.submit();
    });
    /**
     * 页码点击跳转事件
     * @param pageNumber
     * @returns {boolean}
     */
    $.pageSkip = function (pageNumber) {
        $pageNumber.val(pageNumber);
        $listForm.submit();
        return false;
    }
});
