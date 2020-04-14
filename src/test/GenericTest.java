package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

abstract public class GenericTest {
    protected SessionFactory testSessionFactory;

    @BeforeClass
    protected void setupSession() {
        testSessionFactory = new Configuration().configure().buildSessionFactory();
    }
    @AfterClass
    protected void closeSession() {
        testSessionFactory.close();
    }

    @BeforeClass(dependsOnMethods = "setupSession")
    abstract protected void setDAO();
}
