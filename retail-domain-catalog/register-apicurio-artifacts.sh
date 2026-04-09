#!/bin/sh

apicurio_version=3.2.2-SNAPSHOT
apicurio_server=http://localhost:8080

cd ./merchandising/inventory/inventory-adjustment || exit 1

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="merchandising.inventory.inventory-adjustment" \
  -Dartifacts.artifactId=asyncapi.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="merchandising.inventory.inventory-adjustment" \
  -Dartifacts.artifactId=asyncapi-client.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi-client.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

cd - >/dev/null || exit 1

cd ./merchandising/pricing/price-change || exit 1

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="merchandising.pricing.price-change" \
  -Dartifacts.artifactId=asyncapi.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="merchandising.pricing.price-change" \
  -Dartifacts.artifactId=asyncapi-client.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi-client.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

cd - >/dev/null || exit 1

cd ./customer-relationship/customer-management/customer-profile || exit 1

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="customer-relationship.customer-management.customer-profile" \
  -Dartifacts.artifactId=asyncapi.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="customer-relationship.customer-management.customer-profile" \
  -Dartifacts.artifactId=asyncapi-client.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi-client.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

cd - >/dev/null || exit 1

cd ./customer-relationship/customer-management/loyalty-management || exit 1

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="customer-relationship.customer-management.loyalty-management" \
  -Dartifacts.artifactId=asyncapi.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="customer-relationship.customer-management.loyalty-management" \
  -Dartifacts.artifactId=asyncapi-client.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi-client.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

cd - >/dev/null || exit 1

cd ./merchandising/inventory/stock-replenishment || exit 1

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="merchandising.inventory.stock-replenishment" \
  -Dartifacts.artifactId=asyncapi.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

mvn io.apicurio:apicurio-registry-maven-plugin:${apicurio_version}:register \
  -Dapicurio.url=${apicurio_server}/apis/registry/v3 \
  -Dartifacts.groupId="merchandising.inventory.stock-replenishment" \
  -Dartifacts.artifactId=asyncapi-client.yml \
  -Dartifacts.artifactType=ASYNCAPI \
  -Dartifacts.file=./asyncapi-client.yml \
  -Dartifacts.ifExists=FIND_OR_CREATE_VERSION \
  -Dartifacts.autoRefs=true

cd - >/dev/null || exit 1
