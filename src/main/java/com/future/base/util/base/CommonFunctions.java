package com.future.base.util.base;

import com.future.base.component.exception.handler.ExceptionProcessor;
import com.future.base.constant.common.ProAttr;
import com.future.base.constant.common.ProHeader;
import com.future.base.constant.common.Symbol;
import com.future.base.model.common.ExceptionElement;
import com.future.base.model.exps.ProException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.time.Clock;
import java.util.List;
import java.util.Set;
import java.util.function.*;

import static com.future.base.constant.common.ProAttr.LANGUAGE;
import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static com.future.base.constant.common.Symbol.PAIR_SEPARATOR;
import static com.future.base.constant.common.Symbol.PAR_CONCATENATION_DATABASE_URL;
import static java.lang.System.currentTimeMillis;
import static java.time.Instant.now;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Common function set
 *
 * @author liuyunfei
 */
@SuppressWarnings({"WeakerAccess", "JavaDoc", "AliControlFlowStatementWithoutBraces", "unused"})
public class CommonFunctions {

    public static final Gson GSON = new GsonBuilder().serializeNulls().create();

    /**
     * symbols
     */
    public static final String
            PATH_SEPARATOR = Symbol.PATH_SEPARATOR.identity,
            WILDCARD = Symbol.WILDCARD.identity,
            PAR_CONCATENATION = Symbol.PAR_CONCATENATION.identity,
            SCHEME_SEPARATOR = Symbol.SCHEME_SEPARATOR.identity,
            URL_PAR_SEPARATOR = Symbol.URL_PAR_SEPARATOR.identity,
            UNKNOWN = Symbol.UNKNOWN.identity;

    public static final String DEFAULT_LANGUAGE = lowerCase(LANGUAGE.replace(PAR_CONCATENATION, PAR_CONCATENATION_DATABASE_URL.identity));
    public static final List<String> DEFAULT_LANGUAGES = singletonList(DEFAULT_LANGUAGE);

    /**
     * auth header key
     */
    public static final String AUTHORIZATION = ProHeader.AUTHORIZATION.name;

    /**
     * rate limiter key prefix
     */
    public static final String RATE_LIMIT_KEY_PRE = Symbol.RATE_LIMIT_KEY_PRE.identity;

    /**
     * random key length
     */
    public static final int RAN_KEY_STR_LEN = 8;

    /**
     * index of non element
     */
    public static final int NON_EXIST_INDEX = -1, START_IDX = 0;

    /**
     * clock
     */
    public static final Clock CLOCK = ProAttr.CLOCK;

    /**
     * stamp getter/seconds
     */
    public static final Supplier<Long> TIME_STAMP_GETTER = () -> now(CLOCK).getEpochSecond();

    /**
     * stamp getter/millis
     */
    public static final Supplier<Long> MILLIS_STAMP_SUP = () -> now(CLOCK).toEpochMilli();

    /**
     * resource key generator for request
     */
    public static final BinaryOperator<String> REQ_RES_KEY_GENERATOR = (method, uri) ->
            (upperCase(method).intern() + PAR_CONCATENATION + uri).intern();

    /**
     * header value getter
     */
    public static final UnaryOperator<String> HEADER_VALUE_CONVERTER = headerValue -> {
        int idx = indexOf(headerValue, PAIR_SEPARATOR.identity);
        return idx == NON_EXIST_INDEX ? headerValue : substring(headerValue, 0, idx);
    };

    /**
     * valid schema
     */
    public static final Set<String> VALID_SCHEMAS = of("https", "http")
            .map(StringUtils::lowerCase)
            .collect(toSet());

    /**
     * valid method
     */
    public static final Set<String> VALID_METHODS = of(HttpMethod.values())
            .map(Enum::name)
            .map(StringUtils::upperCase)
            .collect(toSet());

    /**
     * schema asserter
     */
    public static final Consumer<String> SCHEMA_ASSERTER = schema -> {
        if (VALID_SCHEMAS.contains(lowerCase(schema)))
            return;

        throw new ProException(BAD_REQUEST);
    };

    /**
     * method asserter
     */
    public static final Consumer<String> METHOD_VALUE_ASSERTER = method -> {
        if (VALID_METHODS.contains(upperCase(method)))
            return;

        throw new ProException(BAD_REQUEST);
    };

    /**
     * header value getter
     */
    public static final BiFunction<HttpHeaders, String, String> HEADER_VALUE_GETTER = (headers, key) ->
            ofNullable(headers.get(key))
                    .filter(cts -> cts.size() > 0)
                    .map(cts -> cts.get(0))
                    .map(HEADER_VALUE_CONVERTER)
                    .orElse("");

    /**
     * simple header value getter
     */
    public static final BiFunction<HttpHeaders, String, String> SIMPLE_HEADER_VALUE_GETTER = (headers, key) ->
            ofNullable(headers.getFirst(key))
                    .orElse("");

    /**
     * get a random str
     */
    public static final Supplier<String> RANDOM_KEY_GETTER = () ->
            randomAlphanumeric(RAN_KEY_STR_LEN) + PAR_CONCATENATION + currentTimeMillis();

    /**
     * throwable converter
     */
    public static final BiFunction<Throwable, List<String>, ExceptionElement> THROWABLE_CONVERTER = ExceptionProcessor::handle;

}
