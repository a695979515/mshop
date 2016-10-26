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
function removeCookie(name,options){
    addCookie(name,null,options);
}
$().ready(function () {
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
        errorElement: "span",
        errorPlacement: function(error, element) {
            var fieldSet = element.closest("div.form-group.div.input");
            if (fieldSet.size() > 0) {
                error.appendTo(fieldSet);
            } else {
                error.insertAfter(element);
            }

        },
        submitHandler: function(form) {
          /*  $(form).find("input:submit").prop("disabled", true);*/
            form.submit();
        }
        });

}
