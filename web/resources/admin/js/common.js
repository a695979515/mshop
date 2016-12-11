/**
 * Created by Panfuhao on 2016/10/14.
 */
/**
 * 添加Cookie
 * @param name
 * @param value
 * @param options
 */
function addCookie(name, value, options) {
    if (arguments.length > 1 && name != null) {
        if (options == null) {
            options = {};
        }
        if (value == null) {
            options.expires = -1;
        }
        if (typeof options.expires == "number") {
            var time = options.expires;
            var expires = options.expires = new Date();
            expires.setTime(expires.getTime() + time * 1000);
        }
        if (options.path == null) {
            options.path = "/";
        }
        if (options.domain == null) {
            options.domain = "";
        }
        document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires != null ? ";expires=" + options.expires.toUTCString() : "") + (options.path != "" ? ";path" + options.path : "") + (options.domain != "" ? ";domain=" + options.domain : "") + (options.secure != null ? ";secure" : "");
    }
}
/**
 * 获取cookie
 * @param name
 * @returns {*}
 */
function getCookie(name) {
    if (name != null) {
        var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
        return value ? decodeURIComponent(value[1]) : null;
    }
}
/**
 * 移除Cookie
 * @param name
 * @param options
 */
function removeCookie(name, options) {
    addCookie(name, null, options);
}

/**
 * 瞬时消息
 * @returns {boolean}
 */
(function ($) {
    $.message = function () {
        var $flashMessage = $("#flashMessage");
        var $flashMessageContent = $("#flashMessage span");
        $flashMessageContent.empty();
        var message = {};
        if ($.isPlainObject(arguments[0])) {
            message = arguments[0];
        } else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
            message.type = arguments[0];
            message.content = arguments[1];
        } else {
            return false;
        }
        if (message.type == null || message.content == null) {
            return false;
        }
        if (message.type == "success") {
            $flashMessage.addClass("alert-success");
            $flashMessageContent.append(message.content);
            $flashMessage.show();
            $flashMessage.delay(3000).hide(0);
        } else if (message.type == "warn") {
            $flashMessage.addClass("alert-warning");
            $flashMessageContent.append(message.content);
            $flashMessage.show();
            $flashMessage.delay(3000).hide(0);
        } else if (message.type == "error") {
            $flashMessage.addClass("alert-danger");
            $flashMessageContent.append(message.content);
            $flashMessage.show();
            $flashMessage.delay(3000).hide(0);
        }

        return false;
    };
    $.fn.extend({
        // 文件上传
        uploader: function (options) {
            var settings = {
                url: '/manage/file/upload',
                fileType: "image",
                fileName: "file",
                data: {},
                maxSize: 10,
                extensions: null,
                before: null,
                complete: null
            };
            $.extend(settings, options);

            if (settings.extensions == null) {
                switch (settings.fileType) {
                    case "media":
                        settings.extensions = 'swf,flv,mp3,wav,avi,rm,rmvb';
                        break;
                    case "file":
                        settings.extensions = 'zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx';
                        break;
                    default:
                        settings.extensions = 'jpg,jpeg,bmp,gif,png';
                }
            }

            var $progressBar = $('<div class="progressBar"><\/div>').appendTo("body");
            return this.each(function () {
                var element = this;
                var $element = $(element);

                var webUploader = WebUploader.create({
                    swf: '/resource/manage/flash/webuploader.swf',
                    server: settings.url + (settings.url.indexOf('?') < 0 ? '?' : '&') + 'fileType=' + settings.fileType,
                    pick: {
                        id: element,
                        multiple: false
                    },
                    fileVal: settings.fileName,
                    formData: settings.data,
                    fileSingleSizeLimit: settings.maxSize * 1024 * 1024,
                    accept: {
                        extensions: settings.extensions
                    },
                    fileNumLimit: 1,
                    auto: true
                }).on('beforeFileQueued', function (file) {
                    if ($.isFunction(settings.before) && settings.before.call(element, file) === false) {
                        return false;
                    }
                    if ($.trim(settings.extensions) == '') {
                        this.trigger('error', 'Q_TYPE_DENIED');
                        return false;
                    }
                    this.reset();
                    $progressBar.show();
                }).on('uploadProgress', function (file, percentage) {
                    $progressBar.width(percentage * 100 + '%');
                }).on('uploadAccept', function (file, data) {
                    $progressBar.fadeOut("slow", function () {
                        $progressBar.width(0);
                    });
                    if (data.message.type != 'success') {
                        $.message(data.message);
                        return false;
                    }
                    $element.prev("input:text").val(data.url);
                    if ($.isFunction(settings.complete)) {
                        settings.complete.call(element, file, data);
                    }
                }).on('error', function (type) {
                    switch (type) {
                        case "F_EXCEED_SIZE":
                            $.message("warn", "上传文件大小超出限制");
                            break;
                        case "Q_TYPE_DENIED":
                            $.message("warn", "上传文件格式不正确");
                            break;
                        default:
                            $.message("warn", "上传文件出现错误");
                    }
                });

                $element.mouseover(function () {
                    webUploader.refresh();
                });
            });
        },

        // 编辑器
        editor: function (options) {
            window.UEDITOR_CONFIG = {
                UEDITOR_HOME_URL: '/resources/admin/ueditor/',
                serverUrl: '/manage/file/upload',
                imageActionName: "uploadImage",
                imageFieldName: "file",
                imageMaxSize: 10485760,
                imageAllowFiles: ['.jpg', '.jpeg', '.bmp', '.gif', '.png'],
                imageUrlPrefix: "",
                imagePathFormat: "",
                imageCompressEnable: false,
                imageCompressBorder: 1600,
                imageInsertAlign: "none",
                videoActionName: "uploadMedia",
                videoFieldName: "file",
                videoMaxSize: 10485760,
                videoAllowFiles: ['.swf', '.flv', '.mp3', '.wav', '.avi', '.rm', '.rmvb'],
                videoUrlPrefix: "",
                videoPathFormat: "",
                fileActionName: "uploadFile",
                fileFieldName: "file",
                fileMaxSize: 10485760,
                fileAllowFiles: ['.zip', '.rar', '.7z', '.doc', '.docx', '.xls', '.xlsx', '.ppt', '.pptx'],
                fileUrlPrefix: "",
                filePathFormat: "",
                toolbars: [[
                    'fullscreen', 'source', '|',
                    'undo', 'redo', '|',
                    'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',
                    'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                    'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                    'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                    'directionalityltr', 'directionalityrtl', 'indent', '|',
                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                    'touppercase', 'tolowercase', '|',
                    'link', 'unlink', 'anchor', '|',
                    'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                    'insertimage', 'insertvideo', 'attachment', 'map', 'insertframe', 'pagebreak', '|',
                    'horizontal', 'date', 'time', 'spechars', '|',
                    'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
                    'print', 'preview', 'searchreplace', 'drafts'
                ]],
                lang: 'zh_CN',
                iframeCssUrl: null,
                pageBreakTag: 'page_break_tag',
                wordCount: false
            };

            UE.Editor.prototype.getActionUrl = function (action) {
                var serverUrl = this.getOpt('serverUrl');
                switch (action) {
                    case "uploadImage":
                        return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=image';
                    case "uploadMedia":
                        return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=media';
                    case "uploadFile":
                        return serverUrl + (serverUrl.indexOf('?') < 0 ? '?' : '&') + 'fileType=file';
                }
                return null;
            };

            UE.Editor.prototype.loadServerConfig = function () {
                this._serverConfigLoaded = true;
            };

            return this.each(function () {
                var element = this;
                var $element = $(element);

                UE.getEditor($element.attr("id"), options).ready(function () {
                    this.execCommand("serverparam", {
                        token: getCookie("token")
                    });
                });
            });
        }

    });
})(jQuery);


$().ready(function () {

    // AJAX全局设置
    $.ajaxSetup({
        traditional: true
    });
    //ajax提交时 传令牌参数
    $(document).ajaxSend(function (event, request, settings) {
        if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
            var token = getCookie("token");
            if (token != null) {
                request.setRequestHeader("token", token);
            }
        }
    })

    $("form").submit(function () {
        var $this = $(this);
        var size = $this.find("input[name='token']").size();
        if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && size == 0) {
            var token = getCookie("token");
            if (token != null) {
                $this.append('<input type="hidden" name="token" value="' + token + '"\/>');
            }
        }
    });
});

/**
 * 验证
 */
if ($.validator != null) {

    $.extend($.validator.messages, {
        required: '必填',
        email: 'E-mail格式错误',
        url: '网址格式错误',
        date: '日期格式错误',
        dateISO: '日期格式错误',
        pointcard: '信用卡格式错误',
        number: '只允许输入数字',
        digits: '只允许输入零或正整数',
        minlength: $.validator.format('长度不允许小于{0}'),
        maxlength: $.validator.format('长度不允许大于{0}'),
        rangelength: $.validator.format('长度必须在{0}-{1}之间'),
        min: $.validator.format('不允许小于{0}'),
        max: $.validator.format('不允许大于{0}'),
        range: $.validator.format('必须在{0}-{1}之间'),
        accept: '输入后缀错误',
        equalTo: '两次输入不一致',
        remote: '输入错误',
        integer: '只允许输入整数',
        positive: '只允许输入正数',
        negative: '只允许输入负数',
        decimal: '数值超出了允许范围',
        pattern: '格式错误',
        extension: '文件格式错误'
    });

    $.validator.setDefaults({
        errorClass: "validator-error",
        ignore: ".ignore",
        ignoreTitle: true,
        errorElement: "label",
        errorPlacement: function (error, element) {
            var fieldSet = element.closest("div.form-group");
            if (fieldSet.size() > 0) {
                error.appendTo(fieldSet);
            } else {
                error.insertAfter(element);
            }

        },
        submitHandler: function (form) {
            form.submit();
        }
    });

}
