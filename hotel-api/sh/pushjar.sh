#!/bin/sh
echo 'begin push jar...'
curl -u admin:admin123 http://10.10.144.74:8081/nexus/content/repositories/snapshots/com/mk/hotel-api/1.0-SNAPSHOT/hotel-api-1.0-$(date +%Y%m%d%H%M%S).jar --request PUT --data ~/.m2/repository/com/mk/hotel-api/1.0-SNAPSHOT/hotel-api-1.0-SNAPSHOT.jar
echo 'push jar finish...'
