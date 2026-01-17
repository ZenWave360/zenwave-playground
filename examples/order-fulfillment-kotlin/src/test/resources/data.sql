-- ============================================================================
-- SQL Data Script for Order Fulfillment JPA Project
-- ============================================================================
--
-- PROMPT TO GENERATE THIS FILE:
--
-- Create an SQL data script (data.sql) that inserts exactly one sample record
-- per table for all entities in the project.
--
-- Requirements:
-- - Use nextval('sequence_name') for all ID columns to properly call the database sequences
-- - Respect foreign key dependencies and insert parent records before child records
-- - Populate ALL required fields (NOT NULL columns) with realistic sample data
-- - Include optional fields where it makes sense for a complete example
-- - Use proper SQL syntax for:
--   * JSON/JSONB columns (use valid JSON strings)
--   * Array columns (use PostgreSQL array syntax)
--   * LOB/BLOB columns (use appropriate data)
--   * Embedded objects (map to prefixed column names)
--   * Enum values (use the integer representation from converters)
--   * Timestamp fields (use CURRENT_TIMESTAMP where appropriate)
--   * Auditing fields (created_by, created_date, last_modified_by, last_modified_date)
-- - Organize the script by module with clear section comments
-- - Ensure referential integrity (all foreign keys reference valid parent IDs)
-- - Use consistent test data (e.g., user_id=1, hospital_id=1, patient_id=1)
--
-- Format:
-- - Add section headers for each module
-- - Comment each table insertion
-- - Use readable formatting with proper indentation
-- - Include a header comment explaining the script's purpose
--
-- Entity tables in this project:
-- You can find all entities and tables running: grep @Table -R src/main/java/
--
-- ============================================================================

-- ============================================================================
-- ORDER FULFILLMENT MODULE
-- ============================================================================

-- Insert sample Order records for each status
INSERT INTO order_table (
    id,
    version,
    order_number,
    status,
    total_amount,
    currency,
    payment_reference,
    tracking_number,
    items,
    created_by,
    created_date,
    last_modified_by,
    last_modified_date
) VALUES
-- PLACED orders
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-001',
    2, -- PLACED status
    299.99,
    'USD',
    NULL,
    NULL,
    '[{"productId": "PROD-001", "productName": "Wireless Bluetooth Headphones", "quantity": 1, "unitPrice": 199.99}, {"productId": "PROD-002", "productName": "USB-C Cable", "quantity": 2, "unitPrice": 50.00}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-002',
    2, -- PLACED status
    149.99,
    'USD',
    NULL,
    NULL,
    '[{"productId": "PROD-003", "productName": "Smartphone Case", "quantity": 1, "unitPrice": 149.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-003',
    2, -- PLACED status
    89.97,
    'EUR',
    NULL,
    NULL,
    '[{"productId": "PROD-004", "productName": "Wireless Mouse", "quantity": 3, "unitPrice": 29.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-004',
    2, -- PLACED status
    599.99,
    'USD',
    NULL,
    NULL,
    '[{"productId": "PROD-005", "productName": "Gaming Keyboard", "quantity": 1, "unitPrice": 599.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
-- PAID orders
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-005',
    3, -- PAID status
    199.99,
    'USD',
    'PAY-REF-12345',
    NULL,
    '[{"productId": "PROD-006", "productName": "Tablet Stand", "quantity": 1, "unitPrice": 199.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-006',
    3, -- PAID status
    79.98,
    'USD',
    'PAY-REF-12346',
    NULL,
    '[{"productId": "PROD-007", "productName": "Phone Charger", "quantity": 2, "unitPrice": 39.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-007',
    3, -- PAID status
    449.99,
    'EUR',
    'PAY-REF-12347',
    NULL,
    '[{"productId": "PROD-008", "productName": "Bluetooth Speaker", "quantity": 1, "unitPrice": 449.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-008',
    3, -- PAID status
    129.99,
    'USD',
    'PAY-REF-12348',
    NULL,
    '[{"productId": "PROD-009", "productName": "Webcam", "quantity": 1, "unitPrice": 129.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
-- SHIPPED orders
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-009',
    4, -- SHIPPED status
    299.99,
    'USD',
    'PAY-REF-12349',
    'TRK-67890',
    '[{"productId": "PROD-010", "productName": "Monitor", "quantity": 1, "unitPrice": 299.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-010',
    4, -- SHIPPED status
    159.98,
    'USD',
    'PAY-REF-12350',
    'TRK-67891',
    '[{"productId": "PROD-011", "productName": "Desk Lamp", "quantity": 2, "unitPrice": 79.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-011',
    4, -- SHIPPED status
    89.99,
    'EUR',
    'PAY-REF-12351',
    'TRK-67892',
    '[{"productId": "PROD-012", "productName": "Ergonomic Cushion", "quantity": 1, "unitPrice": 89.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-012',
    4, -- SHIPPED status
    249.99,
    'USD',
    'PAY-REF-12352',
    'TRK-67893',
    '[{"productId": "PROD-013", "productName": "Printer", "quantity": 1, "unitPrice": 249.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
-- CANCELLED orders
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-013',
    5, -- CANCELLED status
    99.99,
    'USD',
    NULL,
    NULL,
    '[{"productId": "PROD-014", "productName": "Power Bank", "quantity": 1, "unitPrice": 99.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-014',
    5, -- CANCELLED status
    179.98,
    'USD',
    NULL,
    NULL,
    '[{"productId": "PROD-015", "productName": "External Hard Drive", "quantity": 2, "unitPrice": 89.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-015',
    5, -- CANCELLED status
    349.99,
    'EUR',
    NULL,
    NULL,
    '[{"productId": "PROD-016", "productName": "Graphics Tablet", "quantity": 1, "unitPrice": 349.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
),
(
    nextval('order_table_seq'),
    0,
    'ORD-2024-016',
    5, -- CANCELLED status
    59.99,
    'USD',
    NULL,
    NULL,
    '[{"productId": "PROD-017", "productName": "Cable Organizer", "quantity": 1, "unitPrice": 59.99}]'::jsonb,
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP
);
