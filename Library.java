package librarymanagement;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Author> authors;
    private List<LibraryItem> items;
    private List<Patron> patrons;

    public Library() {
        this.authors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public synchronized void addAuthor(Author author) {
        if (author != null && !authors.contains(author)) {
            authors.add(author);
            System.out.println("Author added successfully.");
        } else {
            System.err.println("Invalid author or author already exists.");
        }
    }

    public synchronized void addItem(LibraryItem item) {
        if (item != null && !items.contains(item)) {
            items.add(item);
            System.out.println("Library item added successfully.");
        } else {
            System.err.println("Invalid item or item already exists.");
        }
    }

    public synchronized void addPatron(Patron patron) {
        if (patron != null && !patrons.contains(patron)) {
            patrons.add(patron);
            System.out.println("Patron added successfully.");
        } else {
            System.err.println("Invalid patron or patron already exists.");
        }
    }

    public synchronized void saveLibraryData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Library_data.dat"))) {
            out.writeObject(authors);
            out.writeObject(items);
            out.writeObject(patrons);
            System.out.println("Library data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

    public synchronized void saveLibraryDataToTextFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Library_records.txt"))) {
            writer.println("Authors:");
            for (Author author : authors) {
                writer.println(author.getAuthorName() + "," + author.getDateOfBirth().toString());
            }

            writer.println("Items:");
            for (LibraryItem item : items) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    writer.println("Book," + book.getId() + "," + book.getTitle() + "," + book.getAuthor().getAuthorName() + "," + book.getIsbn() + "," + book.getPublisher() + "," + book.getNumberOfCopies() + "," + book.getFormat());
                } else if (item instanceof Periodical) {
                    Periodical periodical = (Periodical) item;
                    writer.println("Periodical," + periodical.getId() + "," + periodical.getTitle() + "," + periodical.getAuthor().getAuthorName() + "," + periodical.getIssueNumber() + "," + periodical.getPublisher() + periodical.getNumberOfCopies()+ "," + periodical.getFormat());
                }
            }

            writer.println("Patrons:");
            for (Patron patron : patrons) {
                if (patron instanceof StudentPatron) {
                    StudentPatron studentPatron = (StudentPatron) patron;
                    writer.println("StudentPatron," + studentPatron.getName() + "," + studentPatron.getAddress() + "," + studentPatron.getPhoneNumber() + "," + studentPatron.getStudentID() + "," + studentPatron.getMajor());
                } else if (patron instanceof EmployeePatron) {
                    EmployeePatron employeePatron = (EmployeePatron) patron;
                    writer.println("EmployeePatron," + employeePatron.getName() + "," + employeePatron.getAddress() + "," + employeePatron.getPhoneNumber() + "," + employeePatron.getEmployeeID() + "," + employeePatron.getDepartment());
                } else {
                    writer.println("Patron," + patron.getName() + "," + patron.getAddress() + "," + patron.getPhoneNumber());
                }
            }

            System.out.println("Library data saved to text file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving library data to text file: " + e.getMessage());
        }
    }
    public synchronized void viewData() {
        System.out.println("Authors:");
        for (Author author : authors) {
            System.out.println(author);
        }

        System.out.println("\nItems:");
        for (LibraryItem item : items) {
            System.out.println(item);
        }

        System.out.println("\nPatrons:");
        for (Patron patron : patrons) {
            System.out.println(patron);
        }
    }

    public synchronized void loadLibraryDataFromTextFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Library_records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("Authors:")) {
                    while ((line = reader.readLine()) != null && !line.equals("Items:")) {
                        String[] authorData = line.split(",");
                        Author author = new Author(authorData[0], LocalDate.parse(authorData[1]));
                        authors.add(author);
                    }
                }
                if (line != null && line.equals("Items:")) {
                    while ((line = reader.readLine()) != null && !line.equals("Patrons:")) {
                        String[] itemData = line.split(",");
                        Author author = findAuthorByName(itemData[3]);
                        if (itemData[0].equals("Book")) {
                            Book book = new Book(itemData[1], itemData[2], author, itemData[4], itemData[5], Integer.parseInt(itemData[6]), Book.Format.valueOf(itemData[7]));
                            items.add(book);
                        } else if (itemData[0].equals("Periodical")) {
                            Periodical periodical = new Periodical(itemData[1], itemData[2], author, Integer.parseInt(itemData[4]), itemData[5]);
                            items.add(periodical);
                        }
                    }
                }
                if (line != null && line.equals("Patrons:")) {
                    while ((line = reader.readLine()) != null) {
                        String[] patronData = line.split(",");
                        Patron patron;
                        if (patronData[0].equals("StudentPatron")) {
                            patron = new StudentPatron(patronData[1], patronData[2], patronData[3], patronData[4], patronData[5]);
                        } else if (patronData[0].equals("EmployeePatron")) {
                            patron = new EmployeePatron(patronData[1], patronData[2], patronData[3], patronData[4], patronData[5]);
                        } else {
                            patron = new Patron(patronData[1], patronData[2], patronData[3]);
                        }
                        patrons.add(patron);
                    }
                }
            }

            System.out.println("Library data loaded from text file successfully.");
        } catch (IOException e) {
            System.err.println("Error loading library data from text file: " + e.getMessage());
        }
    }

    public Author findAuthorByName(String name) {
        for (Author author : authors) {
            if (author.getAuthorName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null;
    }

    public List<Author> getAuthors() {
        return new ArrayList<>(authors);
    }

    public List<LibraryItem> getItems() {
        return new ArrayList<>(items);
    }

    public List<Patron> getPatrons() {
        return new ArrayList<>(patrons);
    }

    public LibraryItem findItemByID(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

	public void deleteItem(LibraryItem item) {
		if (item != null && items.contains(item)) {
            items.remove(item);
            System.out.println("Item removed successfully.");
        } else {
            System.err.println("Item not found.");
        }
	}

	public Patron findPatronByID(String patronID) {
		for (Patron patron : patrons) {
            if (patron.getPatronID() == Integer.parseInt(patronID)) {
                return patron;
            }
        }
        return null;
	}


    public void loadData() {
    File file = new File("Library_records.txt");
    if (!file.exists()) {
        // Data file doesn't exist, so create initial data and save it
        Author author1 = new Author("John Doe", LocalDate.parse("1970-01-01"));
        Author author2 = new Author("Jane Smith", LocalDate.parse("1980-02-02"));

        Book book1 = new Book("1", "Book One", author1, "111-1111111111", "Publisher One", 5, Book.Format.PRINTED);
        Book book2 = new Book("2", "Book Two", author2, "222-2222222222", "Publisher Two", 3, Book.Format.ELECTRONIC);
        Periodical periodical1 = new Periodical("3", "Periodical One", author1, 1, "Publisher Three", 4, Periodical.Format.PRINTED);
        Periodical periodical2 = new Periodical("4", "Periodical Two", author2, 2, "Publisher Four", 2, Periodical.Format.ELECTRONIC);

        addAuthor(author1);
        addAuthor(author2);

        addItem(book1);
        addItem(book2);
        addItem(periodical1);
        addItem(periodical2);

        Patron student1 = new StudentPatron("Alice", "123 Main St", "123-456-7890", "12345", "Computer Science");
        Patron employee1 = new EmployeePatron("Bob", "456 Elm St", "098-765-4321", "54321", "IT");

        addPatron(student1);
        addPatron(employee1);

        // Save the initial data to a new file
        saveLibraryDataToTextFile();
    } else {
        // File exists, load the data from the file
        loadLibraryDataFromTextFile();
    }
}
}



