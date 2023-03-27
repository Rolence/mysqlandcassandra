package io.ara.remittance.cassandra.repository;

import io.ara.remittance.cassandra.model.StudentEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends CassandraRepository<StudentEntity, UUID> {
    @AllowFiltering
    List<StudentEntity> findByStatus(boolean status);

    StudentEntity findByEmailContaining(String email);
    List<StudentEntity> findByGroupContaining(String email);
}