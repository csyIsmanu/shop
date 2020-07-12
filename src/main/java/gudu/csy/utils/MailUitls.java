package gudu.csy.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public  class MailUitls {
    private static final String USER = "1404630967@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "lgdyysrutxyxggie"; // 如果是qq邮箱可以使户端授权码，或者登录密码


    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String code) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject("来自购物天堂传智商城官方激活邮件");
            // 设置邮件正文:
            message.setContent("<h1>购物天堂传智商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://192.168.6.193:8080/user/userActive.do?code="+code+"'>http://192.168.6.193:8080/user/userActive.do?code="+code+"</a></h3>", "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) throws Exception { // 做测试用
//        MailUitls.sendMail("1404630967@qq.com", "你好，这是一封测试邮件，无需回复。");
//        System.out.println("发送成功");
//    }
}


