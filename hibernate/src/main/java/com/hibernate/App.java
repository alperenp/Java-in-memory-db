package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * This is a basic h2 - hibernate application to show how in-memory database can be constructed and utilized
 * <p>
 * jre 10.0.1 is used on this application
 * 
 * @author alperenp
 *
 */
public class App {
	
	/**
	 * Create {@link SessionFactory} to connect database and execute operations
	 * 
	 * <p>
	 * 1- initialze table and create rows
	 * <p>
	 * 2- load every entry from table and give a console output
	 * <p>
	 * 3- query a single entry from database
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		try {
			persist(sessionFactory);
			load(sessionFactory);
			query(sessionFactory);
		} finally {
			sessionFactory.close();
		}
	}
	
	/**
	 * Step 1: Create objects and save them in database
	 * 
	 * @param sessionFactory
	 */
	private static void persist(SessionFactory sessionFactory) {
		Person p1 = new Person("John", 35);
		Person p2 = new Person("Sina", 30);
		System.out.println("-- persisting persons --");
		System.out.printf("- %s%n- %s%n", p1.getName(), p2.getName());
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(p1);
		session.save(p2);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Step 2: load every entry from table and give a console output
	 * 
	 * @param sessionFactory
	 */
	private static void load(SessionFactory sessionFactory) {
		System.out.println("-- loading persons --");
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Person> persons = session.createQuery("FROM Person").list();
		persons.forEach((x) -> System.out.printf("- %s%n", x.getName()));
		session.close();
	}
	
	/**
	 * Step 3: query a single entry from database
	 * 
	 * @param sessionFactory
	 */
	private static void query(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Person p = session.get(Person.class, 1);
		System.out.println("-- Query person id=1 --");
		System.out.println(p.getName());
		session.close();
	}
}