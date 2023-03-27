package io.ara.remittance.cassandra.service;

import io.ara.remittance.cassandra.data.Student;

import java.util.List;
import java.util.UUID;

public interface CassandraServiceInterface {
    public String  createStudent(Student student);
    public Student getStudentById(UUID id);
    public Student getStudentByEmail(String email);
    public List<Student> getStudentByGroup(String email);
    public List<Student> getAllStudents();
    public List<Student> getAllStudentsByStatus(boolean status);
    public Student updateStudent(UUID id, Student student);
    boolean existById(UUID id);
}
