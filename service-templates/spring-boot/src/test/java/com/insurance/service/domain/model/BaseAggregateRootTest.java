package com.insurance.service.domain.model;

import com.insurance.service.domain.event.DomainEvent;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the BaseAggregateRoot class.
 */
class BaseAggregateRootTest {

    /**
     * Test that a new aggregate is created with default values.
     */
    @Test
    void shouldCreateNewAggregateWithDefaults() {
        // When
        TestAggregate aggregate = new TestAggregate();
        
        // Then
        assertNotNull(aggregate.getId());
        assertNotNull(aggregate.getCreatedAt());
        assertNotNull(aggregate.getUpdatedAt());
        assertEquals(0, aggregate.getVersion());
        assertEquals(0, aggregate.domainEvents().size());
    }
    
    /**
     * Test that an aggregate can be created with a specific ID.
     */
    @Test
    void shouldCreateNewAggregateWithSpecificId() {
        // Given
        UUID id = UUID.randomUUID();
        
        // When
        TestAggregate aggregate = new TestAggregate(id);
        
        // Then
        assertEquals(id, aggregate.getId());
    }
    
    /**
     * Test that events can be registered and retrieved.
     */
    @Test
    void shouldRegisterAndRetrieveEvents() {
        // Given
        TestAggregate aggregate = new TestAggregate();
        TestEvent event1 = new TestEvent();
        TestEvent event2 = new TestEvent();
        
        // When
        aggregate.addEvent(event1);
        aggregate.addEvent(event2);
        
        // Then
        List<DomainEvent> events = aggregate.domainEvents();
        assertEquals(2, events.size());
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
    }
    
    /**
     * Test that marking an aggregate as updated increments the version.
     */
    @Test
    void shouldIncrementVersionWhenMarkedAsUpdated() {
        // Given
        TestAggregate aggregate = new TestAggregate();
        LocalDateTime originalUpdatedAt = aggregate.getUpdatedAt();
        
        // When
        try {
            Thread.sleep(10); // Ensure updatedAt will be different
        } catch (InterruptedException e) {
            // Ignore
        }
        aggregate.doSomething();
        
        // Then
        assertEquals(1, aggregate.getVersion());
        assertTrue(aggregate.getUpdatedAt().isAfter(originalUpdatedAt));
    }
    
    /**
     * Test that events are cleared after calling clearEvents.
     */
    @Test
    void shouldClearEventsAfterPublishing() {
        // Given
        TestAggregate aggregate = new TestAggregate();
        aggregate.addEvent(new TestEvent());
        
        // When
        aggregate.clearEvents();
        
        // Then
        assertEquals(0, aggregate.domainEvents().size());
    }
    
    /**
     * Test implementation of BaseAggregateRoot for testing.
     */
    private static class TestAggregate extends BaseAggregateRoot {
        
        public TestAggregate() {
            super();
        }
        
        public TestAggregate(UUID id) {
            super(id);
        }
        
        public void addEvent(DomainEvent event) {
            registerEvent(event);
        }
        
        public void doSomething() {
            // Simulate some domain logic
            markUpdated();
        }
    }
    
    /**
     * Test implementation of DomainEvent for testing.
     */
    private static class TestEvent implements DomainEvent {
        private final UUID eventId = UUID.randomUUID();
        private final LocalDateTime timestamp = LocalDateTime.now();
        
        @Override
        public UUID getEventId() {
            return eventId;
        }
        
        @Override
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
        
        @Override
        public String getEventType() {
            return "TestEvent";
        }
        
        @Override
        public String getVersion() {
            return "1.0";
        }
    }
} 