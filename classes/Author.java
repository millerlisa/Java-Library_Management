package librarymanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextID = 1;
    private int authorID;
    private String authorName;
    private LocalDate dateOfBirth;
    private List<LibraryItem> itemsWritten;

    public Author(String authorName, LocalDate dateOfBirth) {
        this.authorID = nextID++;
        setAuthorName(authorName);
        setDateOfBirth(dateOfBirth);
        this.itemsWritten = new ArrayList<>();
    }

    public Author(String authorName, String dateString) throws DateTimeParseException {
        this.authorID = nextID++;
        setAuthorName(authorName);
        setDateOfBirth(parseDateString(dateString));
        this.itemsWritten = new ArrayList<>();
    }

    private LocalDate parseDateString(String dateString) throws DateTimeParseException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.", e);
            }
        }

    public int getAuthorID() {
        return authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }
        this.authorName = authorName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date of birth.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public List<LibraryItem> getItemsWritten() {
        return new ArrayList<>(itemsWritten);
    }

    public synchronized void addItem(LibraryItem item) {
        if (item != null && !itemsWritten.contains(item)) {
            itemsWritten.add(item);
            System.out.println("Item added successfully.");
        } else {
            System.err.println("Invalid item or item already exists.");
        }
    }

    public synchronized void removeItem(LibraryItem item) {
        if (item != null && itemsWritten.contains(item)) {
            itemsWritten.remove(item);
            System.out.println("Item removed successfully.");
        } else {
            System.err.println("Item not found.");
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID=" + authorID +
                ", authorName='" + authorName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", itemsWritten=" + itemsWritten +
                '}';
    }

    public static Author createAuthorWithConfirmation() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter author name: ");
            String name = scanner.nextLine();

            System.out.print("Enter date of birth (dd/MM/yyyy): ");
            String dob = scanner.nextLine();

            LocalDate dateOfBirth = new Author(name, dob).getDateOfBirth();

            System.out.println("Author Details:");
            System.out.println("Name: " + name);
            System.out.println("Date of Birth: " + dob);
            // This line does not yet work. It should print the items written by the author. Review Week 12 notes.
            System.out.println("Items Written: " + new ArrayList<LibraryItem>());

            System.out.print("Confirm creation of author (yes [y]/no [n]): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                return new Author(name, dateOfBirth);
            } else {
                System.out.println("Author creation canceled.");
                return null;
            }
        }
    }
}
