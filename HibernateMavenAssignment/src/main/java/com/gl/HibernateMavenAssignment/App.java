package com.gl.HibernateMavenAssignment;

import com.gl.HibernateMavenAssignment.config.HibernateConfig;
import com.gl.HibernateMavenAssignment.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static SessionFactory factory = HibernateConfig.getSessionFactory();
    public static void main( String[] args )
    {
        Teacher t1 = new Teacher();
        t1.setFirstName("Amit");
        t1.setLastName("Singh");
        t1.setEmail("amitsingh.hw@gmail.com");
        insertTeacher(t1);

        t1 = new Teacher();
        t1.setFirstName("Prachi");
        t1.setLastName("Singh");
        t1.setEmail("prachisingh@gmail.com");
        insertTeacher(t1);

        t1 = new Teacher();
        t1.setFirstName("Pranay");
        t1.setLastName("Kathale");
        t1.setEmail("pranayKathale2000@gmail.com");
        insertTeacher(t1);

        t1 = new Teacher();
        t1.setFirstName("Rocky");
        t1.setLastName("Star");
        t1.setEmail("rockstar@gmail.com");
        //Insert Teacher data
        insertTeacher(t1);

        //Update Data
        t1.setFirstName("Jack");
        t1.setLastName("Daniels");
        updateTeacher(t1);

        Teacher readTeacher = readTeacher(t1.getId());
        System.out.println("Read Teacher: " + readTeacher.toString());

        deleteTeacher(t1.getId());

        System.out.println("Get all Queries:");
        getAllTeachers();
    }
    public static void insertTeacher(Teacher teacher){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(teacher);
        tx.commit();
        session.close();

        System.out.println("Teacher Data Inserted"+teacher.toString());
    }
    public static void updateTeacher(Teacher teacher){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(teacher);
        tx.commit();
        session.close();

        System.out.println("Teacher's Data Updated"+teacher.toString());
    }
    public static void deleteTeacher(int tid)
    {
        // Create a session
        // DML commit => transaction
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Teacher teacher = session.get(Teacher.class,tid);
        session.remove(teacher);
        tx.commit();
        session.close();

        System.out.println("Deleted Teacher's Id"+tid);
    }

    public static Teacher readTeacher(int tid){
        Session session = factory.openSession();
        //Transaction tx = session.beginTransaction();
        Teacher teacher = session.get(Teacher.class,tid);
        session.close();

        return teacher;
    }
    public static void getAllTeachers(){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        //List<Teacher> result = new ArrayList<Teacher>();
        List<Teacher> teachers = session.createQuery("from teacher", Teacher.class).getResultList();
        for(Teacher teacher : teachers){
            System.out.println(teacher.toString());
        }
    }
}
