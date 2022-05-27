CREATE TABLE employee
(
    id   int primary key,
    name varchar(255) not null,
    role varchar(255)
);

create table customer
(
    id   int primary key,
    name longvarchar not null
);

create table project
(
    id          int primary key,
    name        longvarchar not null,
    customer_id int         not null references customer (id)
);

create table employee_project
(
    employee_id int not null references employee (id),
    project_id  int not null references project (id),
    unique (employee_id, project_id)
);