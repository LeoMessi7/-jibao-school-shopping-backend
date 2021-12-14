package com.t09.jibao.config;

/*
 * @Author Yuanhao Pei
 * @Description Alipay account configuration
 * @Date 2021/12/7
 */

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {
    // appID
    public static String app_id = "2021000118660468";
    // merchant private key
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJM+QZvjQvFjEwo9wl1W4xvy/cHRRdShLwGqRUL60ZYmS0KMf+C5Z61fY2ls/CEfw6lRlHXwrV/RUJ2AK9xcYIcFgAkMOAo8Pco1YW+Zc0ZZu5WCSR4BFuHmvaY0kd1VKsJ2pds0Y8hyFwAzbjN3KQ3kEF6f58ZyA5FzUj8W7mBlRctKgrWxtdOcAZd/Y1J6zi5ZpbuToME7yh9VMCnwCdx1IiyECcua4mYHzp/8Zg97389nAumqt5NpLgU1+vh7u/DNUDiFcrV4lkaPGXk5VJvH9WZmbe1Zq0uNRJEJyK91SnckdnlvvTUSljjXAzj4byW/NHof0qypCxntLxse8hAgMBAAECggEASjV133KgGQPA0VUfgpshAektwrk7xYWGHrXaQeqTjpJIc6VHHqI33vKRPE9vycEekrUIOjEu1DURxwQ931T0WqYhAxVJmO96xYDW1JBk9yMqCiKhjQELjGsJ3NIsvuUd1R1wi4OmrqBMNqCWdGkOH0sHEmLQkYrZw+dIh8tTNzhNba7qNQtWRB2P/sOJe00ztjY/hgPjRWgWAFPcvsfbRZhGapwBvbAnslb+6VvxhDpQ1HLhJkw8Tpe1d/4h/hb0GHn0EE6zt4vnUO9J4XFDOHyJdBtTv7ZO5w6LTKldryvGhQDTtPVCKAym7V+QRYMlpnwlP6HtZhxqtd8MMnbzQQKBgQDztnN3JEx4ONBgpGb0wAOBuS74zZdDP6U38Rn2Xj0qjm3RDYrzvZIFRQWMPdpjNFt+egwKNZ7GgmJgGj2OjUrozELBoJzds+dfzs+LCse57Vche+r+dHvufbgE9wrZLfl/p/bJk7Ccu29TkEtDjQuOhiKkPauyrryJwczeVBhFGQKBgQCQHrzVc8f9K0YXpWZW9BBZ6IUvAR6ChXyn+Yw8kHlOtVDLWvj2dHFSYqzsSxcH5pgCIgT8UA60FipOb7Ze1CkQKqbMoU46hkvwCHRPOYZpfh/reSM8IwKtvkiLPJUpYqDuB/zKOznbhuh6zjifSRaKbSZWnc+D+qMR4EmJqRxzSQKBgBXjZw9mRS/u+lgTq+ODc00tUpip7lHt32rczpYf9KJj1F0YOEiX2At9HXhw/bI3O5hqYbZxBmeRwkAbWzf4XKf34G7/e9c1lyw1qNI2D6YVOOQa+woLcC4FQIfBoEnNJUFf1nxYNV5+PwtkRD425rjQN0BFAay0oN5Gd53oM53RAoGANTFzJyGV1gar1zxwGnX4twaQabnzBd+5E9KShSQZ8ggQKw6Hx4dQ8ESFaK2KcZsVzeu6hVoTIzrvIIYw/K03/sSPTgXXdthsnMqyCy/DQZqQs3vBmoedjH0oD2Qr9dt2ZITEM+xKPZF8qx8EXR4/2JXSybNzjVXr7EQpQEsPuikCgYA8pcHIptDmFyQOpvUHXzcJev2KGRwEs5rGlDS+zK2OzemvAgegwQO1FjijtlbqLUQgGSe1p9QvOh2BF2yEqeaLRAxDKQw1gl1au3vVYiAHYt4Vz2yjwKhpQOC+/2Khz9F8IzG8d/AhJggcpPUnU0JXy+UHDZayxiZTVsgWePz3kQ==";    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    // notify url
    public static String notify_url = "http://35de-111-187-62-101.ngrok.io/notify_url";
    // return url
    public static String return_url = "http://localhost:8080/Infor";
    //request format, default: json
    public static String format = "json";
    // signature type
    public static String sign_type = "RSA2";
    // charset
    public static String charset = "UTF-8";
    // server
    public static String serverUrl = "https://openapi.alipaydev.com/gateway.do";

    public static String log_path = "D:/alipay";
    //application certification path
    public static String appCertPath = "jibao/src/main/resources/alipayCert/appCertPublicKey_2021000118660468.crt";
    //alipay public certification path
    public static String alipayCertPath = "jibao/src/main/resources/alipayCert/alipayCertPublicKey_RSA2.crt";
    //alipay root certification path
    public static String alipayRootCertPath = "jibao/src/main/resources/alipayCert/alipayRootCert.crt";

    public static Long alipayUid = Long.valueOf(0);

    //log
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
