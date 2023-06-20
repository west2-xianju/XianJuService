package com.xianju.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.easysdk.factory.Factory;
import com.xianju.demo.Util.CaptchaUtils;
import com.xianju.demo.config.AliPayConfig;
import com.xianju.demo.service.IWalletService;
import com.xianju.demo.vo.AliPay;
import com.xianju.demo.vo.RefundAlipay;
import freemarker.template.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@RestController
public class WalletController {
    private static final String FORMAT="json";
    private static final String CHARSET="UTF-8";
    private static final String SIGN_TYPE="RSA2";

    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    private static final String GATEWAY_URL2 = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    @Autowired
    private IWalletService walletService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/wallet")
    public Result<?> getBalance(@RequestHeader String token) {
        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = walletService.getBalance(keyWord.getClaim("userid").asInt());
                return Result.success(data);
            }
            return Result.fail(402, "账号被封锁");
        }
        return Result.fail(401, "未接收到token");
    }

    /*//测试时候用的钱包提现(未整合支付宝)
    @PostMapping("/wallet/withdraw/{money}")
    public Result<?> withdraw(@PathVariable BigDecimal money, @RequestHeader String token) {
        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = walletService.withdraw(keyWord.getClaim("userid").asInt(), money);
                return Result.success(data);
            }
            return Result.fail(402, "账号被封锁");
        }
        return Result.fail(401, "未接收到token");
    }

    //测试时候用的钱包充值(未整合支付宝)
    @PostMapping("/wallet/toup/{money}")
    public Result<?> toUp(@PathVariable BigDecimal money, @RequestHeader String token) {
        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = walletService.toUp(keyWord.getClaim("userid").asInt(), money);
                return Result.success(data);
            }
            return Result.fail(402, "账号被封锁");
        }
        return Result.fail(401, "未接收到token");
    }*/

    //fiwjfm6983@sandbox.com    沙箱买家账号
    //111111    沙箱买家支付密码




    @Resource
    private AliPayConfig aliPayConfig;


    @GetMapping("/wallet/toup/{money}")
    public void pay(@PathVariable BigDecimal money, @RequestHeader String token, HttpServletResponse httpResponse) throws Exception {
        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                AliPay aliPay = new AliPay();
                aliPay.setSubject(keyWord.getClaim("userid").asString());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateString = formatter.format(date);
                aliPay.setTraceNo(dateString);
                aliPay.setTotalAmount(String.valueOf(money));
                redisTemplate.opsForValue().set(token+"1",aliPay.getTraceNo(),7, TimeUnit.DAYS);
                // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
                AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                        aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

                // 2. 创建 Request并设置Request参数
                AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();  // 发送请求的 Request类
                request.setNotifyUrl(aliPayConfig.getNotifyUrl());
                //传json.toString
                String alipayJson = JSON.toJSONString(aliPay);
                request.setBizContent(alipayJson);

                // 执行请求，拿到响应的结果，返回给浏览器
                String form = "";
                try {
                    form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
                httpResponse.setContentType("text/html;charset=" + CHARSET);
                httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
                httpResponse.getWriter().flush();
                httpResponse.getWriter().close();
                //TODO:整合支付宝后在回调时再调用Service
                walletService.toUp(keyWord.getClaim("userid").asInt(), money);
            }
        }else System.out.println("支付失败");
    }

    @PostMapping("/alipay/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }
            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                //TODO: 更新钱包
                //walletService.toUp(Integer.parseInt(params.get("buyer_id")), BigDecimal.valueOf(Long.parseLong(params.get("buyer_pay_amount"))));
            }
        }
        return "success";
    }

    @GetMapping("/wallet/withdraw/{money}")
    public Result<?> withdraw(@PathVariable BigDecimal money, @RequestHeader String token) throws AlipayApiException {

        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                String TraceNo = (String) redisTemplate.opsForValue().get(token+"1");
                // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
                AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL2,
                        aliPayConfig.getAppId(), aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET,
                        aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
                // 2. 创建 Request，设置参数
                RefundAlipay refundAlipay = new RefundAlipay();
                refundAlipay.setSubject(keyWord.getClaim("userid").asString());
                refundAlipay.setOut_trade_no(TraceNo);
                refundAlipay.setTotal_amount(String.valueOf(money.doubleValue()));
                refundAlipay.setProduct_code("FAST_INSTANT_TRADE_PAY");
                AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
                String alipayJson = JSON.toJSONString(refundAlipay);
                request.setBizContent(alipayJson);
                // 3. 执行请求
                AlipayTradeRefundResponse response = alipayClient.execute(request);
                if (response.isSuccess()) {  // 提现成功，isSuccess 为true
                    System.out.println("调用成功");
                    // 4. 更新数据库状态
                    walletService.withdraw(keyWord.getClaim("userid").asInt(), money);
                    return Result.success("提现成功");
                } else {   // 提现失败，isSuccess 为false
                    System.out.println(response.getBody());
                    return Result.fail(440, "提现失败");
                }
            }
        }return Result.fail(401, "未接收到token");
    }
}


