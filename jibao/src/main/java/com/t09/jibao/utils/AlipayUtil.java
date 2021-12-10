package com.t09.jibao.utils;

/*
 * @Author Yuanhao Pei
 * @Description Alipay tools
 * @Date 2021/12/7
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.t09.jibao.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AlipayUtil {

    public static AlipayUtil instance;

    static {
        try {
            instance = new AlipayUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AlipayUtil getInstance() {
        return instance;
    }

    private AlipayUtil() throws Exception {
    }

    public AlipayClient alipayClient = createAlipayClient();

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    private AlipayClient createAlipayClient() throws Exception{
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        //设置网关地址
        certAlipayRequest.setServerUrl(AlipayConfig.serverUrl);
        //设置应用Id
        certAlipayRequest.setAppId(AlipayConfig.app_id);
        //设置应用私钥
        certAlipayRequest.setPrivateKey(AlipayConfig.merchant_private_key);
        //设置请求格式，固定值json
        certAlipayRequest.setFormat(AlipayConfig.format);
        //设置字符集
        certAlipayRequest.setCharset(AlipayConfig.charset);
        //设置签名类型
        certAlipayRequest.setSignType(AlipayConfig.sign_type);
        //设置应用公钥证书路径
        certAlipayRequest.setCertPath(AlipayConfig.appCertPath);
        //设置支付宝公钥证书路径
        certAlipayRequest.setAlipayPublicCertPath(AlipayConfig.alipayCertPath);
        //设置支付宝根证书路径
        certAlipayRequest.setRootCertPath(AlipayConfig.alipayRootCertPath);

        return new DefaultAlipayClient(certAlipayRequest);
    }

    /*** 转码*/
    public String getByte(String param) {
        try {
            return new String(param.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*** 校验签名*/
    public boolean rsaCertCheckV1(HttpServletRequest request) {
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        try {
            boolean signVerified = AlipaySignature.rsaCertCheckV1(params, AlipayConfig.alipayCertPath, AlipayConfig.charset, AlipayConfig.sign_type);
            return signVerified;
        } catch (AlipayApiException e) {
            log.debug("verify sigin error, exception is:{}", e);
            return false;
        }
    }
}
