package com.example.springjpademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final DataSource dataSource;
    private final EntityManager entityManager;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(DataSource dataSource, EntityManager entityManager) {
        this.dataSource = dataSource;
        this.entityManager = entityManager;
    }

    @Transactional
    public int deleteEmployeeByName(String name) {
        Query q = entityManager.createQuery(
                "DELETE FROM Employee e where e.name = :param_name"
        );
        q.setParameter("param_name", name);
        return q.executeUpdate();
    }

    @Transactional
    public Collection<Employee> listEmployeesViaJpa() {
        TypedQuery<Employee> query = entityManager.createQuery(
                "select e from Employee e where e.ilRuolo='TEACHER'",
                Employee.class
        );
        List<Employee> resultList = query.getResultList();
        return resultList;
    }

    @Transactional
    public Collection<Employee> listEmployeesOfProject() {
        TypedQuery<Employee> query = entityManager.createQuery(
                // "select e.* from project p
                //  join employee_project ep on p.id = ep.project_id
                //  join employee e on e.id = ep.employee_id
                //  where p.name= :name"
                "select e from Project p join p.employees e " +
                        "where p.name = :name",
                Employee.class
        );
        query.setParameter("name", "Ciccio");
        List<Employee> resultList = query.getResultStream().collect(Collectors.toList());
        return resultList;
    }

    @Transactional
    public Collection<EmployeeDTO> listEmployeesOfProjectUsingDto() {
        TypedQuery<EmployeeDTO> query = entityManager.createQuery(
                // "select e.* from project p
                //  join employee_project ep on p.id = ep.project_id
                //  join employee e on e.id = ep.employee_id
                //  where p.name= :name"
                "select new com.example.springjpademo.EmployeeDTO(e.ilRuolo, e.name) " +
                        "from Project p join p.employees e " +
                        "where p.name = :name",
                EmployeeDTO.class
        );
        query.setParameter("name", "Ciccio");
        List<EmployeeDTO> resultList = query.getResultStream().collect(Collectors.toList());
        return resultList;
    }


    public Collection<EmployeeOld> listEmployees() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from employee where role='TEACHER'");
            Collection<EmployeeOld> result = new ArrayList<>();
            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    EmployeeOld employee = convertToEmployee(resultSet);
                    result.add(employee);
                }
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("Errore durante la query....", e);
            return Collections.emptyList();
        }
    }

    private EmployeeOld convertToEmployee(ResultSet resultSet) throws SQLException {
        EmployeeOld employee = new EmployeeOld();
        int id = resultSet.getInt("id");
        employee.setId(id);
        String name = resultSet.getString("name");
        employee.setName(name);
        String role = resultSet.getString("role");
        employee.setRole(role);
        return employee;
    }
}
