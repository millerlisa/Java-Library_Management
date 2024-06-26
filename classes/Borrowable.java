package librarymanagement;

public interface Borrowable {
    void borrowItem();
    void returnItem();
	void borrowItem(Patron patron);
    void returnItem(Patron patron);
    void borrowItem(int numberOfCopies);
    void returnItem(int numberOfCopies);
}
