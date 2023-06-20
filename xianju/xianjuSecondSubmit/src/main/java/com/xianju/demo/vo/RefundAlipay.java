package com.xianju.demo.vo;

import lombok.Data;
@Data
public class RefundAlipay {
    private String out_trade_no;//编号
    private String total_amount;//金额
    private String subject;//名称
    private String product_code;

}
