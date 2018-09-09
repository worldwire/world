package com.spring.worldwire.utils;

import com.spring.worldwire.constants.Constants;
import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by luxun on 2018/4/23.
 */
public class MailUtils {

    private static Session session = null;

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        //初始化邮箱信息
        session = Session.getDefaultInstance(buildDefaultproperties(Constants.MAIL_SENDT_ACCOUNT), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.MAIL_SENDT_ACCOUNT, Constants.MAIL_SEND_PASSWORD);
            }
        });
    }

    public static void sendActiveMail(String receiverEmail, Date date, String url) throws IOException, MessagingException {

        String mailTemplate = SpringFileUtil.getFileStrFromResource(Constants.MAIL_SEND_TEMPLATE);

        mailTemplate = mailTemplate.replaceAll("##URL##", url);

        sendMail(receiverEmail, Constants.MAIL_SEND_TITLE, mailTemplate, null);

    }

    public static void sendResetPasswordMail(String receiverEmail, Date date, String url) throws IOException, MessagingException {

        String mailTemplate = SpringFileUtil.getFileStrFromResource(Constants.MAIL_PASSWORD_TEMPLATE);

        mailTemplate = mailTemplate.replaceAll("##URL##", url);

        sendMail(receiverEmail, Constants.MAIL_SEND_TITLE, mailTemplate, null);

    }

    public static void sendMail(String receiverEmail, String title, String content, List<File> files) throws UnsupportedEncodingException, MessagingException {

        MimeMessage msg = createMailMessage(session, receiverEmail, title, content);

        Multipart mp = new MimeMultipart();
        multiAttatchFileUpload(mp, files);//处理附件

        Transport transport = session.getTransport("smtp");
        transport.connect(Constants.MAIL_SENDT_ACCOUNT, Constants.MAIL_SEND_PASSWORD);
        transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
        transport.close();

    }

    /**
     * 构造邮件的主体部分
     * K
     *
     * @param session
     * @param receiveMail
     * @param title
     * @param content
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static MimeMessage createMailMessage(Session session, String receiveMail, String title, String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(Constants.MAIL_SENDT_ACCOUNT));        //设置发件人
        msg.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiveMail)});  //设置收件人
        msg.setSubject(title); //设置邮件标题
        msg.setContent(content, "text/html;charset=UTF-8");
        msg.setSentDate(new Date()); //  设置发件时间
        msg.saveChanges();// 保存设置
        return msg;
    }

    /**
     * 构造默认的邮件属性
     *
     * @param senderEmail
     * @return
     */
    public static Properties buildDefaultproperties(String senderEmail) {
        Properties props = System.getProperties();
        // 创建信件服务器
        //code1234
        props.setProperty("mail.transport.protocol", "stmp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", Constants.MAIL_SMTP_HOST);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");

        return props;
    }

    /**
     * 添加附件
     *
     * @param files
     * @return
     * @throws MessagingException
     */
    public static Multipart multiAttatchFileUpload(Multipart mp, List<File> files) throws MessagingException {
        if (CollectionUtils.isEmpty(files)) {
            return mp;
        }
        files.stream().forEach(item -> {
            MimeBodyPart mbp = new MimeBodyPart();
            // 得到数据源
            FileDataSource fds = new FileDataSource(item);
            // 得到附件本身并至入BodyPart
            try {
                mbp.setDataHandler(new DataHandler(fds));
                // 得到文件名同样至入BodyPart
                mbp.setFileName(fds.getName());
                // 把这个mbp附件add进去
                mp.addBodyPart(mbp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        return mp;
    }

    public static void main(String[] args) throws IOException, MessagingException {
        sendActiveMail("luxun@jd.com", new Date(), "http://wwww.baidu.com");
    }

}
