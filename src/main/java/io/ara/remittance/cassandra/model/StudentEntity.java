package io.ara.remittance.cassandra.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Data
@Builder
public class StudentEntity {
    @PrimaryKey
    private UUID id;

    private String name;
    private String description;
    private String email;
    private String gender;
    private int age;
    private String group;
    private boolean status;
}
