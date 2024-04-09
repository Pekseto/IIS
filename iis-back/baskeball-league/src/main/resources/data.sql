INSERT INTO users (email, password, common_name, given_name, organization, organizational_unit, country, locality, role)
VALUES
    ('admin@example.com', '$2a$10$h9fD5LSSJ/cxG6pysikeOes5ANhA05FDyYi1tiw0mlSEYk8AKQC12', 'Admin1', 'Admin1', 'Company', 'Admin Department', 'SRB', 'Novi Sad', 'ADMIN'),     -- pass admin
    ('admin2@example.com', '$2a$10$h9fD5LSSJ/cxG6pysikeOes5ANhA05FDyYi1tiw0mlSEYk8AKQC12', 'Admin2', 'Admin2', 'Company123', 'Admin Department', 'SRB', 'Beograd', 'ADMIN');  -- pass admin
