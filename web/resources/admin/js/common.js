/**
 * Created by Panfuhao on 2016/10/14.
 */
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
