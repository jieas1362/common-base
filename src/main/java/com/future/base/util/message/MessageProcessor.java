package com.future.base.util.message;

import com.future.base.constant.common.ElementKey;
import com.future.base.model.message.LanguageInfo;
import com.future.base.util.base.ProChecker;
import com.future.base.util.base.WebCommonFunctions;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

import static com.future.base.constant.common.ProAttr.LANGUAGE;
import static com.future.base.constant.common.ResponseElement.INTERNAL_SERVER_ERROR;
import static com.future.base.constant.common.Symbol.*;
import static com.future.base.util.base.FileGetter.getFiles;
import static com.future.base.util.base.FileGetter.getResources;
import static com.future.base.util.base.ProChecker.isNotNull;
import static com.future.base.util.base.ProChecker.isNull;
import static com.future.base.util.base.PropertiesProcessor.parseProp;
import static com.future.base.util.message.ElementProcessor.resolveToValues;
import static java.lang.Integer.*;
import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

/**
 * i18n message processor
 *
 * @author liuyunfei
 */
@SuppressWarnings({"AliControlFlowStatementWithoutBraces", "JavaDoc"})
public final class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    private static final String DEFAULT_LANGUAGE = lowerCase(replace(LANGUAGE, PAR_CONCATENATION.identity, PAR_CONCATENATION_DATABASE_URL.identity));
    private static final int DEFAULT_CODE = INTERNAL_SERVER_ERROR.code;
    private static final String DEFAULT_MESSAGE = INTERNAL_SERVER_ERROR.message;

    private static final String LANGUAGE_NAME_KEY = "NAME";
    private static final String LANGUAGE_PRIORITY_KEY = "PRIORITY";
    private static final String LANGUAGE_ICON_KEY = "ICON";

    private static volatile Map<String, Map<Integer, String>> I_18_N;
    private static volatile List<LanguageInfo> SUPPORT_LANGUAGES;
    private static volatile LanguageInfo DEFAULT_LANGUAGE_INFO;

    private static final UnaryOperator<String> LANGUAGE_IDENTITY_PARSER = n -> {
        int idx = lastIndexOf(n, SCHEME_SEPARATOR.identity);
        return replace(idx >= 0 ? (idx > 0 ? substring(n, 0, idx) : "") : n, PAR_CONCATENATION.identity, PAR_CONCATENATION_DATABASE_URL.identity);
    };

    private static final Function<Map<String, String>, Integer> LANGUAGE_PRIORITY_PARSER = map ->
            ofNullable(map.get(LANGUAGE_PRIORITY_KEY))
                    .filter(NumberUtils::isDigits)
                    .map(Integer::parseInt)
                    .orElse(MAX_VALUE);

    private static final Function<Map<String, String>, Map<Integer, String>> MESSAGES_CONVERTER = messages ->
            messages.entrySet().stream()
                    .filter(e -> isDigits(e.getKey()))
                    .collect(toMap(e -> parseInt(e.getKey()), Map.Entry::getValue, (a, b) -> a));

    private static final Consumer<String> CLASS_PATH_MESSAGES_LOADER = uri -> {
        List<Resource> resources = getResources(uri, ".properties");
        LOGGER.info("resources = {}", resources);

        int size = resources.size();

        Map<Integer, LanguageInfo> infoMap = new HashMap<>(size);
        Map<String, Map<Integer, String>> i18n = new HashMap<>(size);
        LanguageInfo defaultLanguageInfo = null;

        Map<String, String> messages;
        String identity;
        LanguageInfo languageInfo;

        for (Resource r : resources) {
            if (isNull(r))
                continue;

            messages = parseProp(r);
            identity = LANGUAGE_IDENTITY_PARSER.apply(r.getFilename());

            languageInfo = new LanguageInfo(ofNullable(messages.get(LANGUAGE_NAME_KEY)).orElse(""),
                    identity, ofNullable(messages.get(LANGUAGE_ICON_KEY)).orElse(""));

            infoMap.put(LANGUAGE_PRIORITY_PARSER.apply(messages), languageInfo);

            i18n.put(lowerCase(identity), MESSAGES_CONVERTER.apply(messages));

            if (DEFAULT_LANGUAGE.equals(ofNullable(languageInfo.getIdentity()).map(String::toLowerCase).orElse("")))
                defaultLanguageInfo = languageInfo;
        }

        List<LanguageInfo> supportLanguages = infoMap.entrySet().stream()
                .sorted((a, b) -> {
                    if (DEFAULT_LANGUAGE.equals(lowerCase(a.getValue().getIdentity())))
                        return MIN_VALUE;

                    if (DEFAULT_LANGUAGE.equals(lowerCase(b.getValue().getIdentity())))
                        return MAX_VALUE;

                    return a.getKey().compareTo(b.getKey());
                })
                .map(Map.Entry::getValue).collect(toList());

        if (isNull(defaultLanguageInfo))
            throw new RuntimeException("DEFAULT_LANGUAGE_INFO can't be null");

        I_18_N = i18n;
        SUPPORT_LANGUAGES = supportLanguages;
        DEFAULT_LANGUAGE_INFO = defaultLanguageInfo;

        LOGGER.info("I_18_N = {}", I_18_N);
        LOGGER.info("SUPPORT_LANGUAGES = {}", SUPPORT_LANGUAGES);
        LOGGER.info("DEFAULT_LANGUAGE_INFO = {}", DEFAULT_LANGUAGE_INFO);
    };

    private static final Consumer<String> FILE_MESSAGES_LOADER = uri -> {
        List<File> files = getFiles(uri, true);
        LOGGER.info("files = {}", files);

        int size = files.size();

        Map<Integer, LanguageInfo> infoMap = new HashMap<>(size);
        Map<String, Map<Integer, String>> i18n = new HashMap<>(size);
        LanguageInfo defaultLanguageInfo = null;

        Map<String, String> messages;
        String identity;
        LanguageInfo languageInfo;

        for (File f : files) {
            if (isNull(f))
                continue;

            messages = parseProp(f);
            identity = LANGUAGE_IDENTITY_PARSER.apply(f.getName());

            languageInfo = new LanguageInfo(ofNullable(messages.get(LANGUAGE_NAME_KEY)).orElse(""),
                    identity, ofNullable(messages.get(LANGUAGE_ICON_KEY)).orElse(""));

            infoMap.put(LANGUAGE_PRIORITY_PARSER.apply(messages), languageInfo);

            i18n.put(lowerCase(identity), MESSAGES_CONVERTER.apply(messages));

            if (DEFAULT_LANGUAGE.equals(ofNullable(languageInfo.getIdentity()).map(String::toLowerCase).orElse("")))
                defaultLanguageInfo = languageInfo;
        }

        List<LanguageInfo> supportLanguages = infoMap.entrySet().stream()
                .sorted((a, b) -> {
                    if (DEFAULT_LANGUAGE.equals(lowerCase(a.getValue().getIdentity())))
                        return MIN_VALUE;

                    if (DEFAULT_LANGUAGE.equals(lowerCase(b.getValue().getIdentity())))
                        return MAX_VALUE;

                    return a.getKey().compareTo(b.getKey());
                })
                .map(Map.Entry::getValue).collect(toList());

        if (isNull(defaultLanguageInfo))
            throw new RuntimeException("DEFAULT_LANGUAGE_INFO can't be null");

        I_18_N = i18n;
        SUPPORT_LANGUAGES = supportLanguages;
        DEFAULT_LANGUAGE_INFO = defaultLanguageInfo;

        LOGGER.info("I_18_N = {}", I_18_N);
        LOGGER.info("SUPPORT_LANGUAGES = {}", SUPPORT_LANGUAGES);
        LOGGER.info("DEFAULT_LANGUAGE_INFO = {}", DEFAULT_LANGUAGE_INFO);
    };

    private static final Predicate<String> CLASS_PATH_PRE = uri ->
            startsWith(uri, "classpath");

    private static final Consumer<String> MESSAGES_LOADER = uri -> {
        if (CLASS_PATH_PRE.test(uri)) {
            CLASS_PATH_MESSAGES_LOADER.accept(uri);
        } else {
            FILE_MESSAGES_LOADER.accept(uri);
        }
    };

    private static final Function<List<String>, Map<Integer, String>> MESSAGES_GETTER = languages -> {
        if (ProChecker.isNotEmpty(languages)) {
            Map<Integer, String> message;
            for (String language : languages)
                if (isNotNull(message = I_18_N.get(lowerCase(language))))
                    return message;
        }

        return I_18_N.get(DEFAULT_LANGUAGE);
    };

    private static final BiFunction<Integer, List<String>, String> MESSAGE_GETTER = (code, languages) ->
            ofNullable(MESSAGES_GETTER.apply(languages))
                    .map(messages -> ofNullable(messages.get(code)).orElseGet(() -> messages.get(DEFAULT_CODE)))
                    .orElse(DEFAULT_MESSAGE).intern();

    private static final Predicate<ElementKey[]> NON_KEY_REPLACEMENTS_PRE = replacements ->
            isNull(replacements) || replacements.length == 0;

    private static final Predicate<String[]> NON_STR_REPLACEMENTS_PRE = replacements ->
            isNull(replacements) || replacements.length == 0;

    private static final BiFunction<String, String[], String> FILLING_FUNC = (msg, replacements) -> {
        try {
            return format(msg, (Object[]) replacements);
        } catch (Exception e) {
            return msg;
        }
    };

    /**
     * load i18n messages
     */
    public static void load(String location) {
        if (isBlank(location))
            throw new RuntimeException("location can't be blank");

        MESSAGES_LOADER.accept(location);
    }

    /**
     * get message by language
     *
     * @param httpServletRequest
     * @return
     */
    public static Map<Integer, String> listMessage(HttpServletRequest httpServletRequest) {
        return MESSAGES_GETTER.apply(WebCommonFunctions.getAcceptLanguages(httpServletRequest));
    }

    /**
     * support languages
     *
     * @return
     */
    public static List<LanguageInfo> supportLanguages() {
        return SUPPORT_LANGUAGES;
    }

    /**
     * get default language
     *
     * @return
     */
    public static LanguageInfo defaultLanguage() {
        return DEFAULT_LANGUAGE_INFO;
    }

    /**
     * get message by default
     *
     * @param code
     * @return
     */
    public static String resolveToMessage(Integer code) {
        return MESSAGE_GETTER.apply(code, emptyList()).intern();
    }

    /**
     * get message by default
     *
     * @param code
     * @return
     */
    public static String resolveToMessage(Integer code, ElementKey[] replacements) {
        String msg = MESSAGE_GETTER.apply(code, emptyList()).intern();
        return NON_KEY_REPLACEMENTS_PRE.test(replacements) ? msg : FILLING_FUNC.apply(msg, resolveToValues(replacements));
    }

    /**
     * get message by default
     *
     * @param code
     * @return
     */
    public static String resolveToMessage(Integer code, String[] replacements) {
        String msg = MESSAGE_GETTER.apply(code, emptyList()).intern();
        return NON_STR_REPLACEMENTS_PRE.test(replacements) ? msg : FILLING_FUNC.apply(msg, replacements);
    }

    /**
     * get message by i18n
     *
     * @param code
     * @param languages
     * @return
     */
    public static String resolveToMessage(Integer code, List<String> languages) {
        return MESSAGE_GETTER.apply(code, languages).intern();
    }

    /**
     * get message by i18n
     *
     * @param code
     * @param languages
     * @param replacements
     * @return
     */
    public static String resolveToMessage(Integer code, List<String> languages, ElementKey[] replacements) {
        String msg = MESSAGE_GETTER.apply(code, languages).intern();
        return NON_KEY_REPLACEMENTS_PRE.test(replacements) ? msg : FILLING_FUNC.apply(msg, resolveToValues(replacements, languages));
    }

    /**
     * get message by i18n
     *
     * @param code
     * @param languages
     * @param replacements
     * @return
     */
    public static String resolveToMessage(Integer code, List<String> languages, String[] replacements) {
        String msg = MESSAGE_GETTER.apply(code, languages).intern();
        return NON_STR_REPLACEMENTS_PRE.test(replacements) ? msg : FILLING_FUNC.apply(msg, replacements);
    }

    /**
     * get message by i18n
     *
     * @param code
     * @param httpServletRequest
     * @return
     */
    public static String resolveToMessage(Integer code, HttpServletRequest httpServletRequest) {
        return MESSAGE_GETTER.apply(code, WebCommonFunctions.getAcceptLanguages(httpServletRequest)).intern();
    }

    /**
     * get message by i18n
     *
     * @param code
     * @param httpServletRequest
     * @param replacements
     * @return
     */
    public static String resolveToMessage(Integer code, HttpServletRequest httpServletRequest, ElementKey[] replacements) {
        String msg = MESSAGE_GETTER.apply(code, WebCommonFunctions.getAcceptLanguages(httpServletRequest)).intern();
        return NON_KEY_REPLACEMENTS_PRE.test(replacements) ? msg : FILLING_FUNC.apply(msg, resolveToValues(replacements, httpServletRequest));
    }

    /**
     * get message by i18n
     *
     * @param code
     * @param httpServletRequest
     * @param replacements
     * @return
     */
    public static String resolveToMessage(Integer code, HttpServletRequest httpServletRequest, String[] replacements) {
        String msg = MESSAGE_GETTER.apply(code, WebCommonFunctions.getAcceptLanguages(httpServletRequest)).intern();
        return NON_STR_REPLACEMENTS_PRE.test(replacements) ? msg : FILLING_FUNC.apply(msg, replacements);
    }

}