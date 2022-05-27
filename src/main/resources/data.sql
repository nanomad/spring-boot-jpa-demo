INSERT INTO employee(id, name, role)
values (1, 'Giovanni', 'TEACHER');

INSERT INTO employee(id, name, role)
values (2, 'Marco', 'TEACHER');

INSERT INTO employee(id, name, role)
values (3, 'Francesco', NULL);

INSERT INTO customer(id, name)
VALUES (1, 'BIG_PHARMA');

INSERT INTO customer(id, name)
VALUES (2, 'BIG_CHIPS');

INSERT INTO customer(id, name)
VALUES (3, 'BIG_MAMA');

INSERT INTO project(id, name, customer_id)
values (1, 'Ciccio', 1);

INSERT INTO project(id, name, customer_id)
values (2, 'Paperino', 1);

INSERT INTO employee_project(employee_id, project_id)
VALUES (1, 1);

INSERT INTO employee_project(employee_id, project_id)
VALUES (2, 1);

INSERT INTO employee_project(employee_id, project_id)
VALUES (3, 1);

INSERT INTO employee_project(employee_id, project_id)
VALUES (1, 2);

INSERT INTO employee_project(employee_id, project_id)
VALUES (3, 2);
