#!/bin/bash

### ===========================================
### JustLife Services – Stop All Services
### ===========================================

echo "----------------------------------------"
echo " Stopping All Microservices... "
echo "----------------------------------------"

# Services list (process name keywords)
SERVICES=(
 "discovery-server"
 "config-server"
 "customer-service"
 "vehicle-service"
 "cleaner-service"
 "booking-service"
 "api-gateway"
)

for service in "${SERVICES[@]}"; do
    echo "Stopping $service ..."
    
    # Kill mvn spring-boot:run processes
    pkill -f "$service" 2>/dev/null

    # Kill java -jar processes
    pkill -f "$service.jar" 2>/dev/null
    
    sleep 1
done

echo "----------------------------------------"
echo " All services stopped successfully!"
echo "----------------------------------------"
