package io.ara.remittance.cassandra.controller;

import io.ara.remittance.cassandra.data.Student;
import io.ara.remittance.cassandra.service.CassandraServiceInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cass")
public class CassandraController {
    private final CassandraServiceInterface serviceInterface;

    public CassandraController(CassandraServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @PostMapping("/student")
    public ResponseEntity<String > createTutorial(@RequestBody Student student) {
        try {
            System.out.println("Create controller called");
            String  _tutorial = serviceInterface.createStudent(student);
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateTutorial(@PathVariable("id") UUID id, @RequestBody Student student) {
        Student tutorialData = serviceInterface.getStudentById(id);

        if (serviceInterface.existById(id)) {
            return new ResponseEntity<>(serviceInterface.updateStudent(id, student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PreAuthorize("hasAuthority('READ_SENDER')")
    @GetMapping("/student")
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = serviceInterface.getAllStudents();
        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/student/group")
    public ResponseEntity<?> getAllTutorials(@RequestParam(required = false) String group) {
        try {
            List<Student> students = new ArrayList<Student>();

            if (group == null)
                serviceInterface.getAllStudents().forEach(students::add);
            else
                serviceInterface.getStudentByGroup(group).forEach(students::add);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{id}")
//    @PostAuthorize("")
    public ResponseEntity<Student> getTutorialById(@PathVariable("id") UUID id) {
        System.out.println("get mapping by id:  "+id);
        Student student = serviceInterface.getStudentById(id);

        if (serviceInterface.existById(id)) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/status")
    public ResponseEntity<List<Student>> findByPublished() {
        try {
            List<Student> students = this.serviceInterface.getAllStudentsByStatus(true);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
