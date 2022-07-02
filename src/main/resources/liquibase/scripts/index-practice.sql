-- liquibase formatted sql
-- changeset  L:1

CREATE INDEX student_name_index ON student (name);

-- changeset  L:2

CREATE INDEX faculty_nc_idx ON faculty (name, color);