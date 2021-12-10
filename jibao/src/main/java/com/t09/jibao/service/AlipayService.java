package com.t09.jibao.service;

/*
 * @Author Yuanhao Pei
 * @Date 2021/12/7
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradePagePayModel;

import java.io.IOException;

public interface AlipayService {

    public boolean transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException;

    public String goAlipay(AlipayTradePagePayModel model) throws IOException, AlipayApiException;

    public String queryPay(AlipayTradePagePayModel model) throws IOException, AlipayApiException;

    public String close(AlipayTradeCloseModel model) throws AlipayApiException;
}
