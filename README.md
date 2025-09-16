# Hotel-Management-Project
🏨 Hotel Management System (Java)

A Hotel Management System built in Java that supports both:

✅ Console-based Version (simple menu-driven)

✅ Graphical User Interface (GUI) using Java Swing

This project helps manage hotel room bookings, customer details, food orders, billing, and checkouts. It uses file handling & serialization to persist data between sessions.

<hr>

✨ Features

Room Management
<ul>
<li> Luxury Double Room (10 rooms)</li>

<li >Deluxe Double Room (20 rooms)</li>

<li >Luxury Single Room (10 rooms)</li>

<li> Deluxe Single Room (20 rooms)</li>
</ul>

1) Customer Details → Name, Contact, Gender (supports 1 or 2 customers in a room)

2) Food Ordering → Sandwich, Pasta, Noodles, Coke with automatic billing

3) Billing & Checkout → Room + food charges shown before releasing the room

4) Persistence → Data saved automatically in a backup file using Java serialization

Two Modes

🖥️ Console-based (text menu)

🖼️ GUI-based (Java Swing with buttons & dialogs)
<hr>

🛠️ Tech Stack
<ol>
<li> Java (Core, OOP, Exception Handling)</li>

<li> Swing (GUI version)</li>

<li> File Handling & Serialization</li>

<li> Multithreading (background saving)</li>
</ol>

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


<hr>
🔮 Future Enhancements

Database integration (MySQL / MongoDB)

Authentication for staff login

Web-based version using Spring Boot + React

Advanced analytics (occupancy, revenue tracking)
