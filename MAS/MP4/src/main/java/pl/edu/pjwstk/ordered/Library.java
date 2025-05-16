package pl.edu.pjwstk.ordered;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {
    private String name;
    private final List<Book> books = new ArrayList<>();

    public Library(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Library name cannot be empty");
        }
        this.name = name;
    }

    public List<Book> getBooks() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingInt(Book::getPublicationYear));
        return Collections.unmodifiableList(sortedBooks);
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        if (!books.contains(book)) {
            books.add(book);
            book.setLibrary(this);
        }
    }

    public void removeBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        if (books.remove(book)) {
            book.removeLibrary();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
