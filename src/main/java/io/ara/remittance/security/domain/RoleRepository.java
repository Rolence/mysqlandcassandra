package io.ara.remittance.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Role c WHERE c.name = :name")
    Boolean existsByName(@Param("name") final String name);

//    Role findByName(String name);
    Optional<Role> findByName(String name);
}
