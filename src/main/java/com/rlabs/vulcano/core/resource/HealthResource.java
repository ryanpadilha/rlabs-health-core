package com.rlabs.vulcano.core.resource;

import java.util.Map;

import com.rlabs.vulcano.core.health.Health;
import com.rlabs.vulcano.core.health.Info;

/**
 * Health API Resource Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface HealthResource {

	Info info();

	Map<String, Health> health();

}
