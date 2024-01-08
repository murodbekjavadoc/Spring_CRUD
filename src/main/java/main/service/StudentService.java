package main.service;

import main.exs.AppBadException;
import main.madel.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentService {
    public final List<Student> studentList = new LinkedList<>();


    public StudentService() {
        Student s1 = new Student();
        s1.setId(UUID.randomUUID().toString());
        s1.setName("Ali");
        s1.setSurname("Aliyev");
        s1.setPhone("+998 99 795 55 51");
        s1.setCreatedDate(LocalDate.now());
        studentList.add(s1);

        Student s2 = new Student();
        s2.setId(UUID.randomUUID().toString());
        s2.setName("Vali");
        s2.setSurname("valiyev");
        s2.setPhone("+998 99 795 28 64");
        s2.setCreatedDate(LocalDate.now());
        studentList.add(s2);

        Student s3 = new Student();
        s3.setId(UUID.randomUUID().toString());
        s3.setName("Toshmat");
        s3.setSurname("Toshmatov");
        s3.setPhone("+998 79 494 15 24");
        s3.setCreatedDate(LocalDate.now());
        studentList.add(s3);

        Student s4 = new Student();
        s4.setId(UUID.randomUUID().toString());
        s4.setName("Eshmat");
        s4.setSurname("Eshmatov");
        s4.setPhone("+998 79 551 54 85");
        s4.setCreatedDate(LocalDate.now());
        studentList.add(s4);

        Student s5 = new Student();
        s5.setId(UUID.randomUUID().toString());
        s5.setName("Azam");
        s5.setSurname("Azamov");
        s5.setPhone("+998 88 996 66 56");
        s5.setCreatedDate(LocalDate.now());
        studentList.add(s5);


        Student s6 = new Student();
        s6.setId(UUID.randomUUID().toString());
        s6.setName("Beka");
        s6.setSurname("Bekov");
        s6.setPhone("+998 789 45 56");
        s6.setCreatedDate(LocalDate.now());
        studentList.add(s6);
    }

    public Boolean create(Student student) {
        if (student.getName() == null || student.getName().trim().length() < 3) {
            throw new AppBadException("Student name required ");
        } else if (student.getSurname() == null || student.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        if (student.getPhone() !=  null){
            if (!phoneNumberValidator(student.getPhone())) {
                throw new AppBadException("Phone number Invalidator");
            }
        }
            student.setId(UUID.randomUUID().toString());
        studentList.add(student);
        return true;
    }

    public List<Student> getall() {

        return studentList;
    }

    public Boolean deleteById(String id) {
        return studentList.removeIf(student -> student.getId().equals(id));
    }

    public Boolean updateById(Student student, String id) {
        for (Student studentLt : studentList) {
            if (studentLt.getId().equals(id)) {
                studentLt.setName(student.getName());
                studentLt.setSurname(student.getSurname());
                return true;
            }
        }
        return false;
    }

    public List<Student> searchByName(String name, String surname) {

        List<Student> result = new LinkedList<>();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(name) ||
                    student.getSurname().toLowerCase().contains(surname)) {
                result.add(student);
            }
        }
        return result;
    }
    private boolean phoneNumberValidator(String phoneNumber) {
        String phoneNumberRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
