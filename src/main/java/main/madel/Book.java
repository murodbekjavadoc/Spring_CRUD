package main.madel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Book {
    private String id;
    private String title;
    private String author;
    private LocalDate publisherYear;
}
