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
