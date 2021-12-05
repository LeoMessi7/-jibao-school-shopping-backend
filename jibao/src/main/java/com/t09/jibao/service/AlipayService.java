package com.t09.jibao.service;

/**
 * @author Yuanhao Pei
 * @date 2021-12-5
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.t09.jibao.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AlipayService {
    @RequestMapping("/order/test")
    public String test(){
        return "SynNotify";
    }
    //支付宝支付确认付款跳转页面
    @RequestMapping("/order/confirm")
    public void orderConfirm(HttpServletRequest request, HttpServletResponse response) {

        //虚拟一个订单，将其信息定义如下
        String title = "支付订单";
        String total = "1";
        String message = "备注";

        //生成订单号
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
        Map<String,String> map=new LinkedHashMap<>();

        //向支付宝发送请求

        //初始化AlipClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAYURL,
                AlipayConfig.APP_ID,AlipayConfig.RSA_PRIVATE_KEY,AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderSn;

        //付款金额，必填
        String total_amount = total;

        //订单名称，必填
        String subject = title;

        //商品描述，可空
        String body = message;

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
                + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        AlipayTradePagePayResponse alipayResponse = null;

        try {
            alipayResponse = alipayClient.pageExecute(alipayRequest);
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(alipayResponse.getBody());//直接将完整的表单html输出到页面
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("error");
            e.printStackTrace();
        } finally {
        }

    }

    //支付宝异步通知
    @RequestMapping("/pay/synnotify")
    public String synNotify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {

        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            //乱码解决，这段代码在出现乱码时使用
//           valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//           params.put(name, valueStr);

        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

   /* 实际验证过程建议商户务必添加以下校验：
   1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
   2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
   3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
   4、验证app_id是否为该商户本身。
   */
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            System.out.println("success");

        } else {//验证失败
            System.out.println("fail");
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
        return "SynNotify";
    }

    //支付宝同步通知
    @RequestMapping("/pay/return_notify")
    public String returnNotify(){
        return "return_notify";
    }

}
