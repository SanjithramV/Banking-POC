Banking POC – Simplified Banking System

This project is a Proof of Concept (POC) implementation of a simplified banking system.
It demonstrates transaction processing, card validation, PIN security, and role-based monitoring using Spring Boot (Java) for the backend and React.js for the frontend.

The system is built according to the Intern Assessment requirements.

🚀 Features
🔹 System 1 – Transaction Ingress & Routing

/transaction API for withdrawals and top-ups

Validates card_number, pin, amount, and type

Ensures amount is positive (> 0)

Routes only Visa cards (card numbers starting with 4)

Rejects unsupported card ranges

🔹 System 2 – Card Validation & Processing

/process API validates and processes transactions

Verifies card existence in DB

Validates PIN with SHA-256 hashing

Updates card balance (withdraw/top-up)

Declines invalid card, invalid PIN, insufficient balance

🔹 Security

PINs securely stored using SHA-256 hashing

No plain-text PIN storage or logging

🔹 Role-Based Web UI

Super Admin:

View all transactions in the system

Customer:

Login with card details

View balance and personal transaction history

Perform top-ups

📂 Project Structure
Banking-POC/
│
├── backend/
│   ├── system1-ingress/        # Handles transaction ingress & routing
│   ├── system2-processing/     # Handles card validation & processing
│   └── pom.xml                 # Maven dependencies
│
├── frontend/                   # React role-based UI
│   ├── src/components/         # UI components (Admin, Customer, etc.)
│   ├── package.json
│   └── README.md
│
├── docs/                       # Documentation & test cases
│   ├── setup.md
│   ├── api-examples.md
│   ├── test-cases.md
│   └── architecture-diagram.png
│
└── README.md                   # This file

🛠️ Tech Stack

Backend: Java, Spring Boot, H2 Database, Spring Security

Frontend: React.js, Axios

Security: SHA-256 for PIN hashing

Build Tools: Maven, npm

⚙️ Setup Instructions
🔹 Backend (System 1 & 2)
cd backend
mvn spring-boot:run


Runs on: http://localhost:8080

🔹 Frontend (React UI)
cd frontend
npm install
npm start


Runs on: http://localhost:3000

📌 Example API Requests
Withdraw Transaction (Valid)
curl -X POST http://localhost:8080/transaction \
-H "Content-Type: application/json" \
-d '{
  "card_number": "4123456789012345",
  "pin": "1234",
  "amount": 100,
  "type": "withdraw"
}'

Top-Up Transaction (Valid)
curl -X POST http://localhost:8080/transaction \
-H "Content-Type: application/json" \
-d '{
  "card_number": "4123456789012345",
  "pin": "1234",
  "amount": 500,
  "type": "topup"
}'

