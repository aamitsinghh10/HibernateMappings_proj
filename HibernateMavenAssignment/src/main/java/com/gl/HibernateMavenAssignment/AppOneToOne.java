package com.gl.HibernateMavenAssignment;

import com.gl.HibernateMavenAssignment.config.HibernateConfig;
import com.gl.HibernateMavenAssignment.entity.Address;
import com.gl.HibernateMavenAssignment.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppOneToOne {
    private static SessionFactory factory = HibernateConfig.getSessionFactory();
    public static void main(String[] args) {
        Address address = new Address();
        address.setCity("Hyderabad");
        address.setState("Andhra Pradesh");
        address.setCountry("India");

        Student student = new Student();
        student.setName("Amit Singh");
        student.setEmail("amitsingh@gmail.com");
        student.setAddress(address);

        address = new Address();
        address.setCity("Banglore");
        address.setState("Karnataka");
        address.setCountry("India");

        student = new Student();
        student.setName("Aditi hundia");
        student.setEmail("aditihundia@gmail.com");
        student.setAddress(address);

        System.out.println("Insert Student's Data");
        insertStudent(student);

        System.out.println("Delete Student's Data");
        deleteStudent(student.getStudentId());
    }
    public static void insertStudent(Student student){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
        session.close();

        System.out.println("Insert Student"+student.toString());
    }
    public static void deleteStudent(int studentId){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = session.find(Student.class,studentId);
        session.remove(student);
        tx.commit();
        session.close();
    }
}
