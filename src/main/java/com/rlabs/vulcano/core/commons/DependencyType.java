package com.rlabs.vulcano.core.commons;

/**
 * Dependency Type enumeration.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public enum DependencyType {

	INTERNAL("internal"), EXTERNAL("external");

	private String type;

	DependencyType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
