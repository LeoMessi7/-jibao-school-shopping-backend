package com.t09.jibao.service.implement;

/*
 * @Author Yuanhao Pei
 * @Description Alipay service
 * @date 2021/12/7
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.t09.jibao.config.AlipayConfig;
import com.t09.jibao.service.AlipayService;
import com.t09.jibao.utils.AlipayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {


    @Override
    public boolean transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException{
        AlipayClient alipayClient = AlipayUtil.getInstance().getAlipayClient();

        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();


        request.setBizModel(model);
        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);

        AlipayFundTransToaccountTransferResponse response = alipayClient.certificateExecute(request);

        return response.isSuccess();
    }

    /** 网站支付 */
    @Override
    public String goAlipay(AlipayTradePagePayModel model) throws AlipayApiException {
        AlipayClient alipayClient = AlipayUtil.getInstance().getAlipayClient();

        AlipayTradePagePayRequest pagePayRequest = new AlipayTradePagePayRequest();
        pagePayRequest.setReturnUrl(AlipayConfig.return_url);
        pagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
        pagePayRequest.setBizModel(model);

        //请求
        String result = alipayClient.pageExecute(pagePayRequest).getBody();
        return result;
    }

    /***查看支付流水*/
    @Override
    public String queryPay(AlipayTradePagePayModel model) throws IOException, AlipayApiException{
        AlipayClient alipayClient = AlipayUtil.getInstance().getAlipayClient();
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizModel(model);
        //请求
        String result = alipayClient.certificateExecute(alipayRequest).getBody();
        return result;
    }

    /**
     * 关闭交易
     */
    @Override
    public String close(AlipayTradeCloseModel model) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();

        alipayRequest.setBizModel(model);

        AlipayTradeCloseResponse alipayResponse = AlipayUtil.getInstance().getAlipayClient().certificateExecute(alipayRequest);

        System.out.println(alipayResponse.getBody());
        return alipayResponse.getBody();
    }

}
