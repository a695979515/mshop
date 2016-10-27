<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
<input type="hidden" id="searchProperty" name="searchProperty" value="${page.searchProperty}"/>
<input type="hidden" id="orderProperty" name="orderProperty" value="${page.orderProperty}"/>
<input type="hidden" id="orderDirection" name="orderDirection" value="${page.orderDirection}"/>
<#if totalPages gt 1>
<div class="btn-toolbar margin-bottom-10 " style="float: right;">
    <div class="btn-group ">
        <#if isFirst>
            <button type="button" class="btn btn-sm btn-default" disabled="disabled">首页</button>
        <#else>
            <a href="javascript:$.pageSkip(${firstPageNumber});" class="btn btn-sm btn-default">首页</a>
        </#if>
    </div>
    <div class="btn-group ">
        <#if hasPrevious>
            <a href="javascript:$.pageSkip(${previousPageNumber});" class="btn btn-sm btn-default">上一页</a>
        <#else>
            <button type="button" class="btn btn-sm btn-default" disabled="disabled">上一页</button>
        </#if>

        <#list segment as segmentPageNumber>
            <#if segmentPageNumber_index == 0 && segmentPageNumber gt firstPageNumber + 1>
                <button type="button" class="btn btn-sm btn-default" disabled="disabled">...</button>
            </#if>
            <#if segmentPageNumber != pageNumber>
                <a href="javascript:$.pageSkip(${segmentPageNumber});"
                   class="btn btn-sm btn-default">${segmentPageNumber}</a>
            <#else>
                <button type="button" class="btn btn-sm btn-default active"
                        disabled="disabled">${segmentPageNumber}</button>
            </#if>
            <#if !segmentPageNumber_has_next && segmentPageNumber lt lastPageNumber - 1>
                <button type="button" class="btn btn-sm btn-default" disabled="disabled">...</button>
            </#if>
        </#list>

        <#if hasNext>
            <a href="javascript:$.pageSkip(${nextPageNumber});" class="btn btn-sm btn-default">下一页</a>
        <#else>
            <button type="button" class="btn btn-sm btn-default" disabled="disabled">下一页</button>
        </#if>
        </div>
    <div class="btn-group ">
        <#if isLast>
            <button type="button" class="btn btn-sm btn-default" disabled="disabled">尾页</button>
        <#else>
            <a href="javascript:$.pageSkip(${lastPageNumber});" class="btn btn-sm btn-default">尾页</a>
        </#if>
        <label style="padding: 5px 10px;font-size: 12px;line-height: 1.5;">共${totalPages}页</label>
    </div>


    <div class="btn-group">
        <div class="input-group" style="width: 100px;">
            <input id="pageNumber" class="form-control input-sm" name="pageNumber"  value="${pageNumber}" maxlength="9"/>
            <span class="input-group-btn">
            <button type="submit" class="btn btn-sm btn-default">跳转</button>
                </span>
        </div>
    </div>
</div>

</#if>