package main.madel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Student {
    private String Id;
    private String name;
    private String surname;
    private String phone;
    private LocalDate createdDate;
}
