package com.rlabs.vulcano.core.health;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rlabs.vulcano.core.commons.Health;
import com.rlabs.vulcano.core.commons.Status;

/**
 * The Abstract Health Aggretator Class.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public abstract class AbstractHealthAggregator implements HealthAggregator {

	@Override
	public Health aggregate(Map<String, Health> healths) {
		final List<Status> statusCandidates = new ArrayList<>();
		for (Map.Entry<String, Health> entry : healths.entrySet()) {
			statusCandidates.add(entry.getValue().getStatus());
		}

		Status status = aggregateStatus(statusCandidates);
		Map<String, Object> details = aggregateDetails(healths);
		return new Health.Builder(status, details).build();
	}

	protected abstract Status aggregateStatus(List<Status> candidates);

	protected Map<String, Object> aggregateDetails(Map<String, Health> healths) {
		return new LinkedHashMap<>(healths);
	}
}
