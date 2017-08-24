package com.rlabs.vulcano.entity.vo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Information entity for JSON serialization.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class InfoEntity implements Serializable {

	private static final long serialVersionUID = -5063654343102570473L;

	private String id;
	private String name;
	private String version;
	private String description;
	// TODO attribute for git-commit on deploy server

	public InfoEntity() {

	}

	public InfoEntity(String id, String name, String version, String description) {
		this.id = id;
		this.name = name;
		this.version = version;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static InfoEntity buildFromPOM(String filename) {
		final MavenXpp3Reader reader = new MavenXpp3Reader();
		final InfoEntity infoEntity = new InfoEntity();

		try {
			final Model model = reader.read(new FileReader(filename));
			infoEntity.setId(model.getArtifactId());
			infoEntity.setName(model.getName());
			infoEntity.setVersion(model.getVersion());
			infoEntity.setDescription(model.getDescription());
		} catch (IOException | XmlPullParserException e) {
			// ignore
		}

		return infoEntity;
	}

}
