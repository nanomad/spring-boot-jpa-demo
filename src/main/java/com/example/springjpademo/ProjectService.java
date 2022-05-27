package com.example.springjpademo;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final EntityManager em;

    public ProjectService(EntityManager em) {
        this.em = em;
    }

    public Collection<ProjectDTO> findProjectByCustomer(String customer){
        TypedQuery<ProjectDTO> q = em.createQuery("select new com.example.springjpademo.ProjectDTO(p.name) from Project p " +
            "where p.customer.name = :name", ProjectDTO.class);
        q.setParameter("name", customer);
        return q.getResultList();
    }

    public Collection<CustomerDTO> findCustomerByEmployee(String employee){
        TypedQuery<CustomerDTO> q = em.createQuery("select new com.example.springjpademo.CustomerDTO(p.customer.name) " +
            "from Project p join p.employees e " +
            "where e.name = :name", CustomerDTO.class);
        q.setParameter("name", employee);
        return q.getResultList();
    }
}
