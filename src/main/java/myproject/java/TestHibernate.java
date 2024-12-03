package myproject.java;


public class TestHibernate {
	 public static void main(String[] args) {
	        HibernateUtil.getSessionFactory().openSession().close();
	        HibernateUtil.shutdown();
	        System.out.println("Hibernate initialized successfully.");
	    }

}