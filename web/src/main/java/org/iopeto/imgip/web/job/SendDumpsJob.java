package org.iopeto.imgip.web.job;

import org.iopeto.imgip.web.model.Dump;
import org.iopeto.imgip.web.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class SendDumpsJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendDumpsJob.class);

    @Value("${email.from}")
    private String from;

    @Value("${email.to}")
    private String to;

    @Value("${jobs.send_dump_job.cron}")
    private String cron;

    private final TaskScheduler scheduler;
    private final StorageService storageService;
    private final DateFormat dateFormat;
    private final JavaMailSender mailSender;


    @Autowired
    public SendDumpsJob(TaskScheduler scheduler, StorageService storageService, DateFormat dateFormat, JavaMailSender mailSender) {
        this.scheduler = scheduler;
        this.storageService = storageService;
        this.dateFormat = dateFormat;
        this.mailSender = mailSender;
    }

    @PostConstruct
    public void init(){
        scheduler.schedule(() -> {
            try {
                List<Dump> dumps = this.storageService.pullAllDumps();

                if (dumps.isEmpty()){
                    return;
                }

                final StringBuilder bodyBuilder = new StringBuilder();

                dumps.forEach(dump -> {
                    bodyBuilder.append("\n===================================================\n");
                    bodyBuilder.append("Date: ").append(dump.getStringDate()).append("\n");
                    bodyBuilder.append("Image: ").append(dump.getImgName()).append("\n\n");
                    bodyBuilder.append("Dump: \n").append(dump.getDump());
                    bodyBuilder.append("\n===================================================\n");
                });

                final String body = bodyBuilder.toString();

                final String now = dateFormat.format(Calendar.getInstance().getTime());

                this.mailSender.send(mimeMessage -> {
                    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    mimeMessage.setFrom(new InternetAddress(from));
                    mimeMessage.setText(body);
                    mimeMessage.setSubject("Requests dump " + now);
                });

            }
            catch (MailException e) {
                LOGGER.error(e.getMessage(), e);
            }
        },new CronTrigger(cron));
    }
}
