# LeCars-Sales-Management-System
My Assignment Project for WIX1002 Fundamental of Programming

## Overview
LeCars Sales Management System is designed to streamline sales operations for a vehicle sales company. The system includes features for user management, data entry, reporting, and performance insights, catering to both sales and management-level employees.

## Requirements
**1. User Management**
- **Login and Registration:**
New users can register as sales-level employees with a user ID, password, and a secret key.
Existing users can log in with their credentials, with the ability to switch users without terminating the program.
User credentials are stored offline.

**2. Data Import**
Ability to import customer, sales, vehicle, and employee data.
Management-level employees can add new management-level users.

**3. Data Entry**
- **Customer Data:**
Automatically generated Customer ID, with fields for name, phone number, and postcode.
- **Sales Data:**
Automatically generated Sales ID, capturing date/time, car plate, customer ID, and current employee ID.
- **Vehicle Data:**
Fields for car number plate, model, acquired price, status (sold or in stock), and sold price.

**4. Information Viewing**
- **Sales Employees:**
View own customer data, sales records, and all vehicle data.
- **Management Employees:**
Access all customer data, sales records, vehicle data, and employee data, with logs displayed in a tabular format.

**5. Additional Features**
- **Sales Insights:**
Management can view total annual sales, monthly sales totals/averages, and monthly sales trends in a line chart.
- **Salary Calculation:**
Calculate total salaries for employees based on basic salary, allowances, and commissions.
- **Employee Bonus:**
Bonuses for sales employees based on sales records and amounts, with commission tiers for management employees.
- **Search and Filter:**
Employees can search and filter customer, employee, sales, and vehicle records based on specific criteria.

## Implementation Approach
Our team adopted a structured plan with weekly goals, facilitated by regular online meetings for clear communication. We utilized online resources for collaborative skill enhancement.

The implementation phase began with algorithm planning and structured coding, using CSV files for data storage to adhere to the restriction on traditional databases. We embraced File Input/Output and Object-Oriented Programming (OOP) principles for a modular solution.

Rigorous testing throughout the development lifecycle ensured reliability and allowed for error identification and resolution. Our disciplined approach enabled us to meet project challenges and deliver a solution that fulfills the specified requirements and includes additional features.
