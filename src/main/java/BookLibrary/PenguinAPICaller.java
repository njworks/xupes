package BookLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * API Request class
 */
public class PenguinAPICaller extends Authenticator {
    final String kuser = "testuser";
    final String kpass = "testpassword";

    private static final Logger LOGGER = Logger.getLogger(PenguinAPICaller.class.getName());

    class RHAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(kuser, kpass.toCharArray()));
        }
    }

    /**
     * GET request from penguin house API
     *
     * @param urlGet values to retrieve
     * @return list of books found related to search
     */
    public List<Book> getAndReturnApiData(String urlGet) {
        LOGGER.info("Called API GET");
        List<Book> books = new ArrayList<Book>();
        Map<String, String> apiReturnData = new HashMap<String, String>();
        Authenticator.setDefault(new RHAuthenticator());
        URL url = null;
        try {
            // Get data from request
            url = new URL("https://reststop.randomhouse.com/resources/" + urlGet);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("Accept", "application/json");
            InputStream is = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String retrievedData;

            // Go over JSON data retrieved and sort it to a map
            while ((retrievedData = reader.readLine()) != null) {
                String[] splitValues = retrievedData.split("\",\"");

                // Can add more data cleaning here, for know its kept simple for the required data
                for (String each : splitValues) {
                    String[] separateKeyValue = each.split("\":\"");
                    apiReturnData.put(separateKeyValue[0], separateKeyValue[1]);
                }

            }
            if (!apiReturnData.isEmpty()) {
                // Get author name and book title and add to return list
                String author = apiReturnData.get("authorweb");
                String title = apiReturnData.get("titleweb");
//                System.out.println(author +"  "+ title);
                if (author != null && title != null) {
                    String[] authorName = author.replace(" ", "").split(",");
                    String fullName = authorName[1].charAt(0) + authorName[1].substring(1).toLowerCase() + " " + authorName[0].charAt(0) + authorName[0].substring(1).toLowerCase();
                    books.add(new Book(title, fullName));
                } else {
                    LOGGER.warning("Author name and title not found from API request");
                }
            }
            reader.close();
            con.disconnect();

            LOGGER.info("Successful Request");
        } catch (MalformedURLException ex) {
            LOGGER.severe("URL incorrect " + ex);
            return books;
        } catch (IOException ioe) {
            LOGGER.severe("IO issue with reading data " + ioe);
            return books;
        } catch (Exception e) {
            LOGGER.severe("Error " + e);
            return books;
        }
        return books;
    }

}
