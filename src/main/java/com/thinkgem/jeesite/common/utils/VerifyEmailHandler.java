package com.thinkgem.jeesite.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.power.mail.bean.MailBean;
import com.thinkgem.jeesite.common.config.GConstants;



public class VerifyEmailHandler {
    private final static Logger LOG = Logger.getLogger(VerifyEmailHandler.class);
    
  //  private final static DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static VerifyEmailHandler ins = new VerifyEmailHandler();
    private VerifyEmailHandler() {
        
    }
    
    public static VerifyEmailHandler getInstance() {
        
        return ins;
    }

 
    public void sendEmail(final String subTitle, final String body,final List<String> emails, List<String> ccmails) {
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                SAXReader reader = null;
                InputStream in = null;
                Document doc = null;
                Element root = null;
                Element entry = null;
              //  Element temp = null;
            //    List<Element> items = null;
                EmailEntry emailEntry = null;
               // List<String> demails = null;// 开发者邮箱列表
               // List<String> uemails = null;// 非开发者邮箱列表
                
                ResourceLoader resourceLoader = null;
                Resource resource = null;
                
                try {
                    
                    resourceLoader = new DefaultResourceLoader();
                    resource = resourceLoader.getResource("verifyemail.xml");
                    in = resource.getInputStream();
                    in = new BufferedInputStream(in);
                    
                    reader = new SAXReader();
                    
                    reader.setValidation(false);
                    reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
                    reader.setFeature("http://xml.org/sax/features/validation",false);  
                    reader.setFeature("http://apache.org/xml/features/validation/schema",false);
                    reader.setEntityResolver(new EntityResolver() {
                        public InputSource resolveEntity(String publicId, String systemId)
                                throws SAXException, IOException {
                            File dtd = new File("properties.dtd");
                            InputSource source = null;
                            if (dtd.exists() && dtd.canRead()) {
                                InputStream ins = new FileInputStream(dtd);
                                ins = new BufferedInputStream(ins);
                                source = new InputSource(ins);
                                source.setPublicId(publicId);
                                source.setSystemId(systemId);
                            } else {
                                source = new InputSource(new ByteArrayInputStream("".getBytes("UTF-8")));
                            }
                            return source;
                        }
                    });
                    doc = reader.read(in);
                    root = doc.getRootElement();
                    entry = root.element("email");
                    if("0".equals(entry.elementTextTrim("evr"))){
                        resource = resourceLoader.getResource("email.xml");
                        in = resource.getInputStream();
                        in = new BufferedInputStream(in);
                        
                        reader = new SAXReader();
                        reader.setValidation(false);
                        doc = reader.read(in);
                        root = doc.getRootElement();
                        entry = root.element("email");
                        emailEntry = new EmailEntry();
                        emailEntry.setHostName(entry.elementTextTrim("hostname"));
                        emailEntry.setSmtpPort(entry.elementTextTrim("smtpport"));
                        emailEntry.setFrom(entry.elementTextTrim("from"));
                        emailEntry.setPassword(entry.elementTextTrim("password"));
                        emailEntry.setSubTitle(subTitle);
                        emailEntry.setBody(body);
                        emailEntry.setName("【"+GConstants.getValue("name", "")+"】系统管理员");
                        sendEmail(emailEntry, emails, true);
                    }else if("1".equals(entry.elementTextTrim("evr"))){
                        emailEntry = new EmailEntry();
                        emailEntry.setHostName(entry.elementTextTrim("hostname"));
                        emailEntry.setSmtpPort(entry.elementTextTrim("smtpport"));
                        emailEntry.setFrom(entry.elementTextTrim("from"));
                        emailEntry.setPassword(entry.elementTextTrim("password"));
                        emailEntry.setSubTitle(subTitle);
                        emailEntry.setBody(body);
                        emailEntry.setName(entry.elementTextTrim("fromname"));
                        root = doc.getRootElement();
                     //   sendEmailHC(emailEntry, emails,ccmails, true);
                    }

                   
                  
    /*                entry = root.element("user");
                    if (entry != null) {// 非开发者用户只能够查看错误 
//                        this.logger.info("发送邮件给用户....");
                        temp = entry.element("to");
                        if (temp != null) {
                            emailEntry.setTo(temp.getTextTrim());
                            temp = entry.element("subtitle");
                            emailEntry.setSubTitle(temp.getTextTrim());
                            temp = entry.element("body");
                            emailEntry.setBody(temp.getTextTrim());

                            items = entry.elements("email");
                            if ((items != null) && (items.size() > 0)) {
                                uemails = new ArrayList<String>(items.size());
                                for (Element item : items) {
                                    uemails.add(item.getTextTrim());
                                }
                            }
                            sendEmail(emailEntry, uemails, false);
                        }
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException localIOException2) {
                        }
                    }
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException localIOException3) {
                        }
                    }
                }
            }
        };
        thread.start();
    }

     void sendEmail(EmailEntry emailEntry, List<String> emails,
            boolean inpath) {
        if (emailEntry == null) {
            return;
        }
        Email email = new SimpleEmail();
        email.setHostName(emailEntry.getHostName());
        email.setSmtpPort(emailEntry.getSmtpPort());
        email.setCharset("UTF-8");
        email.setAuthenticator(new DefaultAuthenticator(emailEntry.getFrom(),
                emailEntry.getPassword()));
        try {
            email.setFrom(emailEntry.getFrom(), emailEntry.getName());
            email.setSubject(emailEntry.getSubTitle());
//            String header = "项目名称： "+GConstants.getValue("name", "")+"\n";
//            if (inpath) {
//                header = "安装服务器： " + GConstants.getValue("service.host.ip", "") + "\n安装目录为： "
//                        + GConstants.getValue("service.install.path", "") + "\n";
//            }
//            header = header+"发生时间： "+FORMAT.format(System.currentTimeMillis())+"\n";
//            
//            
    /*        String header = "Project Name: "+GConstants.getValue("name", "")+"\n";
            if (inpath) {
                header += "Host IP: " + GConstants.getValue("service.host.ip", "") + "\nProject Path: "
                        + GConstants.getValue("service.install.path", "") + "\n";
            }
            header = header+"Error Time: "+FORMAT.format(System.currentTimeMillis())+"\n";*/
            
            email.setMsg(emailEntry.getBody());
            if (emails != null) {
                for (String cemail : emails) {
                    email.addTo(cemail);
                }
            }
            //email.addTo(emailEntry.getTo());
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
           // LOG.debug("发送邮件失败：\n" + Util.getStackTrace(e));
        }
        LOG.debug("发送邮件成功！");
    }
   

    class EmailEntry {
        private String from;
        private String to;
        private String password;
        private String hostName;
        private int smtpPort;
        private String subTitle;
        private String body;
        private String name;
        EmailEntry() {
        }


        EmailEntry(String from, String to, String password, String hostName, String smtpPort,
                String subTitle, String body,String name) {
            this.from = from;
            this.to = to;
            this.password = password;
            this.hostName = hostName;
            
            try {
                this.smtpPort = Integer.parseInt(smtpPort);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.subTitle = subTitle;
            this.body = body;
            this.name= name;
        }

        public String getFrom() {
            return this.from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getTo() {
            return this.to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHostName() {
            return this.hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public int getSmtpPort() {
            return this.smtpPort;
        }

        public void setSmtpPort(String smtpPort) {
            try {
                this.smtpPort = Integer.parseInt(smtpPort);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setSmtpPort(int smtpPort) {
            this.smtpPort = smtpPort;
        }

        public String getSubTitle() {
            return this.subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getBody() {
            return this.body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }


    
    public void sendEmail(final MailBean bean) {
        Thread thread = new Thread() {

            @Override
            public void run() {
                EmailEntry mail = new EmailEntry(bean.getFrom(), bean.getTo(), bean.getPassword(), bean.getHostName(),
                        bean.getSmtpPort(), bean.getSubTitle(), bean.getBody(), bean.getName());
                sendEmail(mail, bean.getMaillist(), true);
            }
        };
        thread.start();

    }


}
