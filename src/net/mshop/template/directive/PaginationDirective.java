package net.mshop.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import net.mshop.util.FreeMarkerUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页指令
 * Created by Panfuhao on 2016/10/27.
 */
@Component("paginationDirective")
public class PaginationDirective extends BaseDirective {
    /**
     * 模式-参数名称
     */
    private static final String PATTERN_PARAMETER_NAME = "pattern";
    /**
     * 页码-参数名称
     */
    private static final String PAGE_NUMBER_PARAMETER_NAME = "pageNumber";
    /**
     * 总页数-参数名称
     */
    private static final String TOTAL_PAGES_PARAMETER_NAME = "totalPages";
    /**
     * 中间段页数码-参数名称
     */
    private static final String SEGMENT_COUNT_PARAMETER_NAME = "segmentCount";
    /**
     * 模式-变量名称
     */
    private static final String PATTERN_VARIABLE_NAME = "pattern";
    /**
     * 页码-变量名称
     */
    private static final String PAGE_NUMBER_VARIABLE_NAME = "pageNumber";
    /**
     * 总页数-变量名称
     */
    private static final String PAGE_COUNT_VARIABLE_NAME = "totalPages";
    /**
     * 中间段页码-变量名称
     */
    private static final String SEGMENT_COUNT_VARIABLE_NAME = "segmentCount";
    /**
     * 是否存在上一页-变量名称
     */
    private static final String HAS_PREVIOUS_VARIABLE_NAME = "hasPrevious";
    /**
     * 是否存在下一页-变量名称
     */
    private static final String HAS_NEXT_VARIABLE_NAME = "hasNext";
    /**
     * 是否为首页-变量名称
     */
    private static final String IS_FIRST_VARIABLE_NAME = "isFirst";
    /**
     * 是否为尾页-变量名称
     */
    private static final String IS_LAST_VARIABLE_NAME = "isLast";
    /**
     * 上一页页码-变量名称
     */
    private static final String PREVIOUS_PAGE_NUMBER_VARIABLE_NAME = "previousPageNumber";
    /**
     * 下一页页码-变量名称
     */
    private static final String NEXT_PAGE_NUMBER_VARIABLE_NAME = "nextPageNumber";
    /**
     * 首页页码-变量名称
     */
    private static final String FIRST_PAGE_NUMBER_VARIABLE_NAME = "firstPageNumber";
    /**
     * 尾页页码-变量名称
     */
    private static final String LAST_PAGE_NUMBER_VARIABLE_NAME = "lastPageNumber";
    /**
     * 中间段页码-变量名称
     */
    private static final String SEGMENT_VARIABLE_NAME = "segment";

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        String pattern = FreeMarkerUtils.getParameter(PATTERN_PARAMETER_NAME, String.class, map);
        Integer pageNumber = FreeMarkerUtils.getParameter(PAGE_NUMBER_PARAMETER_NAME, Integer.class, map);
        Integer totalPages = FreeMarkerUtils.getParameter(TOTAL_PAGES_PARAMETER_NAME, Integer.class, map);
        Integer segmentCount = FreeMarkerUtils.getParameter(SEGMENT_COUNT_PARAMETER_NAME, Integer.class, map);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        if (totalPages == null || totalPages < 1) {
            totalPages = 1;
        }
        if (segmentCount == null || segmentCount < 1) {
            segmentCount = 5;
        }
        boolean hasPrevious = pageNumber > 1;
        boolean hasNext = pageNumber < totalPages;
        boolean isFirst = pageNumber == 1;
        boolean isLast = pageNumber.equals(totalPages);
        int previousPageNumber = pageNumber - 1;
        int nextPageNumber = pageNumber + 1;
        int firstPageNumber = 1;
        int lastPageNumber = totalPages;
        int startSegmentPageNumber = pageNumber - (int) Math.floor((segmentCount - 1) / 2D);
        int endSegmentPageNumber = pageNumber + (int) Math.ceil((segmentCount - 1) / 2D);
        if (startSegmentPageNumber < 1) {
            startSegmentPageNumber = 1;
        }
        if (endSegmentPageNumber > totalPages) {
            endSegmentPageNumber = totalPages;
        }
        List<Integer> segment = new ArrayList<>();
        for (int i = startSegmentPageNumber; i <= endSegmentPageNumber; i++) {
            segment.add(i);
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put(PATTERN_VARIABLE_NAME, pattern);
        variables.put(PAGE_NUMBER_VARIABLE_NAME, pageNumber);
        variables.put(PAGE_COUNT_VARIABLE_NAME, totalPages);
        variables.put(SEGMENT_COUNT_VARIABLE_NAME, segmentCount);
        variables.put(HAS_PREVIOUS_VARIABLE_NAME, hasPrevious);
        variables.put(HAS_NEXT_VARIABLE_NAME, hasNext);
        variables.put(IS_FIRST_VARIABLE_NAME, isFirst);
        variables.put(IS_LAST_VARIABLE_NAME, isLast);
        variables.put(PREVIOUS_PAGE_NUMBER_VARIABLE_NAME, previousPageNumber);
        variables.put(NEXT_PAGE_NUMBER_VARIABLE_NAME, nextPageNumber);
        variables.put(FIRST_PAGE_NUMBER_VARIABLE_NAME, firstPageNumber);
        variables.put(LAST_PAGE_NUMBER_VARIABLE_NAME, lastPageNumber);
        variables.put(SEGMENT_VARIABLE_NAME, segment);
        setLocalVariables(variables, environment, templateDirectiveBody);
    }
}
