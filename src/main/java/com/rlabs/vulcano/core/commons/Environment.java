package com.rlabs.vulcano.core.commons;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Expose information of environment variables of running-service context.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class Environment {

	private final Map<String, Object> details;

	private Environment(Builder builder) {
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public Object get(String key) {
		return this.details.get(key);
	}

	public static Environment readSystemVariables() {
		final Environment.Builder builder = new Environment.Builder();

		try {
			final Map<String, String> variables = System.getenv();
			for (Entry<String, String> entry : variables.entrySet()) {
				builder.withDetail(entry.getKey(), entry.getValue());
			}
		} catch (Exception e) {
			builder.withDetail("environment", "unknow");
		}

		return builder.build();
	}

	@Override
	public String toString() {
		return "Environment [details=" + details + "]";
	}

	public static class Builder {

		private final Map<String, Object> details;

		public Builder() {
			this.details = new LinkedHashMap<>();
		}

		public Builder withDetail(String key, Object value) {
			this.details.put(key, value);
			return this;
		}

		public Builder withDetail(Map<String, Object> details) {
			this.details.putAll(details);
			return this;
		}

		public Environment build() {
			return new Environment(this);
		}
	}

}
