package com.future.base.constant.common;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * global exception info
 *
 * @author liuyunfei
 */
@SuppressWarnings("unused")
public enum ResponseElement {

    //<editor-fold desc="common">
    OK(HttpStatus.OK.value(), HttpStatus.OK.value(), "Success"),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS.value(), HttpStatus.TOO_MANY_REQUESTS.value(), "Too many requests"),
    PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT.value(), HttpStatus.PARTIAL_CONTENT.value(), "Fallback"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.value(), "Authentication failed or expired, please login"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.value(), "Insufficient permissions"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.value(), "Resource not found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.value(), "Illegal parameter"),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "Invalid media type"),
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE.value(), HttpStatus.PAYLOAD_TOO_LARGE.value(), "Request body too large or request header too larger"),
    NO_CONTENT(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.value(), "No content"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.value(), "You have been restricted access"),
    INTERNAL_SERVER_ERROR(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), "System busy"),
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT.value(), HttpStatus.REQUEST_TIMEOUT.value(), "Timeout"),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT.value(), HttpStatus.GATEWAY_TIMEOUT.value(), "Gateway timeout"),
    EMPTY_PARAM(HttpStatus.BAD_REQUEST.value(), 400000001, "Empty param"),
    INVALID_PARAM(HttpStatus.BAD_REQUEST.value(), 400000002, "Invalid param"),
    EMPTY_PATH_VARIABLE(HttpStatus.BAD_REQUEST.value(), 400000003, "Empty path variable"),
    INVALID_PATH_VARIABLE(HttpStatus.BAD_REQUEST.value(), 400000004, "Invalid path variable"),
    TOO_MANY_HEADERS(HttpStatus.BAD_REQUEST.value(), 400000005, "Too many headers"),
    TOO_LARGE_HEADER(HttpStatus.BAD_REQUEST.value(), 400000006, "Too large headers"),
    TOO_LARGE_BODY(HttpStatus.BAD_REQUEST.value(), 400000007, "Too large body"),
    TOO_LARGE_URI(HttpStatus.BAD_REQUEST.value(), 400000008, "Uri is too long"),
    INVALID_METADATA_PARAM(HttpStatus.BAD_REQUEST.value(), 400000009, "Metadata can't be null"),
    DECRYPTION_FAILED(HttpStatus.BAD_REQUEST.value(), 400000010, "Encryption/decryption or signature/verification failed"),
    INVALID_REQUEST_METHOD(HttpStatus.BAD_REQUEST.value(), 400000011, "Invalid request method"),
    INVALID_IDENTITY(HttpStatus.BAD_REQUEST.value(), 400000012, "Invalid or empty identity"),
    INVALID_DATA_STATUS(HttpStatus.BAD_REQUEST.value(), 400000013, "Invalid data"),
    DATA_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), 400000014, "Data not exist"),
    DATA_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400000015, "Data already exist"),
    DATA_HAS_NOT_CHANGED(HttpStatus.BAD_REQUEST.value(), 400000016, "Data has not changed"),
    VERIFY_IS_INVALID(HttpStatus.BAD_REQUEST.value(), 400000017, "Verify is invalid"),
    TIME_FORMAT_IS_INVALID(HttpStatus.BAD_REQUEST.value(), 400000018, "Time format is invalid"),
    UNSUPPORTED_OPERATE(HttpStatus.BAD_REQUEST.value(), 400000019, "Operate is not support"),
    NEED_TURING_TEST(HttpStatus.BAD_REQUEST.value(), 400000020, "Need to pass the Turing test"),
    DATA_NOT_BELONG_TO_YOU(HttpStatus.BAD_REQUEST.value(), 400000021, "Data not belong to you"),
    DATA_HAS_BEEN_FROZEN(HttpStatus.BAD_REQUEST.value(), 400000022, "Data has been frozen"),
    DATABASE_OPERATE_ERROR(HttpStatus.BAD_REQUEST.value(), 400000023, "DataBase Operate error"),
    //</editor-fold>


    //<editor-fold desc="user">
    MEMBER_NOT_HAS_A_ROLE(HttpStatus.BAD_REQUEST.value(), 400100001, "Member not has a role"),
    MEMBER_ALREADY_HAS_A_ROLE(HttpStatus.BAD_REQUEST.value(), 400100002, "Member already has a role"),
    PHONE_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400100003, "The phone number already exists"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400100004, "The email already exists"),
    NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400100005, "The name already exists"),
    //</editor-fold>


    //<editor-fold desc="auth">
    INVALID_ACCT_OR_PWD(HttpStatus.BAD_REQUEST.value(), 400200001, "Invalid account or password"),
    NO_AUTH_REQUIRED_RESOURCE(HttpStatus.BAD_REQUEST.value(), 400200002, "Resources do not require authentication access"),
    ROLE_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400200003, "Role name already exists"),
    ROLE_LEVEL_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400200004, "Role level already exists"),
    RESOURCE_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400200005, "Resource name already exists"),
    RESOURCE_FEATURE_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), 400200006, "Resource feature already exists"),
    RESOURCE_STILL_USED(HttpStatus.BAD_REQUEST.value(), 400200007, "Resource is still used by the following roles"),
    USER_ROLE_NO_MAPPING(HttpStatus.BAD_REQUEST.value(), 400200008, "User and Role No Mapping"),
    //</editor-fold>

    //biz begin
    ROLE_RESOURCE_MAPPING_NO_DELETE(HttpStatus.BAD_REQUEST.value(), 400300001, "Role and Resource mapping no delete"),
    ROLE_ADMIN_USER_MAPPING_NO_DELETE(HttpStatus.BAD_REQUEST.value(), 400300002, "Role and User mapping no delete"),
    ADMIN_ROLE_NO_DELETE(HttpStatus.BAD_REQUEST.value(), 400300003, "supper admin role no delete"),
    //biz end


    //<pay>
    WITHDRAW_MONEY_LIMIT(HttpStatus.BAD_REQUEST.value(), 400400001, "withdraw money limit"),
    WITHDRAW_NO_CARD(HttpStatus.BAD_REQUEST.value(), 400400002, "withdraw no card"),
    WALLET_PAY_ERROR(HttpStatus.BAD_REQUEST.value(), 400400003, "wallet pay error"),
    PAYBY_REQUEST_ERROR(HttpStatus.BAD_REQUEST.value(), 400400004, "payby request error"),
    PAYBY_RESPONSE_STATUS_ERROR(HttpStatus.BAD_REQUEST.value(), 400400005, "payby response status error"),
    PAYBY_RESPONSE_SIGN_ERROR(HttpStatus.BAD_REQUEST.value(), 400400006, "payby response sign error"),
    //</pay>


    //<game>
    MARKETING_FRAGMENT_LIMIT_ONE(HttpStatus.BAD_REQUEST.value(), 400500001, "marketing fragment limit 1"),
    MARKETING_FRAGMENT_USED(HttpStatus.BAD_REQUEST.value(), 400500002, "marketing fragment used"),
    GAME_ITEM_CAN_NOT_ALLOCATION(HttpStatus.BAD_REQUEST.value(), 400500003, "game item can not allocation"),
    ITEM_VALIDITY_PERIOD_ERROR(HttpStatus.BAD_REQUEST.value(), 400500004, "item validity period error"),
    ITEM_USING(HttpStatus.BAD_REQUEST.value(), 400500005, "item using"),
    //</game>

    ;
    /**
     * http status
     */
    public final int status;

    /**
     * business code
     */
    public final int code;

    /**
     * message
     */
    public final String message;

    ResponseElement(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    private static final Map<Integer, ResponseElement> map = Collections.unmodifiableMap(new HashMap<Integer, ResponseElement>() {
        {
            for (ResponseElement responseElement : ResponseElement.values()) {
                put(responseElement.status, responseElement);
            }
        }
    });

    public static ResponseElement get(Integer status) {
        return map.getOrDefault(status, BAD_REQUEST);
    }
}