package com.future.base.util.base;

import com.future.base.constant.auth.CredentialType;
import com.future.base.constant.auth.DeviceType;
import com.future.base.constant.common.*;
import com.future.base.constant.content.ContentType;
import com.future.base.constant.material.MaterialType;
import com.future.base.constant.pay.PayScene;
import com.future.base.constant.pay.PaymentStatus;
import com.future.base.constant.pay.PaymentType;
import com.future.base.constant.prize.PrizeState;
import com.future.base.constant.prize.Source;
import com.future.base.constant.product.SaleType;
import com.future.base.constant.receive.ReceivingMethod;
import com.future.base.constant.user.Gender;
import com.future.base.constant.user.SourceType;
import com.future.base.constant.verify.BusinessVerifyType;
import com.future.base.model.exps.ProException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Map;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static com.future.base.constant.common.ResponseElement.INVALID_IDENTITY;
import static com.future.base.util.base.ProChecker.isNotNull;
import static com.future.base.util.base.ProChecker.isNull;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Constant Constraint Checker
 *
 * @author liuyunfei
 */
@SuppressWarnings({"JavaDoc", "unused", "AliControlFlowStatementWithoutBraces"})
public final class ConstantProcessor {

    //<editor-fold desc="constant mappings">
    /**
     * http response status and response element mapping
     */
    private static final Map<Integer, ResponseElement> RESPONSE_ELEMENT_MAPPING =
            of(ResponseElement.values()).collect(toMap(e -> e.status, e -> e, (a, b) -> a));

    /**
     * http method name and method mapping
     */
    private static final Map<String, HttpMethod> HTTP_METHOD_MAPPING =
            of(ProHttpMethod.values()).collect(toMap(bhm -> bhm.value.toUpperCase(), bhm -> bhm.method));

    /**
     * media type identity and media mapping
     */
    private static final Map<String, MediaType> MEDIA_TYPE_MAPPING =
            of(ProMediaType.values()).collect(toMap(t -> t.identity.toLowerCase(), t -> t.mediaType));

    /**
     * valid gender type identity and type mapping
     */
    private static final Map<Integer, Gender> GENDER_MAPPING =
            of(Gender.values()).collect(toMap(g -> g.identity, g -> g, (a, b) -> a));

    /**
     * valid source type identity and type mapping
     */
    private static final Map<String, SourceType> SOURCE_TYPE_MAPPING =
            of(SourceType.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * valid status type identity and type mapping
     */
    private static final Map<Integer, Status> STATUS_MAPPING =
            of(Status.values()).collect(toMap(s -> s.status, s -> s, (a, b) -> a));

    /**
     * valid sort type identity and type mapping
     */
    private static final Map<String, SortType> SORT_TYPE_MAPPING =
            of(SortType.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * valid Material type identity and type mapping
     */
    private static final Map<Integer, MaterialType> MATERIAL_TYPE_MAPPING =
            of(MaterialType.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * valid sale type identity and type mapping
     */
    private static final Map<Integer, SaleType> SALE_TYPE_MAPPING =
            of(SaleType.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * state identity and type mapping
     */
    private static final Map<Integer, PrizeState> STATE_MAPPING =
            of(PrizeState.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * source identity and source mapping
     */
    private static final Map<Integer, Source> SOURCE_MAPPING =
            of(Source.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * content type identity and content type mapping
     */
    private static final Map<Integer, ContentType> CONTENT_TYPE_MAPPING =
            of(ContentType.values()).collect(toMap(t -> t.identity, t -> t, (a, b) -> a));

    /**
     * valid credential type identity and type mapping
     */
    private static final Map<String, CredentialType> CREDENTIAL_TYPE_MAPPING =
            of(CredentialType.values()).collect(toMap(e -> e.identity.toUpperCase(), e -> e, (a, b) -> a));

    /**
     * valid device type identity and type mapping
     */
    private static final Map<String, DeviceType> DEVICE_TYPE_MAPPING =
            of(DeviceType.values()).collect(toMap(e -> e.identity.toUpperCase(), e -> e, (a, b) -> a));

    /**
     * business verify type identity and type mapping
     */
    private static final Map<Integer, BusinessVerifyType> BUSINESS_VERIFY_TYPE_MAPPING =
            of(BusinessVerifyType.values()).collect(toMap(e -> e.type, e -> e, (a, b) -> a));

    /**
     * receiving method identity and receiving method mapping
     */
    private static final Map<Integer, ReceivingMethod> RECEIVING_METHOD_MAPPING =
            of(ReceivingMethod.values()).collect(toMap(e -> e.identity, e -> e, (a, b) -> a));

    /**
     * payment type identity and payment type mapping
     */
    private static final Map<Integer, PaymentType> PAYMENT_TYPE_MAPPING =
            of(PaymentType.values()).collect(toMap(e -> e.type, e -> e, (a, b) -> a));

    /**
     * pay scene identity and pay scene mapping
     */
    private static final Map<Integer, PayScene> PAY_SCENE_MAPPING =
            of(PayScene.values()).collect(toMap(e -> e.type, e -> e, (a, b) -> a));

    /**
     * payment status identity and payment status mapping
     */
    private static final Map<Integer, PaymentStatus> PAYMENT_STATUS_MAPPING =
            of(PaymentStatus.values()).collect(toMap(e -> e.status, e -> e, (a, b) -> a));


    //PaymentType
    //</editor-fold>

    //<editor-fold desc="asserter">

    /**
     * assert http method
     *
     * @param value
     * @return
     */
    public static void assertHttpMethod(String value, boolean nullable) {
        if (nullable && isNull(value))
            return;

        if (isBlank(value) || !HTTP_METHOD_MAPPING.containsKey(value.toUpperCase()))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert media type
     *
     * @param identity
     * @return
     */
    public static void assertMediaType(String identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isBlank(identity) || !MEDIA_TYPE_MAPPING.containsKey(identity.toLowerCase()))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert gender type
     *
     * @param identity
     */
    public static void assertGenderIdentity(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (!GENDER_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert source type
     *
     * @param identity
     */
    public static void assertSourceType(String identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (!SOURCE_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert status type
     *
     * @param identity
     */
    public static void assertStatus(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (!STATUS_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert sort type
     *
     * @param identity
     */
    public static void assertSortType(String identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isBlank(identity) || !SORT_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert material type
     *
     * @param identity
     */
    public static void assertMaterialType(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !MATERIAL_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert sale type
     *
     * @param identity
     */
    public static void assertSaleType(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !SALE_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert prize state
     *
     * @param identity
     */
    public static void assertState(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !STATE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert prize source
     *
     * @param identity
     */
    public static void assertSource(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !SOURCE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert content type
     *
     * @param identity
     */
    public static void assertContentType(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !CONTENT_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert credential type
     *
     * @param identity
     */
    public static void assertCredentialType(String identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isBlank(identity) || !CREDENTIAL_TYPE_MAPPING.containsKey(identity.toUpperCase()))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert device type
     *
     * @param identity
     */
    public static void assertDeviceType(String identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isBlank(identity) || !DEVICE_TYPE_MAPPING.containsKey(identity.toUpperCase()))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert verify type
     *
     * @param identity
     */
    public static void assertBusinessVerifyType(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !BUSINESS_VERIFY_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert receiving method
     *
     * @param identity
     */
    public static void assertReceivingMethod(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !RECEIVING_METHOD_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert payment type
     *
     * @param identity
     */
    public static void assertPaymentType(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !PAYMENT_TYPE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert payment scene
     *
     * @param identity
     */
    public static void assertPayScene(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !PAY_SCENE_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }

    /**
     * assert payment status
     *
     * @param identity
     */
    public static void assertPaymentStatus(Integer identity, boolean nullable) {
        if (nullable && isNull(identity))
            return;

        if (isNull(identity) || !PAYMENT_STATUS_MAPPING.containsKey(identity))
            throw new ProException(INVALID_IDENTITY);
    }
    //</editor-fold>

    //<editor-fold desc="getter">

    /**
     * get element by http status
     *
     * @param identity
     * @return
     */
    public static ResponseElement getResponseElementByStatus(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        ResponseElement responseElement = RESPONSE_ELEMENT_MAPPING.get(identity);
        return isNotNull(responseElement) ? responseElement : BAD_REQUEST;
    }

    /**
     * get http method by value
     *
     * @param value
     * @return
     */
    public static HttpMethod getHttpMethodByValue(String value) {
        if (isBlank(value))
            throw new ProException(INVALID_IDENTITY);

        HttpMethod httpMethod = HTTP_METHOD_MAPPING.get(value.toUpperCase());
        if (isNull(httpMethod))
            throw new ProException(INVALID_IDENTITY);

        return httpMethod;
    }

    /**
     * get media type by identity
     *
     * @param identity
     * @return
     */
    public static MediaType getMediaTypeByIdentity(String identity) {
        if (isBlank(identity))
            throw new ProException(INVALID_IDENTITY);

        MediaType mediaType = MEDIA_TYPE_MAPPING.get(identity.toLowerCase());
        if (isNull(mediaType))
            throw new ProException(INVALID_IDENTITY);

        return mediaType;
    }

    /**
     * get gender type by identity
     *
     * @param identity
     * @return
     */
    public static Gender getGenderTypeByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        Gender gender = GENDER_MAPPING.get(identity);
        if (isNull(gender))
            throw new ProException(INVALID_IDENTITY);

        return gender;
    }

    /**
     * get source type by identity
     *
     * @param identity
     * @return
     */
    public static SourceType getSourceTypeByIdentity(String identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        SourceType sourceType = SOURCE_TYPE_MAPPING.get(identity);
        if (isNull(sourceType))
            throw new ProException(INVALID_IDENTITY);

        return sourceType;
    }

    /**
     * get status type by identity
     *
     * @param identity
     * @return
     */
    public static Status getStatusByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        Status status = STATUS_MAPPING.get(identity);
        if (isNull(status))
            throw new ProException(INVALID_IDENTITY);

        return status;
    }

    /**
     * get sort type by identity
     *
     * @param identity
     * @return
     */
    public static SortType getSortTypeByIdentity(String identity) {
        if (isBlank(identity))
            throw new ProException(INVALID_IDENTITY);

        SortType sortType = SORT_TYPE_MAPPING.get(identity);
        if (isNull(sortType))
            throw new ProException(INVALID_IDENTITY);

        return sortType;
    }

    /**
     * get material type by identity
     *
     * @param identity
     * @return
     */
    public static MaterialType getMaterialTypeByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        MaterialType materialType = MATERIAL_TYPE_MAPPING.get(identity);
        if (isNull(materialType))
            throw new ProException(INVALID_IDENTITY);

        return materialType;
    }

    /**
     * get sale type by identity
     *
     * @param identity
     * @return
     */
    public static SaleType getSaleTypeByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        SaleType saleType = SALE_TYPE_MAPPING.get(identity);
        if (isNull(saleType))
            throw new ProException(INVALID_IDENTITY);

        return saleType;
    }

    /**
     * get state by identity
     *
     * @param identity
     * @return
     */
    public static PrizeState getStateByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        PrizeState prizeState = STATE_MAPPING.get(identity);
        if (isNull(prizeState))
            throw new ProException(INVALID_IDENTITY);

        return prizeState;
    }

    /**
     * get source by identity
     *
     * @param identity
     * @return
     */
    public static Source getSourceByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        Source source = SOURCE_MAPPING.get(identity);
        if (isNull(source))
            throw new ProException(INVALID_IDENTITY);

        return source;
    }

    /**
     * get content type by identity
     *
     * @param identity
     * @return
     */
    public static ContentType getContentTypeByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        ContentType contentType = CONTENT_TYPE_MAPPING.get(identity);
        if (isNull(contentType))
            throw new ProException(INVALID_IDENTITY);

        return contentType;
    }

    /**
     * get credential type by identity
     *
     * @param identity
     * @return
     */
    public static CredentialType getCredentialTypeByIdentity(String identity) {
        if (isBlank(identity))
            throw new ProException(INVALID_IDENTITY);

        CredentialType credentialType = CREDENTIAL_TYPE_MAPPING.get(identity.toUpperCase());
        if (isNull(credentialType))
            throw new ProException(INVALID_IDENTITY);

        return credentialType;
    }

    /**
     * get device type by identity
     *
     * @param identity
     * @return
     */
    public static DeviceType getDeviceTypeByIdentity(String identity) {
        if (isBlank(identity))
            throw new ProException(INVALID_IDENTITY);

        DeviceType deviceType = DEVICE_TYPE_MAPPING.get(identity.toUpperCase());
        if (isNull(deviceType))
            throw new ProException(INVALID_IDENTITY);

        return deviceType;
    }

    /**
     * get business verify type by identity
     *
     * @param identity
     * @return
     */
    public static BusinessVerifyType getBusinessVerifyTypeByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        BusinessVerifyType businessVerifyType = BUSINESS_VERIFY_TYPE_MAPPING.get(identity);
        if (isNull(businessVerifyType))
            throw new ProException(INVALID_IDENTITY);

        return businessVerifyType;
    }

    /**
     * get receiving method by identity
     *
     * @param identity
     * @return
     */
    public static ReceivingMethod getReceivingMethodByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        ReceivingMethod receivingMethod = RECEIVING_METHOD_MAPPING.get(identity);
        if (isNull(receivingMethod))
            throw new ProException(INVALID_IDENTITY);

        return receivingMethod;
    }

    /**
     * get payment type by identity
     *
     * @param identity
     * @return
     */
    public static PaymentType getPaymentTypeByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        PaymentType paymentType = PAYMENT_TYPE_MAPPING.get(identity);
        if (isNull(paymentType))
            throw new ProException(INVALID_IDENTITY);

        return paymentType;
    }

    /**
     * get pay scene by identity
     *
     * @param identity
     * @return
     */
    public static PayScene getPaySceneByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        PayScene payScene = PAY_SCENE_MAPPING.get(identity);
        if (isNull(payScene))
            throw new ProException(INVALID_IDENTITY);

        return payScene;
    }

    /**
     * get payment status by identity
     *
     * @param identity
     * @return
     */
    public static PaymentStatus getPaymentStatusByIdentity(Integer identity) {
        if (isNull(identity))
            throw new ProException(INVALID_IDENTITY);

        PaymentStatus paymentStatus = PAYMENT_STATUS_MAPPING.get(identity);
        if (isNull(paymentStatus))
            throw new ProException(INVALID_IDENTITY);

        return paymentStatus;
    }
    //</editor-fold>

}
