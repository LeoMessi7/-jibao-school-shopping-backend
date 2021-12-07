package com.t09.jibao.Controller;

/**
 * @author Yuanhao Pei
 * @date 2021-12-5
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.t09.jibao.config.AlipayConfig;
import com.t09.jibao.service.AlipayService;
import com.t09.jibao.utils.AlipayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author Yuanhao Pei
 * @descibe 支付宝支付
 * @date 2021/19:38
 */
@Slf4j
@RestController
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/paySuccess")
    public String paySuccess() {
        return "支付成功!";
    }

    @RequestMapping("/payFail")
    public String payFail() {
        return "支付失败!";
    }


    /**
     * 支付宝转账接口
     *
     * @param outBizNo     商户端的唯一订单号，对于同一笔转账请求，商户需保证该订单号唯一。
     * @param transAmount  订单总金额，单位为元，精确到小数点后两位，（单笔最低转账0.1元）
     * @param payeeAccount 收钱的账号
     * @throws AlipayApiException
     * @return
     */
    @RequestMapping(value = "/transfer")
    public boolean transfer(@RequestParam(value = "outBizNo") String outBizNo,
                            @RequestParam(value = "payeeAccount") String payeeAccount,
                            @RequestParam(value = "transAmount") String transAmount)
            throws AlipayApiException {
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();

        model.setAmount(transAmount);
        model.setOutBizNo(outBizNo);
        model.setPayeeType("ALIPAY_LOGONID");
        model.setPayeeAccount(payeeAccount);
        model.setPayerShowName("JiBao");
        model.setRemark("支付测试");

        if (alipayService.transfer(model)) {
            System.out.println("支付成功outBizNo为+" + outBizNo + "转账给" + payeeAccount + ",共" + transAmount + "元。");
            return true;
        } else {
            System.out.println("支付失败");
            return false;
        }
    }

    /*** 网站支付*/
    @RequestMapping(value = "/goAlipay", produces = "text/html; charset=UTF-8")
    public String goAlipay(@RequestParam(value = "outBizNo") String outBizNo,
                           @RequestParam(value = "subject") String subject,
                           @RequestParam(value = "amount") String amount)
            throws IOException, AlipayApiException {

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        model.setOutTradeNo(outBizNo);
        model.setSubject(subject);
        model.setTotalAmount(amount);
        model.setBody("支付测试");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        return alipayService.goAlipay(model);
    }

    /*** 同步回调*/
    @RequestMapping("/return_url")
    public ModelAndView return_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        log.info(">>>>>>>>支付成功, 进入同步通知接口...");
        boolean verifyResult = AlipayUtil.getInstance().rsaCertCheckV1(request);
        ModelAndView mv = null;
        if (verifyResult) {
            //商户订单号
            String out_trade_no = AlipayUtil.getInstance().getByte(request.getParameter("out_trade_no"));
            //支付宝交易号
            String trade_no = AlipayUtil.getInstance().getByte(request.getParameter("trade_no"));
            log.info("商户订单号：{}，支付宝交易号，{}", out_trade_no, trade_no);
            mv = new ModelAndView("paySuccess");
        } else {
            mv = new ModelAndView("payFail");
        }
        return mv;
    }

    /*** 异步回调*/
    @RequestMapping(value = "/notify_url", method = RequestMethod.POST)
    public String notify_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        log.info(">>>>>>>>支付成功, 进入异步通知接口...");

        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder(">>>>>>>>>> alipay notify >>>>>>>>>>>>>>\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n"));
        notifyBuild.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(notifyBuild.toString());

        boolean flag = AlipayUtil.getInstance().rsaCertCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = AlipayUtil.getInstance().getByte(request.getParameter("trade_status"));
            // 商户订单号
            String out_trade_no = AlipayUtil.getInstance().getByte(request.getParameter("out_trade_no"));
            //支付宝交易号
            String trade_no = AlipayUtil.getInstance().getByte(request.getParameter("trade_no"));
            //付款金额
            String total_amount = AlipayUtil.getInstance().getByte(request.getParameter("total_amount"));
            log.info("交易状态:{},商户订单号:{},支付宝交易号:{},付款金额:{}", tradeStatus, out_trade_no, trade_no, total_amount);
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if (tradeStatus.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是    可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }
            return "success";
        }
        return "fail";
    }

    /***查看支付流水*/
    @RequestMapping(value = "/queryPay")
    public String queryPay(@RequestParam("orderId") String orderId)
            throws IOException, AlipayApiException {
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(orderId);
        return alipayService.queryPay(model);
    }


    /**
     * 关闭交易
     *
     * @param orderNo
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("/close")
    public String close(@RequestParam(value = "orderNo") String orderNo)
            throws AlipayApiException {
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(orderNo);
        return alipayService.close(model);
    }
}

