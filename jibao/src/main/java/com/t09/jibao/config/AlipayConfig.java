package com.t09.jibao.config;

/**
 * @author Yuanhao Pei
 * @date 2021-12-5
 */


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000118660468";
    // 商户私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJM+QZvjQvFjEwo9wl1W4xvy/cHRRdShLwGqRUL60ZYmS0KMf+C5Z61fY2ls/CEfw6lRlHXwrV/RUJ2AK9xcYIcFgAkMOAo8Pco1YW+Zc0ZZu5WCSR4BFuHmvaY0kd1VKsJ2pds0Y8hyFwAzbjN3KQ3kEF6f58ZyA5FzUj8W7mBlRctKgrWxtdOcAZd/Y1J6zi5ZpbuToME7yh9VMCnwCdx1IiyECcua4mYHzp/8Zg97389nAumqt5NpLgU1+vh7u/DNUDiFcrV4lkaPGXk5VJvH9WZmbe1Zq0uNRJEJyK91SnckdnlvvTUSljjXAzj4byW/NHof0qypCxntLxse8hAgMBAAECggEASjV133KgGQPA0VUfgpshAektwrk7xYWGHrXaQeqTjpJIc6VHHqI33vKRPE9vycEekrUIOjEu1DURxwQ931T0WqYhAxVJmO96xYDW1JBk9yMqCiKhjQELjGsJ3NIsvuUd1R1wi4OmrqBMNqCWdGkOH0sHEmLQkYrZw+dIh8tTNzhNba7qNQtWRB2P/sOJe00ztjY/hgPjRWgWAFPcvsfbRZhGapwBvbAnslb+6VvxhDpQ1HLhJkw8Tpe1d/4h/hb0GHn0EE6zt4vnUO9J4XFDOHyJdBtTv7ZO5w6LTKldryvGhQDTtPVCKAym7V+QRYMlpnwlP6HtZhxqtd8MMnbzQQKBgQDztnN3JEx4ONBgpGb0wAOBuS74zZdDP6U38Rn2Xj0qjm3RDYrzvZIFRQWMPdpjNFt+egwKNZ7GgmJgGj2OjUrozELBoJzds+dfzs+LCse57Vche+r+dHvufbgE9wrZLfl/p/bJk7Ccu29TkEtDjQuOhiKkPauyrryJwczeVBhFGQKBgQCQHrzVc8f9K0YXpWZW9BBZ6IUvAR6ChXyn+Yw8kHlOtVDLWvj2dHFSYqzsSxcH5pgCIgT8UA60FipOb7Ze1CkQKqbMoU46hkvwCHRPOYZpfh/reSM8IwKtvkiLPJUpYqDuB/zKOznbhuh6zjifSRaKbSZWnc+D+qMR4EmJqRxzSQKBgBXjZw9mRS/u+lgTq+ODc00tUpip7lHt32rczpYf9KJj1F0YOEiX2At9HXhw/bI3O5hqYbZxBmeRwkAbWzf4XKf34G7/e9c1lyw1qNI2D6YVOOQa+woLcC4FQIfBoEnNJUFf1nxYNV5+PwtkRD425rjQN0BFAay0oN5Gd53oM53RAoGANTFzJyGV1gar1zxwGnX4twaQabnzBd+5E9KShSQZ8ggQKw6Hx4dQ8ESFaK2KcZsVzeu6hVoTIzrvIIYw/K03/sSPTgXXdthsnMqyCy/DQZqQs3vBmoedjH0oD2Qr9dt2ZITEM+xKPZF8qx8EXR4/2JXSybNzjVXr7EQpQEsPuikCgYA8pcHIptDmFyQOpvUHXzcJev2KGRwEs5rGlDS+zK2OzemvAgegwQO1FjijtlbqLUQgGSe1p9QvOh2BF2yEqeaLRAxDKQw1gl1au3vVYiAHYt4Vz2yjwKhpQOC+/2Khz9F8IzG8d/AhJggcpPUnU0JXy+UHDZayxiZTVsgWePz3kQ==";    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问，测试修改springboot端口和外网地址
    public static String notify_url = "http://localhost:8080/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网（通过netapp获取net123或自己购买生成）可以正常访问
    public static String return_url = "http://localhost:8080/return_url";
    //请求格式，固定值json
    public static String format = "json";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "D:/alipay";
    //应用公钥证书路径
    public static String appCertPath = "D:/JiBao-backend/jibao/src/main/resources/alipayCert/appCertPublicKey_2021000118660468.crt";
    //支付宝公钥证书路径
    public static String alipayCertPath = "D:/JiBao-backend/jibao/src/main/resources/alipayCert/alipayCertPublicKey_RSA2.crt";
    //支付宝根证书路径
    public static String alipayRootCertPath = "D:/JiBao-backend/jibao/src/main/resources/alipayCert/alipayRootCert.crt";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
