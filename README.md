Below is a clean, professional **README.md** you can directly use for your GitHub repository.
It is written in an academic–technical tone and aligns well with a **SEN5000 Object-Oriented System Design** submission.

You can copy–paste this as `README.md`.

---

# Environmental CO₂ Logging System (Java Client–Server)

## Module Information

* **Module Title:** Object Oriented System Design
* **Module Code:** SEN5000
* **University:** Cardiff Metropolitan University – School of Technologies

## Project Overview

This project implements a **Java-based client–server system** designed to collect, validate, and store environmental carbon dioxide (CO₂) readings. The system allows multiple clients to connect concurrently to a central server and submit CO₂ measurements along with user identification and location information.

The server validates all inputs, timestamps each submission, and safely stores the data in a shared CSV file using thread-safe mechanisms. The project demonstrates practical application of **networking**, **multithreading**, and **object-oriented design principles** in line with the SEN5000 assignment brief.

---

## Key Features

* Client–server architecture using Java sockets
* Supports **up to four concurrent clients**
* Multithreaded server using a thread-per-client model
* Robust input validation (User ID, UK postcode, CO₂ range)
* Thread-safe CSV data storage
* Command-line client interface
* UML-based system design (Use Case, Class, and Sequence diagrams)

---

## System Architecture

* **Client:**

  * Command-line interface
  * Sends user ID, postcode, and CO₂ reading to the server

* **Server:**

  * Listens on a configurable port
  * Handles each client in a separate thread
  * Validates inputs
  * Adds timestamps
  * Writes data safely to a CSV file

---

## Technologies Used

* **Programming Language:** Java (JDK 17+)
* **Networking:** Java Sockets (TCP)
* **Concurrency:** Java Multithreading
* **Data Storage:** CSV File
* **IDE:** IntelliJ IDEA
* **Design Tools:** UML Diagrams

---

## Project Structure

```
src/
├── client/
│   └── ClientMain.java
├── server/
│   ├── ServerMain.java
│   └── ClientHandler.java
├── common/
│   ├── CO2Reading.java
│   ├── DataLogger.java
│   └── ValidationUtils.java
└── resources/
    └── co2_readings.csv
```

---

## How to Run the System

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* IntelliJ IDEA or any Java-compatible IDE

### Running the Server

1. Open the project in IntelliJ IDEA
2. Navigate to `src/server/ServerMain.java`
3. Right-click and select **Run ServerMain**
4. Confirm the console shows the server listening on port `5555`

### Running the Client

1. Navigate to `src/client/ClientMain.java`
2. Right-click and select **Run ClientMain**
3. Enter the required inputs when prompted

---

## Input Requirements

* **User ID:** Alphanumeric, 6–12 characters
* **Postcode:** Valid UK postcode format
* **CO₂ Reading:** Numeric value between 300 and 5000 ppm

---

## Data Storage

All validated submissions are appended to:

```
co2_readings.csv
```

Each record is stored in the following format:

```
Timestamp, UserID, Postcode, CO2Value
```

Thread synchronization ensures safe concurrent access to the file.

---

## Testing

The system was tested for:

* Client–server connectivity
* Functional correctness
* Input validation
* Concurrent client handling
* Error handling and stability

All test cases passed as documented in the project report.

---

## Limitations

* CSV storage limits scalability
* No graphical user interface
* No authentication or encryption

---

## Future Enhancements

* Replace CSV with database storage
* Add GUI for improved usability
* Implement authentication and encrypted communication
* Extend concurrent client capacity

---

## AI Acknowledgement

AI tools were used **only to assist with report writing, clarity, and academic tone**.
No AI tools were used to generate source code or testing evidence.

---

## Authors

* Sahid Riyan Ahmed
* Imthiyas Arshad
* Mohd Shadan Pasha

---

If you want, I can:

* Shorten this for a **public GitHub repo**
* Add **badges** (Java, License, University)
* Customize it for **private academic submission**
* Align it with **GitHub best practices**

Just tell me.
