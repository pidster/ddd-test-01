-- Create Flyway schema history table
CREATE SCHEMA IF NOT EXISTS public;

-- Example table for an aggregate root entity
CREATE TABLE IF NOT EXISTS example_aggregate (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version INT NOT NULL DEFAULT 0,
    
    -- Business fields (example)
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    amount DECIMAL(19, 2)
);

-- Example table for a value object
CREATE TABLE IF NOT EXISTS example_value_object (
    id UUID PRIMARY KEY,
    aggregate_id UUID NOT NULL REFERENCES example_aggregate(id),
    created_at TIMESTAMP NOT NULL,
    
    -- Business fields (example)
    value_type VARCHAR(50) NOT NULL,
    value_data JSONB NOT NULL
);

-- Example table for storing outbox messages for reliable event publishing
CREATE TABLE IF NOT EXISTS outbox_events (
    id UUID PRIMARY KEY,
    aggregate_type VARCHAR(255) NOT NULL,
    aggregate_id UUID NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    payload JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL,
    processed BOOLEAN NOT NULL DEFAULT FALSE,
    processed_at TIMESTAMP
);

-- Indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_example_aggregate_status ON example_aggregate(status);
CREATE INDEX IF NOT EXISTS idx_example_value_object_aggregate_id ON example_value_object(aggregate_id);
CREATE INDEX IF NOT EXISTS idx_outbox_events_processed ON outbox_events(processed);
CREATE INDEX IF NOT EXISTS idx_outbox_events_aggregate ON outbox_events(aggregate_type, aggregate_id); 