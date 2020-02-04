package BookLibrary;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Few test calls
     */
    public static void main(String[] args) {
        BookRetrieveHelper book = new BookRetrieveHelper();
        LOGGER.info(book.getAuthorBasedOnBook("21 Lessons for the 21st Century"));
        LOGGER.info(book.getOneBookBasedOnAuthor("Tony Abbott"));
    }
}
