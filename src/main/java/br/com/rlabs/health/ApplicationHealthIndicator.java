package br.com.rlabs.health;

import br.com.rlabs.health.Health.Builder;

/**
 * The Application Health Indication.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class ApplicationHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		builder.up();
	}

}
