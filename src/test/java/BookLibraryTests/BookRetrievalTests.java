package BookLibraryTests;


import BookLibrary.BookRetrieveHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRetrievalTests {
    private static BookRetrieveHelper books;

    @BeforeAll
    public static void setup(){
        books = new BookRetrieveHelper();
    }

    // TODO test right value, wrong input, small input, multiple strings, empty input, null input
    // getAuthorBasedOnBook - Expected
    @Test
    public void getAuthorBasedOnBookCorrectTest(){
        assertEquals("Yuvalnoah Harari", books.getAuthorBasedOnBook("21 Lessons for the 21st Century"), "Test failed: Unable to obtain correct author");
    }

    // getAuthorBasedOnBook - If book not found
    @Test
    public void getAuthorBasedOnBookNotFoundTest(){
        assertEquals("Not Found", books.getAuthorBasedOnBook("La Belle Sauvage: The Book of Dust, Volume One"), "Test failed: Found wrong author");
    }

    // getAuthorBasedOnBook - Wrong input
    @Test
    public void getAuthorBasedOnBookEmptyInputTest(){
        assertEquals("Invalid input", books.getAuthorBasedOnBook(""), "Test failed: Unable to validate input");
    }

    // getAuthorBasedOnBook - Just random work
    @Test
    public void getAuthorBasedOnBookOneWordTest(){
        assertEquals("H.beam Piper", books.getAuthorBasedOnBook("Sapiens"), "Test failed: Unable to obtain correct author");
    }

    // getOneBookBasedOnAuthor() - Expected
    @Test
    public void getOneBookBasedOnAuthorCorrectTest(){
        assertEquals("The Carpet People", books.getOneBookBasedOnAuthor("Terry Pratchett"), "Test failed: Unable to obtain correct book");
    }

    // getOneBookBasedOnAuthor() - If author not found
    @Test
    public void getOneBookBasedOnAuthorNotFoundTest(){
        assertEquals("Not Found", books.getOneBookBasedOnAuthor("3boaa"), "Test failed: Found wrong book");
    }

    // getOneBookBasedOnAuthor() - Wrong input
    @Test
    public void getOneBookBasedOnAuthorEmptyInputTest(){
        assertEquals("Invalid input", books.getOneBookBasedOnAuthor(""), "Test failed: Unable to validate input");
    }

    // getOneBookBasedOnAuthor() - Random work
    @Test
    public void getOneBookBasedOnAuthorOneWordTest(){
        assertEquals("Borreguita and the Coyote", books.getOneBookBasedOnAuthor("book"), "Test failed: Returned wrong book");
    }

}
