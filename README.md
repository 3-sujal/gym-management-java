gym-management-java

A Java-based Gym Management System with a graphical user interface (GUI) built using Java Swing. It supports two types of members — Regular and Premium — with features like attendance tracking, loyalty points, plan upgrades, and payment management.


How to Run the Project

Prerequisites

Make sure you have the following installed on your system:


Java JDK 8 or higher — Download from oracle.com or adoptium.net
A terminal / command prompt


You can verify your Java installation by running:

java -version


Running the Application


Clone or Download the repository:

Click the green Code button above and select Download ZIP, then extract it.
Alternatively, clone it via Git:





git clone https://github.com/your-username/gym-management-java.git


Navigate to the project directory:


cd gym-management-java


Compile all Java files:


javac *.java


Run the application:


java GymGUI


Project Structure

gym-management-java/
│
├── GymMember.java        # Abstract base class for all gym members
├── RegularMember.java    # Regular member with plan upgrade support
├── PremiumMember.java    # Premium member with trainer and payment features
└── GymGUI.java           # Main GUI application (entry point)


Features


Add Regular and Premium gym members
Mark attendance and track loyalty points
Activate / Deactivate memberships
Regular members can upgrade plans (Basic → Standard → Deluxe)
Premium members can pay dues and receive a 10% discount on full payment
Display member details and revert/remove members
