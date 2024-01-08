package main.controller;

import main.exs.AppBadException;
import main.madel.Book;
import main.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity createBook(@RequestBody Book book) {
        try {
            bookService.createBook(book);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> all() {
        return ResponseEntity.ok(bookService.allList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(bookService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") String id){
        return ResponseEntity.ok(bookService.delete(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Book book,@PathVariable("id") String id){
        try {
        bookService.update(book, id);
        }catch (AppBadException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam(value = "name",required = false) String name){
       return ResponseEntity.ok(bookService.search(name));
    }
}

