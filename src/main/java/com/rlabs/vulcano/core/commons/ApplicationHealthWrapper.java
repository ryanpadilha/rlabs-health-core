package com.rlabs.vulcano.core.commons;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Application Health Wrapper class.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class ApplicationHealthWrapper {

	private Health application;
	private Map<String, Health> dependencies;

	public ApplicationHealthWrapper(Health application) {
		this.application = application;
		this.dependencies = new LinkedHashMap<>();
	}

	public Health getApplication() {
		return application;
	}

	public void setApplication(Health application) {
		this.application = application;
	}

	public Map<String, Health> getDependencies() {
		return dependencies;
	}

	public void addDependency(String key, Health health) {
		this.dependencies.put(key, health);
	}

	@Override
	public String toString() {
		return "ApplicationHealthWrapper [application=" + application + ", dependencies=" + dependencies + "]";
	}

}
