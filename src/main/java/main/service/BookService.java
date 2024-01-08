package main.service;

import main.exs.AppBadException;
import main.madel.Book;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private final List<Book> bookList = new LinkedList<>();

    public BookService() {
        Book book1 = new Book();
        book1.setId(UUID.randomUUID().toString());
        book1.setTitle("Hadis");
        book1.setPublisherYear(LocalDate.now());
        bookList.add(book1);

        Book book2 = new Book();
        book2.setId(UUID.randomUUID().toString());
        book2.setTitle("Java Point");
        book2.setPublisherYear(LocalDate.now());
        bookList.add(book2);

        Book book3 = new Book();
        book3.setId(UUID.randomUUID().toString());
        book3.setTitle("Task");
        book3.setPublisherYear(LocalDate.now());
        bookList.add(book3);

        Book book4 = new Book();
        book4.setId(UUID.randomUUID().toString());
        book4.setTitle("Spring");
        book4.setPublisherYear(LocalDate.now());
        bookList.add(book4);
    }

    public Boolean createBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().length() < 3) {
            throw new AppBadException("invalid parameter");
        }
        return true;
    }

    public List<Book> allList() {
        return bookList;
    }

    public Book get(String id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        return bookList.removeIf(book -> book.getId().equals(id));
    }

    public boolean update(Book bk, String id) {
        List<Book> result = new LinkedList<>();
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(bk.getTitle());
                bk.setPublisherYear(LocalDate.now());
                return true;
            }
        }
        return false;
    }

    public List<Book> search(String name) {
        List<Book> result = new LinkedList<>();
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(name)) {
                result.add(book);
            }
        }
        return result;
    }
}
