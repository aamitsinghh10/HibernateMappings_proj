package com.gl.HibernateMavenAssignment;

import com.gl.HibernateMavenAssignment.config.HibernateConfig;
import com.gl.HibernateMavenAssignment.entity.Employee;
import com.gl.HibernateMavenAssignment.entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AppManyToManyBidirectional {
    private static SessionFactory factory = HibernateConfig.getSessionFactory();
    public static void main(String[] args) {

        Employee e1 = new Employee();
        e1.setName("ranbir kapoor");
        e1.setEmail("ranbir@gmail.com");

        Employee e2 = new Employee();
        e2.setName("Shraddha kapoor");
        e2.setEmail("sradhu@email.com");

        Project p1 = new Project();
        p1.setProjName("Tu Joothi main makkar");
        p1.setProjClient("red chillies");

        Project p2 = new Project();
        p2.setProjName("Yeh Jawani ha diwani");
        p2.setProjClient("Yashraj films");

        List<Employee> employees = new ArrayList<Employee>();
        List<Project> projects = new ArrayList<Project>();
        System.out.println("Adding data to employees table:");
        employees.add(e1);
        employees.add(e2);

        p1.setEmployees(employees);
        p2.setEmployees(employees);

        System.out.println("Adding data to projects table:");
        projects.add(p1);
        projects.add(p2);

        e1.setProjects(projects);
        e2.setProjects(projects);

        System.out.println("Saving Employees and Projects:");
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(e1);
        session.persist(e2);

        tx.commit();
        session.close();
    }
}
