package io.ara.remittance.model;

import io.ara.remittance.security.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserBaseRepository <T extends User> extends PagingAndSortingRepository<T, Long> {
    Optional<T> findByEmail(final String email);
}
