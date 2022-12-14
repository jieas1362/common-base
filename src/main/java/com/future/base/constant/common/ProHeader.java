package com.future.base.constant.common;

import org.springframework.http.HttpHeaders;

/**
 * HTTP headers
 *
 * @author liuyunfei
 */
public enum ProHeader {

    /**
     * auth
     */
    AUTHORIZATION(HttpHeaders.AUTHORIZATION),

    /**
     * private key for client
     */
    SECRET("Secret"),

    /**
     * refresh token
     */
    REFRESH("Refresh"),

    /**
     * user agent
     */
    USER_AGENT(HttpHeaders.USER_AGENT),

    /**
     * request ip
     */
    REQUEST_IP("Request_Ip"),

    /**
     * metadata
     */
    METADATA("Metadata"),

    /**
     * user source
     */
    SOURCE("Source"),

    /**
     * host
     */
    HOST(HttpHeaders.HOST),

    /**
     * verify key
     */
    VERIFY_KEY("Verify-Key"),

    /**
     * verify value
     */
    VERIFY_VALUE("Verify-Value"),

    /**
     * need to pass turing test
     */
    NEED_TURING_TEST("Need-Turing-Test"),

    /**
     * turing data
     */
    TURING_DATA("Turing-Data"),

    /**
     * content
     */
    CONTENT_DISPOSITION(HttpHeaders.CONTENT_DISPOSITION),

    /**
     * REQ EXTRA
     */
    REQUEST_EXTRA("RequestExtra"),

    /**
     * RES EXTRA
     */
    RESPONSE_EXTRA("ResponseExtra");

    public final String name;

    ProHeader(String name) {
        this.name = name;
    }
}
