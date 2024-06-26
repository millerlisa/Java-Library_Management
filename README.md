# Java-Library_Management
SD10 - Semester 3 MidTerm Sprint

Kyle March-Maccuish
Steve Sharpe
Lisa Miller


User Documentation for Library Management System 

1. Introduction

The Library Management System (LMS) is a console-based application designed to facilitate the management of library items, authors, and patrons. It allows librarians to efficiently handle operations such as adding, editing, and deleting books and periodicals, managing author and patron records, and handling the borrowing and returning of library items.


2. Application Overview

The LMS is structured to manage various library entities:

Library Items: Includes books (printed, electronic, audio) and periodicals (printed, electronic).
Authors: Contains details about authors, including their name, date of birth, and list of written items.
Patrons: Encompasses students and employees who borrow library items, including their personal information and borrowed items list.


3. Classes and Their Functionality

LibraryItem (Abstract Class)
  Represents a general library item.
  Implements Borrowable interface to handle borrowing and returning items.
  Attributes: id, title, author, isbn, publisher, numberOfCopies.
  Methods: borrowItem, returnItem.
  
    Book (Subclass of LibraryItem)
      Represents a book item.
      Additional Attribute: format (Printed, Electronic, Audio).

    Periodical (Subclass of LibraryItem)
      Represents a periodical item.
      Additional Attribute: type (Printed, Electronic).

Author
  Represents an author with details.
  Attributes: name, dateOfBirth, itemsWritten.
  Methods: addItem.

Patron (Abstract Class)
  Represents a library patron.
  Attributes: name, address, phoneNumber, borrowedItems.
  Methods: borrowItem, returnItem.

    Student (Subclass of Patron)
      Represents a student patron.

    Employee (Subclass of Patron)
      Represents an employee patron.

Library
  Manages library items, authors, and patrons.
  Methods: addItem, editItem, deleteItem, addAuthor, editAuthor, deleteAuthor, addPatron, editPatron, deletePatron, searchItemByTitle, searchItemByAuthor, searchItemByISBN.

Demo
  Contains the main method and the menu system for navigation.
  Facilitates operations such as adding, editing, deleting, borrowing, and returning items.


The Library Management System is a user-friendly application designed to streamline library operations. By following the steps outlined in this documentation, users can efficiently manage library items, authors, and patrons, ensuring a smooth and organized library management experience.
