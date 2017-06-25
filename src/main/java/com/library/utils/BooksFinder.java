package com.library.utils;


import com.library.beans.Book;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BooksFinder {

    private static final String GOOGLE_BOOKS_API_URL
            = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    @Nullable
    private static URL getUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException exc) {
            exc.printStackTrace();
        }
        return null;
    }


    @NotNull
    private static String getStringData(@NotNull InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }


    @Nullable
    private static String requestBookSearch(String isbn) {
        URL searchUrl = getUrl(GOOGLE_BOOKS_API_URL + isbn);
        String data = null;
        if (searchUrl == null) {
            return null;
        }
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) searchUrl.openConnection();
            InputStream inputStream = httpConnection.getInputStream();
            data = getStringData(inputStream);
            inputStream.close();
            httpConnection.disconnect();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return data;
    }


    public static List<Book> searchBook(String isbn) throws JSONException {

        ArrayList<Book> bookList = new ArrayList<>();
        String searchResult = requestBookSearch(isbn);
        if (searchResult == null) {
            return bookList;
        }
        JSONObject jsonObject = new JSONObject(searchResult);
        int numOfBooks = jsonObject.getInt("totalItems");
        if (numOfBooks == 0) {
            return bookList;
        }
        JSONArray jsonArrayOfBooks = jsonObject.getJSONArray("items");
        for (int i = 0; i < numOfBooks; i++) {
            JSONObject bookInfo = jsonArrayOfBooks.getJSONObject(i).getJSONObject("volumeInfo");

            Book book = new Book();

            JSONArray industryIdentifiers = bookInfo.getJSONArray("industryIdentifiers");
            for (int j = 0; j < industryIdentifiers.length(); j++) {
                JSONObject identifier = industryIdentifiers.getJSONObject(j);
                switch (identifier.getString("type")) {
                    case "ISBN_10":
                        book.setIsbn10(identifier.getString("identifier"));
                        break;
                    case "ISBN_13":
                        book.setIsbn13(identifier.getString("identifier"));
                        break;
                }
            }

            if (bookInfo.has("title")) {
                book.setTitle(bookInfo.getString("title"));
            }

            if (bookInfo.has("description")) {
                book.setDescription(bookInfo.getString("description"));
            }

            if (bookInfo.has("pageCount")) {
                book.setPageCount(bookInfo.getInt("pageCount"));
            }

            if (bookInfo.has("authors")) {
                book.setAuthors(bookInfo.getJSONArray("authors").join(", ").replace("[]\"", "").replace("\"", ""));
            }

            if (bookInfo.has("publisher")) {
                book.setPublisher(bookInfo.getString("publisher"));
            }

            if (bookInfo.has("publishedDate")) {
                book.setPublishDate(bookInfo.getString("publishedDate"));
            }

            if (bookInfo.has("categories")) {
                book.setCategory(bookInfo.getJSONArray("categories").join(", ").replace("[]\"", "").replace("\"",""));
            }

            if (bookInfo.has("averageRating")) {
                book.setAvgRating(String.valueOf(bookInfo.getDouble("averageRating")));
            }

            if (bookInfo.has("imageLinks") && bookInfo.getJSONObject("imageLinks").has("thumbnail")) {
                book.setImageUrl(bookInfo.getJSONObject("imageLinks").getString("thumbnail"));
            }

            bookList.add(book);
        }
        return bookList;
    }

}
