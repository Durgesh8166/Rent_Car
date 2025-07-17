

## 🚗 Rent\_car\_For\_Trip – Java-Based Console Project

> A real-world **car rental management system** developed in **Java** with professional-grade features and modular design. Recently upgraded with advanced business logic like pricing rules, loyalty discounts, fines, and analytics.

---

### 📌 Features

| Feature                        | Description                                         |
| ------------------------------ | --------------------------------------------------- |
| 🚘 Car Renting & Returning     | Core functionality with availability tracking       |
| 📅 Rental Duration             | Customizable rental days                            |
| 🛡️ Insurance Option ✅ **NEW** | Add ₹200/day insurance (optional)                   |
| 🔁 Late Return Fine ✅ **NEW**  | Fine of ₹100/day for late returns                   |
| 🚗 Car Categories ✅ **NEW**    | Economy, SUV, Luxury, etc.                          |
| 📊 Revenue Tracking ✅ **NEW**  | Calculate & display total revenue                   |
| 📈 Most Rented Car ✅ **NEW**   | Tracks most frequently rented car                   |
| 📋 Active Rentals ✅ **NEW**    | Displays current active rentals                     |
| 🧱 Object-Oriented Design      | Clean modular classes with OOP principles           |
| 🧩 Easy Extensibility          | Ready for DB, GUI, or Web integration               |

---

### 🛠️ Tech Stack

* **Language:** Java
* **Concepts:** OOP (Encapsulation, Abstraction, Polymorphism)
* **Tools:** IntelliJ 

---

### 📂 Project Structure

```
Main.java
├── Car.java
├── Customer.java
├── Rental.java
└── CarRentalSystem.java
```

UML-Like Overview:

```
+-------------+     +-------------+     +-------------+
|    Car      | --> |   Rental    | <-- |  Customer   |
+-------------+     +-------------+     +-------------+

+-------------------+
| CarRentalSystem   |
| - Business Logic  |
+-------------------+
```

---

### 🔧 How to Use

```bash
# Compile
javac Main.java

# Run
java Main
```

📝 Java 11+ recommended
✅ No external libraries required

---

### 🧪 Sample Scenario

```
== Rent a Car ==
Available Cars:
C003: Mahindra Thar (SUV)

Enter Car ID: C003
Enter rental days: 3
Add Insurance for ₹200/day? (Y/N): Y

Total Price after discount (if any): ₹1050.00
```

---

### 📈 Business Intelligence Features

* ✅ **NEW:** Total Revenue Report
* ✅ **NEW:** Most Rented Car Analytics
* ✅ **NEW:** Display of Current Active Rentals
* ✅ **NEW:** Fine Calculation for Late Returns
* ✅ **NEW:** Loyalty Discount after 3+ rentals

---

---

### 👨‍💻 Author

**Durgesh Yadav**
Java Developer | IoT & Data Enthusiast
📧 \[[durgeshyadav8166@example.com]]

🔗 \[LinkedIn Profile:-https://www.linkedin.com/in/durgesh-yadav-1504b6292/]

---

