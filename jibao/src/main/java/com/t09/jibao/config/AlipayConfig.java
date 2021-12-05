package com.t09.jibao.config;

/**
 * @author Yuanhao Pei
 * @date 2021-12-5
 */

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2021000118660468";

    // 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
    public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC2rIIrLwBbVBZSZBDBTWdgSnZoXi6RVmkNdPRXpgX8s/B5ibA//ytxJlP5Tzei7wOZWyFnWDBl+EPC4Wc61wj96anvFPrrdT1/Gew4wiEIXnz0W/fr9tUFcCmSprp0MzCKuszY1Ynp30aR5zhiCSU/P9CbDkfd/vJt6e/9E+VtjBmGiC4uW52dOJ6Ap67QvSRIMny9yi3XGiUhMh2EGnzJqDafV4HrjphNJhcnS8SQSkjhyE3EtyCwzi9+KacSFFqp5ub2nyNZUUBAVasJ8MVdQEKRMAyg6Rtq4Lay5yIECnF9Xh814GrhkVQkdRFS0aYlA0oaBo47nVd3IVWjOsAtAgMBAAECggEBAISf2F274RltgktNJTECnoQ1a6Q4hLy2LM09VUzxV7Vxeqzyv0inXQXoWt1Nm0orF8TWefkK+RB/X8r0VTtN9dRLOjo/VDFwzDYkPvGyV8M4vqW2w9fIPD6XgUdcz1Af/iUqnCDtYBSxKw4w/imHFEjIbw60Ho9ZUu9kAf4O3Opbqz1qj7FOgrkqBKXFeU0yilBHUjJMUHaaPB5WyZPb72lOEwL8Hu55x9V6Ec1xqvo82m5VPNvFGYDaDZlVC0pcCP1BbvhQXXTN0B/SL5axjXqDAxnSJWbkz0QGlLiAvIQDF9xQECkIBDvoox8XvbXJWOb6xsKpjRpGgtDilCVaXKECgYEA6Bct7KFFuYOAiIuzHGYIu1Fs3kTJTQt4tdBZMR4Zq1AMw242arqjoUfJOPQ0efB+Uu6z0Nrprkoqelpv2dLBFEFcwwvIqxK2x3kU4hZdBnW4HVjvlJXUaT3nUs6QP1UDMVNNOtUVZvfUYH/X+x1S+4UUwzy1ubGNEALVZqy7fMkCgYEAyX4Vu2OBZmWcDjBXqnfw7MQfsCTER/UtzC068/Lr1WmAWmNvMQNoTX3hyWhHMKYMrqCV79jht+dl1kvX3Q7R9SoOUR+8LXN5QRP8N1sZRIYXiw8V+V/r8Hr9RVC0+H3M+jD8RxLkBuDDss52WuQZ8C9S5c7r+PAegm+14dMWLkUCgYEA0Jx9F/PBs0UDFDjVbpI8CPrga0zSz6CfCHVUpjSTCAv03PjEitxl1cKrmLjQLka/lRWfpPwQDSv20HFXlie8itNRaLO/1B0HE3a/EZTeWr8XFbHp7tlZyprswN6Qg4yLSRvCED6VdS8PwwlGfh1WiGOPTQnVoip4S8rwO9DI2RkCgYB12Rvm1OUrOcTTe6zalHpCUNdj061FlP4rTE0Nv4f181XSCBRRq3eG01iKz0XMC2KhhGUNumESifHDK+rebTuZ0/Y5toD+aGqECKdczRhzPqZoe/NTPqolphmaQAEI0bNgpeP3wfHCoBV18FHDCWSuWU93/9V+bRq7GZFcunRfrQKBgQCpZQ6PV5nbqHI7U0LiGtJRQGU9h5RV+yKV4PeXIzVZPXky3ky05R90huYhqKkVFwtl/IovFO/Ws9CKtC9BTkPtRDnlK75dMcVXK/fOCDuNzwmm/MOZ3JVusJ2wp8inTDNQMpznl7qpVvJQD6KOtStjfP5B7beRK3lV/krUcL6m9A==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlu0ji6lyfOinclEIuXzGU2JEVHo5AdPftaBCIMtP5PfWO6wer4vvoU9mFhJtyjSrysj1PqjbU1eKN4jl/6wY8Ux3Iv851p42WLRKf/ELHe4QiSSBc1kFFvEt8yq1nTB1Y9+n0eCOstLxK6i4JAxOlsA50AuUO12av74bONDjDkqF/Hf8JWqMFlKfW/yQs1HzM8zibIueids27eeFCKSts58W53Vq2mBq1QxTM3RhXxcqrG7K31KtWRNmMwqq4LKC8UMp75TtEA/QR8JKlW6qXoQFiOFf1mCYgvHdxtHwc9meAwIFkIgycXOpVvbp8wzYbyInJUyFBRP+PJhWE0JSqwIDAQAB";

    // 异步通知，在这里我们设计自己的后台代码
    public static String notify_url = "http://localhost/pay/synnotify";;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost/pay/return_notify";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关 https://openapi.alipaydev.com/gateway.do https://openapi.alipay.com/gateway.do
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String LOG_PATH = "D:\\alipay\\alipaylog";

    public static String FORMAT = "json";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
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
