# 📧 Email Management System

## 📌 Description
A complete **Email Management System** built with **Spring Boot** and **Angular**.  
It allows sending emails immediately or scheduling them for later, with support for multiple recipients and file attachments.  
The system also provides a contact manager, a history of sent/scheduled emails, and an admin dashboard with statistics.

## 🚀 Technologies
- **Backend**: Spring Boot (Java), MySQL
- **Frontend**: Angular
- **Scheduler**: Spring `@Scheduled`
- **Mailing**: JavaMailSender
- **Version Control**: Git & GitHub

## 🔑 Features
### 👤 User Features
- **Contact Management**  
  - Add, edit, delete contacts (name, email, etc.)
- **Email Sending**  
  - Send a simple email
  - Send to multiple recipients
  - Send with file attachments (e.g., PDF, DOCX)
- **Send Options**  
  - Immediate sending
  - Scheduled sending (at a chosen date/time)
- **History of Emails**  
  - View list of emails with:
    - Subject
    - Content
    - Date
    - Status (SENT, PENDING)
    - Attached file (if any)

### 📊 Dashboard
- Statistics and analytics on:
  - Emails sent
  - Pending emails
  - Success/failure rates
- Visual charts and summaries

## 📂 Project Structure
- `/backend` → Spring Boot application (services, controllers, entities)
- `/frontend` → Angular SPA (components, services, UI)
- `/sql` → Database schema

## ▶️ How to Run
1. Clone the repo:
   ```bash
   git clone https://github.com/eya-gritli-dev/email-management-system.git
