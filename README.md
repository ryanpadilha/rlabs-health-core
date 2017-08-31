# vulcano-health-core

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Vulcano Health Core Library API Monitor for Microservices.<br>
This maven-component-library is used on monitored services by <b>[Vulcano Health Check API Monitor Project](https://github.com/ryanpadilha/vulcano-health-check-monitor)</b>.<br><br>
The goal is include additional features to help monitor and manage applications when it's on production environment.<br>
Basically we have, on this version, four endpoints with essential information:

- /health
- /info
- /properties
- /environment

## Requirements

- Java 1.8 or later.
- Maven 3.x or later.

## JSON datamodel

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