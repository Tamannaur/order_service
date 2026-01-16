Note: First need to run inventory service then Order service
# Order Service

A Spring Boot application for managing inventory with order processing capabilities. Includes unit tests for order handling logic and uses H2 in-memory database for development.

---

## 🛠️ Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17+** (required for Spring Boot 3.x)
- **Maven** (or Gradle if using Gradle build)
- **IDE** (e.g., IntelliJ IDEA) for code navigation

---
### 1. **Get Product Inventory**
**Endpoint**: `POST /order`  
**Description**: Update the data in the inventory via rest call 
**Request**:
```json
{
  "productId": 1003,
  "quantity": 11
}
```
**Response**:
```json
{
  "orderId": 12,
  "productId": 1003,
  "productName": "Product-1003",
  "quantity": 11,
  "status": "PLACED",
  "reservedFromBatchIds": [],
  "message": "Order placed. Inventory reserved."
}
```
It will internally set fromOutSide as true
headers.set("fromOutSide", String.valueOf(true));
and update the quantity in inventory table
