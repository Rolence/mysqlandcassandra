package io.ara.remittance.cassandra.data;

import lombok.Data;

import java.util.UUID;

@Data
public class Student {
    private UUID id;
    private String name;
    private String description;
    private String email;
    private String gender;
    private int age;
    private String group;
    private boolean status;
}
