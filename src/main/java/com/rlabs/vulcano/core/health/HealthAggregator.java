package com.rlabs.vulcano.core.health;

import java.util.Map;

import com.rlabs.vulcano.core.commons.Health;

/**
 * The Health Aggregator Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface HealthAggregator {

	Health aggregate(Map<String, Health> healths);
}
