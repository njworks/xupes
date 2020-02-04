package BookLibrary;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.logging.Logger;

/**
 * Find books based on different valid params
 */
public class BookRetrieveHelper {

    private PenguinAPICaller penguinAPICaller;
    private static final Logger LOGGER = Logger.getLogger(BookRetrieveHelper.class.getName());

    public BookRetrieveHelper() {
        penguinAPICaller = new PenguinAPICaller();
    }

    /**
     * Check if user input is valid
     */
    private boolean checkIfValidInput(String value) {
        return value != null && value.length() > 0;
    }

    /**
     * Checks for author based on book title
     *
     * @param bookTitle Book title
     * @return Author's full name based on book. If none found, will return 'Not Found'.
     */
    public String getAuthorBasedOnBook(String bookTitle) {
        if (!checkIfValidInput(bookTitle.trim())) {
            return "Invalid input";
        }
        List<Book> authorByBook = penguinAPICaller
                .getAndReturnApiData("works/?start=0&max=1&expandLevel=1&search=" + bookTitle.trim().replace(" ", "%20").toLowerCase());
        if (!authorByBook.isEmpty()) {
            LOGGER.info(String.format("Found author %s for book %s", authorByBook.get(0).getAuthor(), bookTitle));
            return authorByBook.get(0).getAuthor();
        }
        return "Not Found";
    }

    /**
     * Returns first book based on author.
     *
     * @param author: Enter author's name
     * @return Book name by the author. If none found, will return 'Not Found'.
     */
    public String getOneBookBasedOnAuthor(String author) {
        if (!checkIfValidInput(author.trim())) {
            return "Invalid input";
        }
        List<Book> bookByAuthor = penguinAPICaller
                .getAndReturnApiData("works/?start=0&max=1&expandLevel=1&search=" + author.trim().replace(" ", "%20").toLowerCase());
        if (!bookByAuthor.isEmpty()) {
            LOGGER.info(String.format("Found book by author %s -> %s", author, bookByAuthor.get(0).getTitle()));
            return bookByAuthor.get(0).getTitle();
        }
        return "Not Found";
    }

}
