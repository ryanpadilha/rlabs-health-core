package br.com.rlabs.health;

import br.com.rlabs.commons.utils.JavaMailSender;
import br.com.rlabs.health.Health.Builder;

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
	protected void doHealthCheck(Builder builder) throws Exception {
		if (null == this.mailSender) {
			builder.up().withDetail("mail-server", "unknown");
		} else {
			builder.withDetail("mail-server", this.mailSender.getHostname() + ":" + this.mailSender.getPort());
			this.mailSender.testConnection();
			builder.up();
		}
	}

}
