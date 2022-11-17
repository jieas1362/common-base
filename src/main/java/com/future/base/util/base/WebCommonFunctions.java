package com.future.base.util.base;

import com.future.base.model.common.ProResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.future.base.constant.common.ResponseElement.OK;
import static com.future.base.constant.common.Symbol.LIST_ELEMENT_SEPARATOR;
import static com.future.base.util.message.MessageProcessor.resolveToMessage;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.split;
import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * 传统mvc组件集
 *
 * @author liu
 */
@SuppressWarnings({"unused", "JavaDoc", "CatchMayIgnoreException"})
public final class WebCommonFunctions extends CommonFunctions {

    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String X_REAL_IP = "X-Real-IP";
    private static final String UNKNOWN = "UNKNOWN";

    private static final Predicate<String> VALID_HEADER_ASSERTER = h ->
            h != null && !"".equals(h) && !UNKNOWN.equalsIgnoreCase(h);

    public static final Function<HttpServletRequest, String> REQUEST_IDENTITY_GETTER = request ->
            RATE_LIMIT_KEY_PRE + ofNullable(request)
                    .map(WebCommonFunctions::getIp)
                    .map(String::hashCode)
                    .map(String::valueOf)
                    .orElse(UNKNOWN);

    /**
     * get Accept-Language
     *
     * @param httpServletRequest
     * @return
     */
    public static List<String> getAcceptLanguages(HttpServletRequest httpServletRequest) {
        try {
            return ofNullable(httpServletRequest.getHeader(ACCEPT_LANGUAGE)).filter(ProChecker::isNotBlank)
                    .map(l -> l.split(LIST_ELEMENT_SEPARATOR.identity)[0])
                    .map(Collections::singletonList).orElseGet(Collections::emptyList);
        } catch (Exception e) {
            return DEFAULT_LANGUAGES;
        }
    }

    /**
     * package response result for reactive
     *
     * @param code
     * @return
     */
    public static ProResponse<String> generate(int code) {
        String message = resolveToMessage(code).intern();
        return new ProResponse<>(code, message, message);
    }

    /**
     * package response result for reactive
     *
     * @param code
     * @param httpServletRequest
     * @return
     */
    public static ProResponse<String> generate(int code, HttpServletRequest httpServletRequest) {
        String message = resolveToMessage(code, httpServletRequest).intern();
        return new ProResponse<>(code, message, message);
    }

    /**
     * package response result for reactive
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ProResponse<T> generate(int code, T data) {
        return new ProResponse<>(code, data, resolveToMessage(code));
    }

    /**
     * package response result for reactive
     *
     * @param code
     * @param data
     * @param httpServletRequest
     * @param <T>
     * @return
     */
    public static <T> ProResponse<T> generate(int code, T data, HttpServletRequest httpServletRequest) {
        return new ProResponse<>(code, data, resolveToMessage(code, httpServletRequest));
    }

    /**
     * package response result for reactive
     *
     * @param code
     * @param httpServletRequest
     * @return
     */
    public static ProResponse<String> generate(int code, HttpServletRequest httpServletRequest, String[] replacements) {
        String message = resolveToMessage(code, httpServletRequest, replacements);
        return new ProResponse<>(code, message, message);
    }

    /**
     * 获取请求IP
     *
     * @param httpServletRequest
     * @return
     */
    @SuppressWarnings("DuplicatedCode")
    public static String getIp(HttpServletRequest httpServletRequest) {

        String ip = httpServletRequest.getHeader(X_FORWARDED_FOR);
        if (VALID_HEADER_ASSERTER.test(ip)) {
            return split(ip, ",")[0];
        }

        ip = httpServletRequest.getHeader(PROXY_CLIENT_IP);
        if (VALID_HEADER_ASSERTER.test(ip)) {
            return ip;
        }

        ip = httpServletRequest.getHeader(WL_PROXY_CLIENT_IP);
        if (VALID_HEADER_ASSERTER.test(ip)) {
            return ip;
        }

        ip = httpServletRequest.getHeader(HTTP_CLIENT_IP);
        if (VALID_HEADER_ASSERTER.test(ip)) {
            return ip;
        }

        ip = httpServletRequest.getHeader(HTTP_X_FORWARDED_FOR);
        if (VALID_HEADER_ASSERTER.test(ip)) {
            return ip;
        }

        ip = httpServletRequest.getHeader(X_REAL_IP);
        if (VALID_HEADER_ASSERTER.test(ip)) {
            return ip;
        }

        return ofNullable(httpServletRequest.getRemoteAddr())
                .orElse(UNKNOWN);
    }

    /**
     * write response
     *
     * @param request
     * @param response
     * @param data
     * @throws IOException
     */
    public static void writeResponse(HttpServletRequest request, HttpServletResponse response, String data) {
        try {
            response.setStatus(OK.status);
            //noinspection deprecation
            response.setContentType(APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(ofNullable(data).orElse(""));
            response.flushBuffer();
        } catch (IOException e) {
        }
    }

}
