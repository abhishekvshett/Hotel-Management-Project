# Hotel-Management-Project
🏨 Hotel Management System (Java)

A Hotel Management System built in Java that supports both:

✅ Console-based Version (simple menu-driven)

✅ Graphical User Interface (GUI) using Java Swing

This project helps manage hotel room bookings, customer details, food orders, billing, and checkouts. It uses file handling & serialization to persist data between sessions.

<hr>

✨ Features

Room Management

Luxury Double Room (10 rooms)

Deluxe Double Room (20 rooms)

Luxury Single Room (10 rooms)

Deluxe Single Room (20 rooms)

Customer Details → Name, Contact, Gender (supports 1 or 2 customers in a room)

Food Ordering → Sandwich, Pasta, Noodles, Coke with automatic billing

Billing & Checkout → Room + food charges shown before releasing the room

Persistence → Data saved automatically in a backup file using Java serialization

Two Modes

🖥️ Console-based (text menu)

🖼️ GUI-based (Java Swing with buttons & dialogs)

🛠️ Tech Stack

Java (Core, OOP, Exception Handling)

Swing (GUI version)

File Handling & Serialization

Multithreading (background saving)

<hr>
🚀 Getting Started
1️⃣ Clone the repository
git clone https://github.com/your-username/hotel-management-java.git
cd hotel-management-java

2️⃣ Run Console Version
javac Main.java
java Main


You will get a menu-driven interface like this:

Enter your choice :
1. Display room details
2. Display room availability
3. Book
4. Order food
5. Checkout
6. Exit

3️⃣ Run GUI Version
javac HotelUI.java
java HotelUI


You will see a Swing-based UI:

Buttons for Features, Availability, Booking, Food Ordering, Checkout, Save & Exit

Pop-up dialogs for entering customer details, food orders, and checkout confirmation
