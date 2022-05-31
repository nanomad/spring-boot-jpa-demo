package com.example.springjpademo;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

@Service
public class ProjectService {
    public static final Random RNG = new Random();
    private final EntityManager em;

    public ProjectService(EntityManager em) {
        this.em = em;
    }

    public Collection<ProjectDTO> findAllProjects() {
        TypedQuery<ProjectDTO> q = em.createQuery("SELECT new com.example.springjpademo.ProjectDTO(p) FROM Project p", ProjectDTO.class);
        return q.getResultList();
    }
    public Collection<ProjectDTO> findProjectByCustomer(String customer) {
        TypedQuery<ProjectDTO> q = em.createQuery("select new com.example.springjpademo.ProjectDTO(p) from Project p " +
                "where p.customer.name = :name", ProjectDTO.class);
        q.setParameter("name", customer);
        return q.getResultList();
    }

    public Collection<CustomerDTO> findCustomerByEmployee(String employee) {
        TypedQuery<CustomerDTO> q = em.createQuery("select new com.example.springjpademo.CustomerDTO(p.customer.name) " +
                "from Project p join p.employees e " +
                "where e.name = :name", CustomerDTO.class);
        q.setParameter("name", employee);
        return q.getResultList();
    }

    public Employee getById(int id) {
        TypedQuery<Employee> q = em.createQuery(
                "SELECT e from Employee e where e.id=:id",
                Employee.class
        );
        q.setParameter("id", id);
        return q.getSingleResult();
//        return em.find(Employee.class, id);
    }

    @Transactional
    public void createRandomProject() {
        Project p = new Project();
        p.setName("Progetto - " + UUID.randomUUID());
        p.setId(RNG.nextInt());
        p.setCustomer(em.find(Customer.class, 1));

        Employee e = getById(1);
        e.getProjects().add(p);
//        em.persist(p);
    }
}
