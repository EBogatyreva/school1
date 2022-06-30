-- liquibase formatted sql
-- changeset  L:1

CREATE INDEX student_name_index ON student (name);
CREATE INDEX faculty_nc_idx ON faculty (name, color);