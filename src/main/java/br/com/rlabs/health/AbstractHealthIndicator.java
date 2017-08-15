package br.com.rlabs.health;

import org.apache.log4j.Logger;

import br.com.rlabs.health.Health.Builder;

/**
 * The Abstract Health Indicator.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public abstract class AbstractHealthIndicator implements HealthIndicator {

	private static final Logger LOGGER = Logger.getLogger(AbstractHealthIndicator.class);

	@Override
	public Health health() {
		final Builder builder = new Health.Builder();

		try {
			doHealthCheck(builder);
		} catch (Exception e) {
			LOGGER.warn("Health check failed", e);
			builder.down(e);
		}

		return builder.build();
	}

	protected abstract void doHealthCheck(Health.Builder builder) throws Exception;
}
