package pl.edu.pjwstk.ordered;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private Library library;

    public Book(String title, String author, int publicationYear) {
        setTitle(title);
        setAuthor(author);
        setPublicationYear(publicationYear);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Book author cannot be empty");
        }
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear < 0) {
            throw new IllegalArgumentException("Publication year cannot be negative");
        }
        this.publicationYear = publicationYear;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        if (this.library == library) {
            return;
        }

        if (this.library != null) {
            Library oldLibrary = this.library;
            this.library = null;
            oldLibrary.removeBook(this);
        }

        if (library != null) {
            this.library = library;
            library.addBook(this);
        }
    }

    public void removeLibrary() {
        this.library = null;
    }

    @Override
    public String toString() {
        return title + " (" + publicationYear + ") by " + author;
    }
}
