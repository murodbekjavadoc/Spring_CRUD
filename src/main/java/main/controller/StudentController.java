package main.controller;

import main.exs.AppBadException;
import main.madel.Student;
import main.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Student student) {
        try {
            studentService.create(student);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400
        }
        return ResponseEntity.ok(true);    //200
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> all() {
        return ResponseEntity.ok(studentService.getall());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.deleteById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Student student,
                                    @PathVariable("id") String id) {
        try {
            studentService.updateById(student, id);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Student>> search(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "surname", required = false) String surname) {
        return ResponseEntity.ok(studentService.searchByName(name, surname));
    }
}
