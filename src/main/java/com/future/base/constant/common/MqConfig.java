package com.future.base.constant.common;

public class MqConfig {
    public static final String EXCHANGE_DIRECT = "1buy.direct";
    public static final String EXCHANGE_TOPIC = "1buy.topic";
    public static final String EXCHANGE_ORDER_FINISH = "1buy.order.finish";


    //后台配置新游戏开始
    public static final String QUEUE_GAME_START = "game_start";

    //订单完成 成功、失败
    public static final String QUEUE_GAME_ORDER_FINISH = "game_order_finish";

    //支付结果
    public static final String QUEUE_ORDER_PAY_RESULT = "order_pay_result";


    public static final String ROUTING_GAME_ADMIN_START = "game_admin_start";
    public static final String ROUTING_ORDER_PAY_RESULT = "order_pay_result";
}
