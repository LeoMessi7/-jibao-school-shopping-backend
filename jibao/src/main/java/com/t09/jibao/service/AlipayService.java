package com.t09.jibao.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public interface AlipayService {

    public boolean transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException;

    public String goAlipay(AlipayTradePagePayModel model) throws IOException, AlipayApiException;

    public String queryPay(AlipayTradePagePayModel model) throws IOException, AlipayApiException;

    public String close(AlipayTradeCloseModel model) throws AlipayApiException;
}
