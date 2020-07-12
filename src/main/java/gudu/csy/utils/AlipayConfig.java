package gudu.csy.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102100732424";
    public static String notify_url = "http://192.168.6.193:8080/order/success.do";
    // 商户私钥，您的PKCS8格式RSA2私钥，就是我们用工具生成的，我让你先保存起来的
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCkhTLHdoFjF5WVzqA9ip/9n4EZmUVncAZl0/4Km+YhpobXxf3wjOtOoRpNMAqbVdHeZphqpyI9+GNUJDOhDUYaGsq2bDLGWIAN8ePFSPNWXBnrac032e0r9mnOSARrByeOTpAF/EcrabJSjKfg13afv3v4yA75Ezu2AJIsEjayEhvdD6/ZMU6506tt20MgZDkugH3UD/w7ezO74UdY8FDUUBofiNw1k/blOdKcIJb8/OcpqW/gOz2u4h0/mNLZJ3FBIA8IjrAdgbt6UHfI/ldAIiXeNDp/mhPsGAMHggsUqUpf1EBQOvPRsgnwQ3EUT+FMaNPnFaVQkgAebk/xf2Y9AgMBAAECggEACfDieJr4hnxOVZb+p7lQoYqZymowf2eNe2mr2AhDh5UAYNld1sMtFLs3U6KrgWdWHxIj843Vy9jOZ68oT3r12W31AcDX8JQ4aJ9NHAakbXtzNX5XICDS6+0fx48idTUhdr/YhAQSaNUiKUrGeheK22w+mXG9Nd+ro/u02WTgWTIWHXHSq3Ozk0F9jUSMKHh2MdGIA23eZqEvOgfeDtGKKnjdfrwsvaI10fblRUH+VRkXK8o40M4TGzFuwTKG35Jh0Efs/1E0hwQA2aBzdteiy7PyWTxzeEJWVLPHhiSE2CKKQVslD4gJe9z56NqSH5ikSf5FNmOd2s45byaiUgGIAQKBgQDS4BEpOvrEfvwLRasg5w4Rr4w5ZxGt5Sf/c0uIe+YRh4wrmWuSR5taVQ527YNKTyK963vbuBsws772NJwWmcj3y0U2PvK82RSJB39Mj9hDPGyI1oelxoFY+Lts0b60MIYLTmejBnQ8tzfXf7G+ICiBiDxeNjFsyvGRxU1zMqum/QKBgQDHucQT+2jEvNeSynG46ekeChdRCMyKCc92ynQfqNTqOW8eIShxHiLL34JK3TUdrNrrYko+HE1PGNcAmik1IUq7fHc1aQOnOpDj/domGOI5CPZixY3Dey2CH7O3L0ut/gk7tpH0AAIf1k9qbz7CVCtComRdmZCuigR8/6c7WLIAQQKBgH8F87BnU6jLaR2NINEX/TSQDyKAwHiEYfyRQb18DJEgmkGlD9Q0iBL4y1a+KWi4lI/camZm1xIWJACAnklK53DEI/PSJTu2c5U7S+Yde8K8ghBHUbx8/2tNoPCiw4Ic/JGQ8AKWn6v3HY8U30dMbbnhC6QgdtM2yB7rv4SV0CotAoGAFyVdJt+s7Zfg978EXOSmwndy6k41vg2TAHZH7N9ZBWfwi7JWCH3wlGXrqJdoL283062JYxdOkksTp5s2CVU5Xu72tbbc2IV+Ax4JKP8bQHbcbufsHOpqhLj9z83oXFqjYUswMPHLTTs96tzHjifI96jwLrS8Qq32BTJvg/4sasECgYBqkFgVX6F0P/FoEtWUH8jc6xpRUyi8lRx8LA7IoshOTcGejSjjQBrtrSNXHmc7No3upU1IrB/gKNJNmtBPF/jp4/yXfHUKwk2wongT6BnTj/x7RLa6/wkcLULLih1gmft+rPfQBmv+i4t5HWhZRh8og46UHSj8WFs3AJz1px4L/g==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq2LSrj9CXoNmOcK91M9tbNcnCne2t2NjXpvy/JAWa1R0fgoqvdVa3iKOr/OAjSmcFYy12oo7BDOrqhLvkUca7rDV09R+sr1UPC5pOGTIHlpDg4aP92wWcyeR1Xtho/l4ASLIksx7W6ORmxAeLLFNOKMrEJra1nbpjlPqY+7RlHCQUQYlMOatIJf3jfqBUrCsZZsrCmAbvnUxySQCfoaA2XRy9kXbqfwRmG3n8lWS8YqdCWKrAX6ibOs5xxLV+E7eTybs6FOih0UKvsqR4GoZluHrqepmX8tFyNRa4YRqxxYpIz0YgVIxFfqJQRxwtORHSAHBPj0Kvzij1WImvR3KoQIDAQAB";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}