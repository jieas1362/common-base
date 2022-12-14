package com.future.base.util.metadata;

import com.future.base.model.exps.ProException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.future.base.constant.common.ResponseElement.INVALID_METADATA_PARAM;
import static com.future.base.util.base.ProChecker.isNotNull;
import static com.future.base.util.base.CommonFunctions.GSON;
import static com.google.gson.reflect.TypeToken.getParameterized;

/**
 * metadata converter
 *
 * @author liuyunfei
 */
@SuppressWarnings({"JavaDoc", "unused", "AliControlFlowStatementWithoutBraces"})
public final class MetadataProcessor {

    private static final Type METADATA_TYPE = getParameterized(Map.class, String.class, String.class).getType();

    /**
     * return empty metadata
     */
    private static final Supplier<Map<String, String>> EMPTY_METADATA_SUP = HashMap::new;

    /**
     * empty json
     */
    private static final String EMPTY_JSON = "{}";

    /**
     * metadata map -> json
     *
     * @param metadata
     * @return
     */
    public static String metadataToJson(Map<String, String> metadata) {
        if (isNotNull(metadata))
            return GSON.toJson(metadata);

        return EMPTY_JSON;
    }

    /**
     * json -> metadata map
     *
     * @param json
     * @return
     */
    public static Map<String, String> jsonToMetadata(String json) {
        if (isNotNull(json))
            try {
                return GSON.fromJson(json, METADATA_TYPE);
            } catch (JsonSyntaxException e) {
                throw new ProException(INVALID_METADATA_PARAM);
            }

        return EMPTY_METADATA_SUP.get();
    }

}
