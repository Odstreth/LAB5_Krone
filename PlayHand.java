package poker;

import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class PlayHand {

	public static SessionFactory factory;

	public static void main(String[] args) {

		try{

			factory = new AnnotationConfiguration().
			configure().
			//addPackage("com.xyz") //add package if used.
			addAnnotatedClass(Hand.class).
			buildSessionFactory();

		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}

		
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		for (int gCount = 0; gCount <= 2000000; gCount++) {
			Deck d = new Deck();
			Hand h = new Hand(d);
			h.EvalHand();
			session.save(h);
		}
		tx.commit();
		session.close();

		
	}


}
