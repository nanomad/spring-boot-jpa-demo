INSERT INTO employee(id, name, role)
values (1, 'Giovanni', 'TEACHER');

INSERT INTO employee(id, name, role)
values (2, 'Marco', 'TEACHER');

INSERT INTO employee(id, name, role)
values (3, 'Francesco', NULL);

INSERT INTO project(id, name)
values (1, 'Ciccio');

INSERT INTO project(id, name)
values (2, 'Paperino');

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
