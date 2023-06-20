package com.xianju.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.easysdk.factory.Factory;
import com.xianju.demo.config.AliPayConfig;
import com.xianju.demo.vo.AliPay;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


//支付宝测试controller
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    //fiwjfm6983@sandbox.com    沙箱买家账号
    //111111    沙箱买家支付密码

    private static final String FORMAT="json";
    private static final String CHARSET="UTF-8";
    private static final String SIGN_TYPE="RSA2";

    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    private static final String SERVER_URL="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String APPID="2021000122669896";
    private static final String PRIVATE_KEY="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfWCcbkOjk7zVqeCMdstnevV6GltzYCZIb4DVnuFdYHhFsmxMZVh7bSGcoxRa+A+NmbSjLTe6Yb8WlK1Mf6wmUz1QWNm1WqANh98vpoCnCvHxDUSF0GmhWwyDY22CTupvkf9wYGOcqPzAmHbst72QtNayM1jsOvKy69RDqkvZVxBeUtZEHb+kIeBUa6Rr7dHxcWQMn10Iwh2fovi2z2b99EGZ+f5eaXe7PvthD+amJq/a19pwsLW2MixvQL8lfuivXlvKcFYdyYMGrj6tCKijHW/VKId0IBE4QsWbP+p0RoGDL7foQxwVremsj3xS8N+PuxqUGJrAxgmNkJ4l3qvRDAgMBAAECggEAZUiKKypz7rjh8hgE8iax97PU9qXEIIoM1i0wpQn2cutB1g/IFw23tViTQcXtC8C69WsjR/70Tu6U6cTdJGeogm86mpfj7fYDmG0CrzB/+ukF72ANbJgwhB2prk3bWGuAFcDz7Fk/7omVmFTDmpIU0evRDwBJL7fb70ZAh2n8rExhaMGkeuDFz5k2R0Z58IF4n234z/ZjI2hqmiCFmsWidLl1ePrNE1Q+1M8mOCysEehUk70w4ChJx7DJn3fQ7HBqb95yUcjvOLx0HWxiXGDxvnf1nmc6B/ybbEc3yCUkup8bgjn1JKTd099tw+TFWsdrs9KFAngezJ84G96CYV4YwQKBgQDsQWof5j3HSYyDf9e/YRJr5wSSfFyV5u7KSblRI5eMNfmCkccKVjkCGYqwfq3OdZ1QDKz+UloLeFPfkOC4QSAlORxoMRWal7Pb+dDo0w/QsEFXHw2kgDaaikmhal0G4zTe+i8MXzrx7kLGf0A3w/1ibUpwh2DzqaMdIbIdjewMIwKBgQCsqUGwCxhnIBdt70ezVEFNX1TrAEdYhTvuKnnzt7kZwOeRwGLY7YjnUmVft3awGY4Pp8uv2UP2KMZd1Jpmt7TrQ5aqymfn4ODL2PAHWapw+HVkMj1GlwQDFAj4rBEziVFk9ukVdonqizPosHpaBpLGnBASOORYXKUnFVQ6gsBpYQKBgE7KAfphbnjnrlSsbecFGH+jq4KutrHHMhuEOqk0lXXNCkjhFN/9oUVx/y6dEJmp36yyjX7DOARbPJ1yU38fOPkw8qhMtke4D+NsjJ8YxE10elXzpTkXCR7Av411S1DgtOEnzkjx+VZ1fw9zoJb4ZahNimW8wDiCekNc2WnBytbBAoGAew1prkBKFP0PFZ9p10md/LmLeKxLLLBPxO5q2aI/fjY4E20sRXQMlOfpLa1AHkMoNsocZWgPgnkO3sJtg/U7MEyMLY5xPRXMwBsM/gAOc9LvPwh7GTr5dgQqXKes3rDn3HAIL+66Xfj1dsmHeW+j3X5OCd6YBs2IOnF+BPE4liECgYBx6kkrXOuXez2p6Ah8Q0ljGHFp8SnLJ7dUXx9ipNDCiZYz2iNGUTq8SeXiBGLQUsteeyIwPv/wMwJZHY1LuSk1+NMc3fCQzP+o7YX0vvyLkTalcZ1RJJwUT3HMb42tPTrVlx/yZdv6RkMgVBeBbtTxHqeiu5BCleOGJbuZ79oRpw==";

    private static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkETQvM9JincqHHgeo9QW3j/YT2PZuiOb95bfRdngb+Rep8HHthAKB7e5vC8rosZCZGDuNy+vvIeuHGn7H597oZGdETONVtKBtIJ13lL0pppv29teQzY6Z9PfbV9wW4i9KiZoaxU4pZcVjhi6tqzF5BOAZzCvxf4sAjO2VB51AbPVrrbcomXJ9j0QNabQdQsTD/GaGhrx94o0HESPRBom5NLmCq6VC0D0jYZVVMuuJoCp+CnpCHKqN+Y2aEITt1E3TzjXFBC7/rrtJwDi3nu4Xv/WUUZ0/wwAroDjWou5qCYRXcD06dSWNpBDWJ29zRKWpgO8HkiyuRVrZS8nke40VQIDAQAB";
    private static final String NOTIFY_URL="http://umvjfr.natappfree.cc/alipay/notify";//穿透后更改配置


    @GetMapping("/pays") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public String pay(AliPay aliPay) {
        return getString(aliPay);
    }

    static String getString(AliPay aliPay) {
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,APPID,PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("XianJuToUpAccount");//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
        model.setSubject(aliPay.getSubject());//商品的标题/交易标题/订单标题/订单关键字等
        model.setOutTradeNo(aliPay.getTraceNo());//商户网站唯一订单号
        model.setTimeoutExpress("30m");//该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        //注：若为空，则默认为15d。
        model.setTotalAmount(aliPay.getTotalAmount());//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        request.setBizModel(model);
        request.setNotifyUrl(NOTIFY_URL);
        AlipayTradeAppPayResponse response;
        try {
            //  发起API调用
            //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            if (response != null) {
                System.out.println("调用成功");
            }
            System.out.println(response.getBody());//orderString 可以直接给客户端请求，无需再做处理。
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return response.getBody();
    }

    @PostMapping("/notifys")  // 支付宝异步回调
    public String payNotifys(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("==============支付宝异步回调=============");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 更新订单未已支付
              //  walletService.toUp(tradeNo, BigDecimal.valueOf(params.get("total_amount")));
            }
        }
        return "success";
    }


}




