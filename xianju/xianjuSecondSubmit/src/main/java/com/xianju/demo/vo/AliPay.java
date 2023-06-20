package com.xianju.demo.vo;

import lombok.Data;

//测试支付宝时订单实体类
@Data
public class AliPay {
    private String traceNo;//订单编号
    private String totalAmount;//交易金额
    private String subject;//货物名称
    private String alipayTraceNo;
}

