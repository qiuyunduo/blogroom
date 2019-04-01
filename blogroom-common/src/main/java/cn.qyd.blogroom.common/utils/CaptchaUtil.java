package cn.qyd.blogroom.common.utils;

import jodd.mail.Email;
import jodd.mail.MailServer;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

public class CaptchaUtil {

    private static final String EMAIL = "iqiuyunduo@163.com";

    private static final String EMAIL_HOST = "smtp.163.com";

    private static final Integer EMAIL_PORT = 465;

    /**
     * 发送邮件
     */
    public static boolean sendEmail(String email, String content) {

        Email emails = Email.create()
                .from(EMAIL)
                .to(email)
                .subject("BlogRoom")
                .textMessage(content);

        SmtpServer smtpServer = MailServer.create()
                .host(EMAIL_HOST)
                .port(EMAIL_PORT)
                .auth(EMAIL, ToolUtil.emailShouQuanMa)
                .ssl(true)
                .buildSmtpMailServer();


        SendMailSession session = smtpServer.createSession();
        session.open();
        session.sendMail(emails);
        session.close();

        return true;
    }
}
