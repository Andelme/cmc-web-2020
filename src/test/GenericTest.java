package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

abstract public class GenericTest {
    private SessionFactory testSessionFactory;
    protected Session testSession;

    @BeforeClass
    protected void setupSession() {
        testSessionFactory = new Configuration().configure().buildSessionFactory();
        testSession = testSessionFactory.openSession();
    }
    @AfterClass
    protected void closeSession() {
        testSession.close();
        testSessionFactory.close();
    }

    @BeforeClass(dependsOnMethods = "setupSession")
    abstract protected void setDAO();
}
