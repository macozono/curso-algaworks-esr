package com.algaworks.algafood.infrastructure.service.email;

import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties properties;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		try {
			helper.setSubject(mensagem.getAssunto());
			helper.setText(processarTemplate(mensagem), Boolean.TRUE);
			helper.setTo(mensagem.getDestinatarios().toArray(new String[] {}));
			helper.setFrom(properties.getRemetente());
			
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
		
		mailSender.send(mimeMessage);
	}
	
	private String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (IOException e) {
			throw new EmailException("Não foi possível processar template", e);
		} catch (TemplateException e) {
			throw new EmailException("Não foi possível processar template", e);
		}
	}
}
