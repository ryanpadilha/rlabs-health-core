# vulcano-health-core

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://api.travis-ci.org/ryanpadilha/vulcano-health-core.png)](https://travis-ci.org/ryanpadilha/vulcano-health-core)


Vulcano Health Core Library API Monitor for Microservices.<br>
This maven-component-library is used on monitored services by <b>[Vulcano Health Check API Monitor Project](https://github.com/ryanpadilha/vulcano-health-check-monitor)</b>.<br><br>
The goal is include additional features to help monitor and manage applications when it's on production environment.<br>
Basically we have, on this version, four endpoints with essential information:

- /health - check if the service is running correctly with all internal and external dependencies.
- /info - show details about version.
- /properties - list all application properties.
- /environment - relate all system environment variables of service context.

### Requirements

- Java 1.8 or later.
- Maven 3.x or later.

## Example of usage in Java

The health resource endpoint class implementation.

```java

@RouteResource(path = "/api/service")
public class HealthResourceImpl implements HealthResource {

	private static final String DB_IDENTITY = "db-postgres";

	@Override
	@Route(path = "/health", method = Method.GET)
	public ApplicationHealthWrapper health() {
		final Health applicationHealth = new ApplicationHealthIndicator().health(DependencyType.INTERNAL);
		final ApplicationHealthWrapper wrapper = new ApplicationHealthWrapper(applicationHealth);

		try {
			DataSourceHealthIndicator databaseIndicator = new DataSourceHealthIndicator(DB_IDENTITY,
					ConnectionFactory.getDataSource());
			wrapper.addDependency(DB_IDENTITY, databaseIndicator.health(DependencyType.EXTERNAL));
		} catch (Exception e) {
			wrapper.addDependency(DB_IDENTITY, Health.down(e).build());
		}

		return wrapper;
	}

	@Override
	@Route(path = "/info", method = Method.GET)
	public Info info() {
		return Info.buildFromPOM(WplacesServiceApplication.class);
	}

	@Override
	@Route(path = "/properties", method = Method.GET)
	public Property properties() {
		// TODO read-config manually, in future process automatically
		return Property.readProperties(Constants.getAllProperties());
	}

	@Override
	@Route(path = "/environment", method = Method.GET)
	public Environment environment() {
		return Environment.readSystemVariables();
	}

}

```

### JSON response datamodel

```json
{
    "application": {
        "status": "UP",
        "type": "INTERNAL",
        "details": {
            "response.timestamp": 1504211410427,
            "response.timestamp.utc": "31/08/2017 17:30:10"
        }
    },
    "dependencies": {
        "db-postgres": {
            "status": "UP",
            "type": "EXTERNAL",
            "details": {
                "database": "PostgreSQL",
                "database.version": "9.6.2",
                "database.statement": "OK"
            }
        }
    }
}
```

## License

Copyright 2017 © ResearchLabs under [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0)

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```