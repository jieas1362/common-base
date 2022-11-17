package com.future.base.util.message;

import com.future.base.constant.common.ElementKey;
import com.future.base.util.base.ProChecker;
import com.future.base.util.base.PropertiesProcessor;
import com.future.base.util.base.WebCommonFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

import static com.future.base.constant.common.ElementKey.DEFAULT;
import static com.future.base.constant.common.ProAttr.LANGUAGE;
import static com.future.base.constant.common.Symbol.*;
import static com.future.base.util.base.FileGetter.getFiles;
import static com.future.base.util.base.FileGetter.getResources;
import static com.future.base.util.base.ProChecker.isNotNull;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * i18n element processor
 *
 * @author liuyunfei
 */
@SuppressWarnings({"AliControlFlowStatementWithoutBraces", "JavaDoc", "unused"})
public final class ElementProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElementProcessor.class);

    private static final String DEFAULT_LANGUAGE = lowerCase(replace(LANGUAGE, PAR_CONCATENATION.identity, PAR_CONCATENATION_DATABASE_URL.identity));
    private static final String DEFAULT_KEY = DEFAULT.key;
    private static final String DEFAULT_VALUE = DEFAULT.key;

    private static final UnaryOperator<String> PRE_NAME_PARSER = n -> {
        int idx = lastIndexOf(n, SCHEME_SEPARATOR.identity);
        String name = idx >= 0 ? (idx > 0 ? substring(n, 0, idx) :"") : n;
        return replace(name, PAR_CONCATENATION.identity, PAR_CONCATENATION_DATABASE_URL.identity);
    };

    private static volatile Map<String, Map<String, String>> I_18_N;

    private static final Consumer<String> CLASS_PATH_ELEMENT_LOADER = uri -> {
        List<Resource> resources = getResources(uri, ".properties");
        LOGGER.info("resources = {}", resources);

        if (resources.size() != ofNullable(MessageProcessor.supportLanguages()).map(List::size).orElse(0))
            LOGGER.warn("size of element languages support and size of message languages support are different");

        //noinspection UnnecessaryLocalVariable
        Map<String, Map<String, String>> i18n = resources.stream()
                .collect(toMap(f -> lowerCase(PRE_NAME_PARSER.apply(f.getFilename())),
                        PropertiesProcessor::parseProp, (a, b) -> a));

        I_18_N = i18n;
        LOGGER.info("I_18_N = {}", I_18_N);
    };

    private static final Consumer<String> FILE_ELEMENT_LOADER = uri -> {
        List<File> files = getFiles(uri, true).stream().filter(Objects::nonNull).collect(toList());
        LOGGER.info("files = {}", files);

        if (files.size() != ofNullable(MessageProcessor.supportLanguages()).map(List::size).orElse(0))
            LOGGER.warn("size of element languages support and size of message languages support are different");

        //noinspection UnnecessaryLocalVariable
        Map<String, Map<String, String>> i18n = files.stream()
                .collect(toMap(f -> lowerCase(PRE_NAME_PARSER.apply(f.getName())),
                        PropertiesProcessor::parseProp, (a, b) -> a));

        I_18_N = i18n;
        LOGGER.info("I_18_N = {}", I_18_N);
    };

    private static final Predicate<String> CLASS_PATH_PRE = uri ->
            startsWith(uri, "classpath");

    private static final Consumer<String> ELEMENT_LOADER = uri -> {
        if (CLASS_PATH_PRE.test(uri)) {
            CLASS_PATH_ELEMENT_LOADER.accept(uri);
        } else {
            FILE_ELEMENT_LOADER.accept(uri);
        }
    };

    private static final Function<List<String>, Map<String, String>> ELEMENT_GETTER = languages -> {
        if (ProChecker.isNotEmpty(languages)) {
            Map<String, String> element;
            for (String language : languages)
                if (isNotNull(element = I_18_N.get(lowerCase(language))))
                    return element;
        }

        return I_18_N.get(DEFAULT_LANGUAGE);
    };

    private static final BiFunction<List<String>, Map<String, String>, Map<String, String>> TARGETS_GETTER = (keys, allElement) -> {
        if (ProChecker.isNotEmpty(allElement) && ProChecker.isNotEmpty(keys)) {
            Map<String, String> res = new HashMap<>(keys.size(), 1.0f);
            String value;
            for (String key : keys) {
                if (isNotBlank(key)) {
                    value = allElement.get(key);
                    res.put(key, isNotNull(value) ? value : "");
                }
            }

            return res;
        }

        return emptyMap();
    };

    private static final BiFunction<Map<String, String>, ElementKey, String> VALUE_GETTER = (values, key) ->
            ofNullable(values.get(ofNullable(key).map(k -> k.key).orElse(DEFAULT_KEY))).orElse(DEFAULT_VALUE).intern();

    /**
     * load i18n elements
     */
    public static void load(String location) {
        if (isBlank(location))
            throw new RuntimeException("location can't be blank");

        ELEMENT_LOADER.accept(location);
    }

    /**
     * select all elements by default language
     *
     * @return
     */
    public static Map<String, String> selectAllElement() {
        return ELEMENT_GETTER.apply(null);
    }

    /**
     * select all elements by languages
     *
     * @param languages
     * @return
     */
    public static Map<String, String> selectAllElement(List<String> languages) {
        return ELEMENT_GETTER.apply(languages);
    }

    /**
     * select all elements by request
     *
     * @param httpServletRequest
     * @return
     */
    public static Map<String, String> selectAllElement(HttpServletRequest httpServletRequest) {
        return selectAllElement(WebCommonFunctions.getAcceptLanguages(httpServletRequest));
    }

    /**
     * select elements by default language and keys
     *
     * @param keys
     * @return
     */
    public static Map<String, String> selectElement(List<String> keys) {
        return ProChecker.isNotEmpty(keys) ? TARGETS_GETTER.apply(keys, ELEMENT_GETTER.apply(null)) : emptyMap();
    }

    /**
     * select elements by languages and keys
     *
     * @param keys
     * @param languages
     * @return
     */
    public static Map<String, String> selectElement(List<String> keys, List<String> languages) {
        return ProChecker.isNotEmpty(keys) ? TARGETS_GETTER.apply(keys, ELEMENT_GETTER.apply(languages)) : emptyMap();
    }

    /**
     * select elements by request and request
     *
     * @param keys
     * @param httpServletRequest
     * @return
     */
    public static Map<String, String> selectElement(List<String> keys, HttpServletRequest httpServletRequest) {
        return selectElement(keys, WebCommonFunctions.getAcceptLanguages(httpServletRequest));
    }

    /**
     * get element value by i18n
     *
     * @param key
     * @return
     */
    public static String resolveToValue(ElementKey key) {
        return resolveToValue(key, emptyList());
    }

    /**
     * get element values by i18n
     *
     * @param keys
     * @return
     */
    public static String[] resolveToValues(ElementKey[] keys) {
        return resolveToValues(keys, emptyList());
    }

    /**
     * get element value by i18n
     *
     * @param key
     * @param languages
     * @return
     */
    public static String resolveToValue(ElementKey key, List<String> languages) {
        return ofNullable(ELEMENT_GETTER.apply(languages))
                .map(values -> VALUE_GETTER.apply(values, key))
                .orElse(DEFAULT_VALUE).intern();
    }

    /**
     * get element values by i18n
     *
     * @param keys
     * @param languages
     * @return
     */
    public static String[] resolveToValues(ElementKey[] keys, List<String> languages) {
        return ofNullable(ELEMENT_GETTER.apply(languages))
                .map(values ->
                        ofNullable(keys)
                                .filter(ks -> ks.length > 0)
                                .map(ks ->
                                        Stream.of(ks)
                                                .map(key -> VALUE_GETTER.apply(values, key))
                                                .toArray(String[]::new)
                                ).orElse(new String[]{DEFAULT_VALUE.intern()})
                )
                .orElse(new String[]{DEFAULT_VALUE.intern()});
    }

    /**
     * get element value by i18n
     *
     * @param key
     * @param httpServletRequest
     * @return
     */
    public static String resolveToValue(ElementKey key, HttpServletRequest httpServletRequest) {
        return resolveToValue(key, WebCommonFunctions.getAcceptLanguages(httpServletRequest));
    }

    /**
     * get element values by i18n
     *
     * @param keys
     * @param httpServletRequest
     * @return
     */
    public static String[] resolveToValues(ElementKey[] keys, HttpServletRequest httpServletRequest) {
        return resolveToValues(keys, WebCommonFunctions.getAcceptLanguages(httpServletRequest));
    }

}