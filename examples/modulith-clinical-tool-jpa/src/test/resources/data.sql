-- ============================================================================
-- SQL Data Script for Clinical Tool JPA Project
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
-- Users Module
-- ============================================================================
INSERT INTO application_user (id, version, name, username, email, password, roles, enabled, credentials_non_expired, account_non_expired, account_non_locked, additional_properties, created_by, created_date, last_modified_by, last_modified_date)
VALUES (nextval('application_user_seq'), 0, 'Admin User', 'admin', 'user@email.com', '$2a$10$I86fM/OvHg8ounuxGq2oBufCecu3NFO4vT1SWIvd4hnM72lrzdXBG', ARRAY['USER', 'ADMIN'], true, true, true, true, '{"theme": "dark", "notifications": true}', 'user', '2024-08-02 13:36:47.389586', 'system', '2024-08-02 13:36:47.389586');

-- ============================================================================
-- Clinical Module
-- ============================================================================

-- Hospital
INSERT INTO hospital (id, version, name, lang, timezone)
VALUES (nextval('hospital_seq'), 0, 'Test Hospital', 'en', 'UTC');

-- Doctor
INSERT INTO doctor (id, version, user_id, profile_picture_id, hospital_id, name, surname, surname2, email, phone_number, lang, created_by, created_date, last_modified_by, last_modified_date)
VALUES (nextval('doctor_seq'), 0, 1, 1, 1, 'Jane', 'Smith', 'Johnson', 'jane.smith@example.com', '1234567890', 'en', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Patient
INSERT INTO patient (id, version, user_id, hospital_id, profile_picture_id, phone_number, his_number, email,
                     general_info_name, general_info_surname, general_info_surname2, general_info_identity_document_type, general_info_identity_document_number, general_info_birth_date, general_info_gender, general_info_lang,
                     health_insurance_info_insurance_company_id, health_insurance_info_insurance_card_number,
                     document_ids, created_by, created_date, last_modified_by, last_modified_date)
VALUES (nextval('patient_seq'), 0, 1, 1, 1, '0987654321', 'HIS123456', 'patient@example.com',
        'Alice', 'Brown', 'Williams', 1, '12345678A', '1990-01-15', 1, 'en',
        'INS001', 'CARD123456',
        '[1, 2, 3]'::jsonb, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Address
INSERT INTO address (id, version, street, city, postal_code, country_code, additional_info)
VALUES (nextval('address_seq'), 0, '123 Main Street', 'Madrid', '28001', 'ESP', 'Near central park');

-- PatientAddress
INSERT INTO patient_address (id, version, street, city, postal_code, country_code, additional_info, current, patient_id)
VALUES (nextval('patient_address_seq'), 0, '456 Oak Avenue', 'Barcelona', '08001', 'ESP', 'Apartment 3B', true, 1);

-- HospitalAddress
INSERT INTO hospital_address (id, version, street, city, postal_code, country_code, additional_info, patient_id)
VALUES (nextval('hospital_address_seq'), 0, '789 Hospital Road', 'Valencia', '46001', 'ESP', 'Main entrance', 1);

-- MedicalContact
INSERT INTO medical_contact (id, version, name, surname, surname2, hospital, area, job_position, phone_number, email, patient_id)
VALUES (nextval('medical_contact_seq'), 0, 'Dr. Robert', 'Garcia', 'Martinez', 'General Hospital', 'Cardiology', 'Cardiologist', '1112223333', 'robert.garcia@hospital.com', 1);

-- PersonalContact
INSERT INTO personal_contact (id, version, name, surname, surname2, phone_number, email, patient_relationship_type, emergency_contact, patient_id)
VALUES (nextval('personal_contact_seq'), 0, 'Maria', 'Lopez', 'Fernandez', '4445556666', 'maria.lopez@example.com', 1, true, 1);

-- PatientWearable
INSERT INTO patient_wearable (id, version, wearable_type, serial_number, patient_id)
VALUES (nextval('patient_wearable_seq'), 0, 'Fitbit', 'SN123456789', 1);

-- ============================================================================
-- Documents Module
-- ============================================================================

-- DocumentInfo
INSERT INTO document_info (id, version, uuid, file_name, document_type, content_type, tags)
VALUES (nextval('document_info_seq'), 0, 'uuid-1234-5678-9012', 'medical_report.pdf', 'MEDICAL_REPORT', 'application/pdf', ARRAY['medical', 'report', '2024']);

-- DocumentData
INSERT INTO document_data (id, version, data, document_id)
VALUES (nextval('document_data_seq'), 0, lo_from_bytea(0, decode('89504E470D0A1A0A', 'hex')), 1);

-- ============================================================================
-- Master Data Module
-- ============================================================================

-- MasterData
INSERT INTO master_data (id, version, type, key, value, translations)
VALUES (nextval('master_data_seq'), 0, 1, 'MALE', 'Male', '[{"lang": "en", "text": "Male"}, {"lang": "es", "text": "Masculino"}]');

-- ============================================================================
-- Surveys Module
-- ============================================================================

-- Survey
INSERT INTO survey (id, version, name, hospital_id, title, lang, sections, created_by, created_date, last_modified_by, last_modified_date)
VALUES (nextval('survey_seq'), 0, 'patient-satisfaction-survey', 1, 'Patient Satisfaction Survey', 'en',
    '[{"name": "general", "questionIds": [1, 2]}]', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- Question
INSERT INTO question (id, version, name, question_type, required, range_start, range_end, translations, options, include_other, created_by, created_date, last_modified_by, last_modified_date)
VALUES (nextval('question_seq'), 0, 'satisfaction-question', 4, true, 1, 10,
    '[{"lang": "en", "text": "How satisfied are you with our service?"}]',
    '[{"name": "very-satisfied", "translations": [{"lang": "en", "text": "Very Satisfied"}]}]',
    false, 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- SurveyAnswers
INSERT INTO survey_answers (id, version, survey_id, patient_id, date, lang, answers, created_by, created_date, last_modified_by, last_modified_date)
VALUES (nextval('survey_answers_seq'), 0, 1, 1, '2024-12-12', 'en',
    '[{"questionId": 1, "value": "8"}]', 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP);

-- ============================================================================
-- Terms and Conditions Module
-- ============================================================================

-- TermsAndConditions
INSERT INTO terms_and_conditions (id, version, content, lang, content_version, start_date)
VALUES (nextval('terms_and_conditions_seq'), 0, lo_from_bytea(0, convert_to('These are the terms and conditions for using the clinical tool application. By using this application, you agree to comply with all applicable laws and regulations.', 'UTF8')), 'en', '1.0.0', '2024-01-01');

-- AcceptedTermsAndConditions
INSERT INTO accepted_terms_and_conditions (id, version, user_id, terms_and_conditions_id, accepted_date)
VALUES (nextval('accepted_terms_and_conditions_seq'), 0, 1, 1, CURRENT_TIMESTAMP);
