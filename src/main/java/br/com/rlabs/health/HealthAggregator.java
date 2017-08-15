package br.com.rlabs.health;

import java.util.Map;

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
