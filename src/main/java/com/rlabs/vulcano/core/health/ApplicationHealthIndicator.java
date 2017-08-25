package com.rlabs.vulcano.core.health;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rlabs.vulcano.core.commons.Health.Builder;

/**
 * The Application Health Indication.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class ApplicationHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) {
		final Date timestamp = new Date();
		builder.up().withDetail("request.timestamp", timestamp);
		builder.up().withDetail("request.timestamp.formatted", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp));
	}

}
