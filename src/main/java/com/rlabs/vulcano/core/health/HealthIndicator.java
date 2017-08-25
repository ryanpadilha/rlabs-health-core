package com.rlabs.vulcano.core.health;

import com.rlabs.vulcano.core.commons.DependencyType;
import com.rlabs.vulcano.core.commons.Health;

/**
 * The Health Indicator Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface HealthIndicator {

	Health health(DependencyType type);
}
