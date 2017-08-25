package com.rlabs.vulcano.core.health.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The JavaMail Sender Implementation.<br>
 * Supports both TLS and SSL authentication for sending emails.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public final class JavaMailSender {

	/**
	 * GMail SMTP server supports TLS and SSL authentication.
	 */

	private static final String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private String hostname;
	private int port;
	private String username;
	private String password;
	private Boolean authentication;
	private Boolean startTLSEnable;

	private Session session;

	public JavaMailSender(String hostname, int port, String username, String password, Boolean authentication,
			Boolean startTLSEnable, Session session) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.authentication = authentication;
		this.startTLSEnable = startTLSEnable;
		this.session = session;
	}

	public void init() {
		final Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.host", this.hostname);
		javaMailProperties.put("mail.smtp.port", this.port);
		javaMailProperties.put("mail.smtp.auth", this.authentication);

		if (this.startTLSEnable) {
			javaMailProperties.put("mail.smtp.starttls.enable", this.startTLSEnable);
		} else {
			javaMailProperties.put("mail.smtp.socketFactory.port", this.port);
			javaMailProperties.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
			javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
		}

		this.session = Session.getInstance(javaMailProperties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public void testConnection() throws MessagingException {
		Transport transport = null;
		try {
			transport = connectTransport();
		} finally {
			if (null != transport)
				transport.close();
		}
	}

	public void doSend(final String from, final String to, final String subject, final String text)
			throws AddressException, MessagingException {
		final Message message = new MimeMessage(this.session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		message.setSubject(subject);
		message.setText(text);
		message.setSentDate(new Date());

		Transport.send(message);
	}

	protected Transport connectTransport() throws MessagingException {
		String username = this.username;
		String password = this.password;

		if ("".equals(username)) {
			username = null;

			if ("".equals(password))
				password = null;
		}

		final Transport transport = this.session.getTransport();
		transport.connect(this.hostname, this.port, username, password);
		return transport;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Boolean authentication) {
		this.authentication = authentication;
	}

	public Boolean getStartTLSEnable() {
		return startTLSEnable;
	}

	public void setStartTLSEnable(Boolean startTLSEnable) {
		this.startTLSEnable = startTLSEnable;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
