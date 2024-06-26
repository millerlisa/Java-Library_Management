package librarymanagement;

import java.util.Scanner;


import librarymanagement.Book.Format;


public class Demo {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        library.loadLibraryDataFromTextFile();  // Load data from text file at the start

        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addAuthor();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    saveData();
                    break;
                case 5:
                    viewData();
                    break;
                case 6:
                    editItem();
                    break;
                case 7:
                    deleteItem();
                    break;
                case 8:
                    borrowItem();
                    break;
                case 9:
                    returnItem();
                    break;    
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        library.saveLibraryDataToTextFile();  // Save data to text file before exiting
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add Author");
        System.out.println("2. Add Library Item");
        System.out.println("3. Add Patron");
        System.out.println("4. Save Data");
        System.out.println("5. View Data");
        System.out.println("6. Edit Library Item");
        System.out.println("7. Delete Library Item");
        System.out.println("8. Borrow Library Item");
        System.out.println("9. Return Library Item");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void addAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (dd/MM/yyyy): ");
        String dob = scanner.nextLine();
        Author author = new Author(name, dob);
        library.addAuthor(author);
    }

    private static void addItem() {
        System.out.println("Choose item type:");
        System.out.println("1. Book");
        System.out.println("2. Periodical");
        int itemType = getChoice();

        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        Author author = library.findAuthorByName(authorName);
        if (author == null) {
            System.out.println("Author not found. Please add the author first.");
            return;
        }

        switch (itemType) {
            case 1:
                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();
                System.out.print("Enter publisher: ");
                String publisher = scanner.nextLine();
                System.out.print("Enter number of copies: ");
                int numberOfCopies = Integer.parseInt(scanner.nextLine());
                System.out.println("Choose format:");
                System.out.println("1. Printed");
                System.out.println("2. Electronic");
                System.out.println("3. Audio");
                Format format = Format.values()[getChoice() - 1];
                Book book = new Book(id, title, author, isbn, publisher, numberOfCopies, format);
                library.addItem(book);
                break;
            case 2:
                System.out.print("Enter issue number: ");
                int issueNumber = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter publisher: ");
                String periodicalPublisher = scanner.nextLine();
                Periodical periodical = new Periodical(id, title, author, issueNumber, periodicalPublisher);
                library.addItem(periodical);
                break;
            default:
                System.out.println("Invalid item type.");
        }
    }

    private static void addPatron() {
        System.out.println("Choose patron type:");
        System.out.println("1. Regular Patron");
        System.out.println("2. Student Patron");
        System.out.println("3. Employee Patron");
        int patronType = getChoice();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        switch (patronType) {
            case 1:
                Patron patron = new Patron(name, address, phoneNumber);
                library.addPatron(patron);
                break;
            case 2:
                System.out.print("Enter student ID: ");
                String studentID = scanner.nextLine();
                System.out.print("Enter major: ");
                String major = scanner.nextLine();
                StudentPatron studentPatron = new StudentPatron(name, address, phoneNumber, studentID, major);
                library.addPatron(studentPatron);
                break;
            case 3:
                System.out.print("Enter employee ID: ");
                String employeeID = scanner.nextLine();
                System.out.print("Enter department: ");
                String department = scanner.nextLine();
                EmployeePatron employeePatron = new EmployeePatron(name, address, phoneNumber, employeeID, department);
                library.addPatron(employeePatron);
                break;
            default:
                System.out.println("Invalid patron type.");
        }
    }

    private static void saveData() {
        library.saveLibraryData();
    }

    private static void viewData() {
        System.out.println("\nAuthors:");
        for (Author author : library.getAuthors()) {
            System.out.println(author);
        }

        System.out.println("\nItems:");
        for (LibraryItem item : library.getItems()) {
            System.out.println(item);
        }

        System.out.println("\nPatrons:");
        for (Patron patron : library.getPatrons()) {
            System.out.println(patron);
        }
    }

    private static void editItem() {
        System.out.print("Enter item ID to edit: ");
        String id = scanner.nextLine();
        LibraryItem item = library.findItemByID(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Choose field to edit:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Number of copies");
        int field = getChoice();

        switch (field) {
            case 1:
                System.out.print("Enter new title: ");
                String title = scanner.nextLine();
                item.setTitle(title);
                break;
            case 2:
                System.out.print("Enter new author name: ");
                String authorName = scanner.nextLine();
                Author author = library.findAuthorByName(authorName);
                if (author == null) {
                    System.out.println("Author not found. Please add the author first.");
                    return;
                }
                item.setAuthor(author);
                break;
            case 3:
                System.out.print("Enter new number of copies: ");
                int numberOfCopies = Integer.parseInt(scanner.nextLine());
                item.setNumberOfCopies(numberOfCopies);
                break;
            default:
                System.out.println("Invalid field.");
        }
    }

    private static void deleteItem() {
        System.out.print("Enter item ID to delete: ");
        String id = scanner.nextLine();
        LibraryItem item = library.findItemByID(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        library.deleteItem(item);
    }

    private static void borrowItem() {
        System.out.print("Enter item ID to borrow: ");
        String id = scanner.nextLine();
        LibraryItem item = library.findItemByID(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        
        System.out.print("Enter patron ID: ");
        String patronID = scanner.nextLine();
        Patron patron = library.findPatronByID(patronID);
        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        if (item instanceof Borrowable) {
            Borrowable borrowableItem = (Borrowable) item;
            borrowableItem.borrowItem(patron);
        } else {
            System.out.println("Item is not borrowable.");
        }
    }

    private static void returnItem() {
        System.out.print("Enter item ID to return: ");
        String id = scanner.nextLine();
        LibraryItem item = library.findItemByID(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        if (item instanceof Borrowable) {
            Borrowable borrowableItem = (Borrowable) item;
            borrowableItem.returnItem();
        } else {
            System.out.println("Item is not borrowable.");
        }
    }
}

