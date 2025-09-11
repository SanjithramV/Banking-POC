# Bank POC (Spring Boot + Thymeleaf)

This project implements the intern assessment POC described in the assessment pdf.

## Structure
- `src/main/java/com/example/bankpoc` - application code
- `src/main/resources/templates` - Thymeleaf UI for customer and admin
- `pom.xml` - Maven build

## How to run
1. Ensure JDK 17+ and Maven installed.
2. From project root:
   ```
   mvn spring-boot:run
   ```
3. Access:
   - UI: http://localhost:8080/
   - Customer login: http://localhost:8080/customer/login
   - Admin UI: http://localhost:8080/admin (HTTP Basic auth: admin/adminpass)
   - H2 console: http://localhost:8080/h2-console (jdbc:h2:mem:bankdb, user=sa)

## API
- POST `/transaction` - System 1. Example:
```
POST /transaction
Content-Type: application/json
{
  "cardNumber":"4123456789012345",
  "pin":"1234",
  "amount":100,
  "type":"withdraw"
}
```
- POST `/process` - System 2 (internal). Accepts same payload.

## Test cases (preloaded)
- Card 4123456789012345, PIN 1234, balance 1000.00 (supported)
- Card 4123456789012346, PIN 4321, balance 50.00 (supported)
- Card 5123456789012345, PIN 0000, balance 500.00 (NOT supported by System 1 routing)

PINs are stored as SHA-256 hashes. No plaintext PINs are stored or logged.

