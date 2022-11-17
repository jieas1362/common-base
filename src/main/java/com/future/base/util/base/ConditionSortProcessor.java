package com.future.base.util.base;

import com.future.base.constant.common.SortType;
import com.future.base.model.common.SortCondition;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.future.base.constant.common.SortType.DESC;
import static com.future.base.util.base.ProChecker.*;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

/**
 * sort by condition util
 *
 * @author liuyunfei
 */
@SuppressWarnings({"JavaDoc", "AliControlFlowStatementWithoutBraces"})
public final class ConditionSortProcessor {

    private static final Set<String> VALID_SORTS = Stream.of(SortType.values()).map(st -> st.identity).collect(toSet());

    /**
     * assert and package attr
     *
     * @param condition
     * @param sortAttrMapping
     * @param defaultSortColumn
     */
    public static void process(SortCondition condition, Map<String, String> sortAttrMapping, String defaultSortColumn) {
        if (isNull(condition))
            return;

        String sortType = condition.getSortType();
        if (isBlank(sortType) || !VALID_SORTS.contains(sortType))
            condition.setSortType(DESC.identity);

        String sortAttribute = condition.getSortAttribute();
        if (isBlank(sortAttribute) || isEmpty(sortAttrMapping)) {
            condition.setSortAttribute(defaultSortColumn);
        } else {
            condition.setSortAttribute(ofNullable(sortAttrMapping.get(sortAttribute))
                    .orElse(defaultSortColumn));
        }
    }

}
