package io.ara.remittance.cassandra.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.ara.remittance.cassandra.data.Student;
import io.ara.remittance.cassandra.model.StudentEntity;
import io.ara.remittance.cassandra.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CassandraServiceImpl implements CassandraServiceInterface{
    private final StudentRepository repository;
    private final JmsTemplate jmsTemplate;

    public CassandraServiceImpl(StudentRepository repository
            , JmsTemplate jmsTemplate
    ) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public String createStudent(Student student) {
        System.out.println("Create Student service called");

        StudentEntity entity = StudentEntity.builder()
                .age(student.getAge())
                .description(student.getDescription())
                .email(student.getEmail())
                .name(student.getName())
                .gender(student.getGender())
                .status(student.isStatus())
                .id(Uuids.timeBased())
                .group(student.getGroup())
                .build();
        UUID id = this.repository.save(entity).getId();
        System.out.println("this.repository.save(entity).getId(); called");
        jmsTemplate.convertAndSend("CreateStudent","entity");
        return id.toString();
    }

    @Override
    public Student getStudentById(UUID id) {
        Student student = new Student();
        StudentEntity entity = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(entity,student);
        return student;
    }

    @Override
    public Student getStudentByEmail(String email) {
        Student student = new Student();
        BeanUtils.copyProperties(repository.findByEmailContaining(email),student);
        return student;
    }

    @Override
    public List<Student> getStudentByGroup(String group) {
        List<Student> students = new ArrayList<>();
        List<StudentEntity> entities = repository.findByGroupContaining(group);
        System.out.println("Ent size fotbnb   "+entities.size());
        entities.forEach(en -> {
            Student student = new Student();
            BeanUtils.copyProperties(en, student);
            students.add(student);
        });
//        BeanUtils.copyProperties(entities,students);
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        List<StudentEntity> entities = repository.findAll();
        System.out.println("Ent size findAll   "+entities.size());
        entities.forEach(en -> {
            Student student = new Student();
            BeanUtils.copyProperties(en, student);
            students.add(student);
        });
//        BeanUtils.copyProperties(entities, students);
        return students;
    }

    @Override
    public List<Student> getAllStudentsByStatus(boolean status) {
        List<Student> students = new ArrayList<>();
        List<StudentEntity> entities = repository.findByStatus(status);
        entities.forEach(en -> {
            Student student = new Student();
            BeanUtils.copyProperties(en, student);
            students.add(student);
        });
//        BeanUtils.copyProperties(entities,students);
        return students;
    }

    @Override
    public Student updateStudent(UUID id, Student student) {
        Optional<StudentEntity> entity = repository.findById(id);
        if (entity.isPresent()){
            StudentEntity t = entity.get();
            t.setAge(student.getAge());
            t.setDescription(student.getDescription());
            t.setEmail(student.getEmail());
            t.setGender(student.getGender());
            t.setName(student.getName());
            t.setStatus(student.isStatus());
            t.setGroup(student.getGroup());
            repository.save(t);
            jmsTemplate.convertAndSend("Update Student",t);
        }

        return student;
    }

    @Override
    public boolean existById(UUID id) {
        return repository.existsById(id);
    }
}
