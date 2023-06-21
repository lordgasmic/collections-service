package com.lordgasmic.collections.bookbujo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordgasmic.collections.bookbujo.models.BookResponse;
import com.lordgasmic.collections.bookbujo.models.Identifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class LookupService {

    private static final String BOOK_API_KEY = "AIzaSyDqX9gYU-uEDSVfrLTURllDYQRzlw1xvNw";
    private static final String BOOK_FIELDS = "kind,items(volumeInfo/title,volumeInfo/authors,volumeInfo/pageCount,volumeInfo/categories,volumeInfo/industryIdentifiers)";


    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public LookupService() {
        this.client = HttpClient.newBuilder().build();
        this.objectMapper = new ObjectMapper();
    }

    public Object getBookDataByISBN(long isbn) throws URISyntaxException, IOException, InterruptedException {
        String sb = "q=isbn:" + isbn + "&" + "key=" + BOOK_API_KEY + "&" + "maxResults=1" + "&" + "fields=" + BOOK_FIELDS;

        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.googleapis.com/books/v1/volumes?" + sb)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        BookResponse bookResponse = objectMapper.readValue(response.body(), BookResponse.class);
        boolean found = false;
        for (Identifier identifier : bookResponse.getItems().get(0).getVolumeInfo().getIndustryIdentifiers()) {
            if (identifier.getType().equals("ISBN_13")) {
                long foundISBN = Long.parseLong(identifier.getIdentifier());
                if (isbn != foundISBN) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "isbn did not match");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find isbn in api");
        }
        return bookResponse;
    }
}
