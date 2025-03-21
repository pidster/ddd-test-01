package com.insurance.service.infrastructure.persistence;

import com.insurance.service.domain.model.BaseAggregateRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Base repository interface for domain aggregates.
 * Extends Spring Data JPA's JpaRepository with additional functionality.
 *
 * @param <T> The aggregate type
 */
@NoRepositoryBean
public interface AggregateRepository<T extends BaseAggregateRoot> extends JpaRepository<T, UUID> {
    
    /**
     * Example of custom query method that could be added to all aggregate repositories.
     * Find an aggregate by its business key (domain identifier rather than technical ID).
     * 
     * @param businessKey The domain-specific identifier
     * @return The aggregate, if found
     */
    // T findByBusinessKey(String businessKey);
} 