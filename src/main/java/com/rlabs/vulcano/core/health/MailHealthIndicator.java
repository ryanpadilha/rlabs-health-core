package com.rlabs.vulcano.core.health;

import com.rlabs.vulcano.core.commons.utils.JavaMailSender;
import com.rlabs.vulcano.core.health.Health.Builder;

/**
 * The Mail Health Indicator.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class MailHealthIndicator extends AbstractHealthIndicator {

	private final JavaMailSender mailSender;

	public MailHealthIndicator(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	protected void doHealthCheck(Builder builder) {
		if (null == this.mailSender) {
			builder.up().withDetail("mail-server", "unknown");
		} else {
			try {
				builder.withDetail("mail-server", this.mailSender.getHostname() + ":" + this.mailSender.getPort());
				this.mailSender.testConnection();
				builder.up();
			} catch (Exception e) {
				builder.down(e);
			}
		}
	}

}
