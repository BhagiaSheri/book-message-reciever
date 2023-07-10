package books;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BookListener {
    @JmsListener(destination = "book-queue")
    public void receiveMessage(final String bookAsString) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Book book = objectMapper.readValue(bookAsString, Book.class);
            System.out.println("JMS receiver received message:" + book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
