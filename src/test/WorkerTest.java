package test;

import spring.dao.impl.WorkerDAOImpl;
import spring.entity.Worker;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class WorkerTest extends GenericTest {
    private WorkerDAOImpl workerDAO;
    private Long test_worker_id;

    @BeforeClass(dependsOnMethods = "setupSession")
    protected void setDAO() {
        workerDAO = new WorkerDAOImpl();
        workerDAO.setSessionFactory(testSessionFactory);
    }
    @Test
    public void getWorker() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        Worker worker = workerDAO.getByID((long) 1);
        Assert.assertNotNull(worker);
        Assert.assertEquals((long) worker.getWorker_id(), 1);
        Assert.assertEquals(worker.getName(), "Афанасьева Екатерина Сергеевна");
        Assert.assertEquals(worker.getBirth_date().toString(), "1980-03-23 00:00:00.0");
        Assert.assertEquals(worker.getAddress(), "Москва, ул.Леваневского, д.77, кв.59");
        Assert.assertEquals(worker.getPhone_number(), "+7(965)874-04-31");
        Assert.assertEquals(worker.getHire_date().toString(), "2005-01-05 00:00:00.0");
        Assert.assertEquals(worker.getEducation_degree(), Worker.DegreeType.doctor);

        Assert.assertNull(workerDAO.getByID((long) 11));

        List<Worker> workers = workerDAO.getByDepartmentName("Andelme Research");
        List<Worker> testWorkers = Arrays.asList(
                workerDAO.getByID((long) 2),
                workerDAO.getByID((long) 3)
        );
        Assert.assertEquals(workers.size(), testWorkers.size());
        for (Worker e : workers) {
            Assert.assertTrue(testWorkers.contains(e));
        }

        Assert.assertEquals(workerDAO.getByDepartmentName("Name").size(), 0);

        workers = workerDAO.getByPostypeName("Лаборант");
        testWorkers = Arrays.asList(
                workerDAO.getByID((long) 6)
        );
        Assert.assertEquals(workers.size(), testWorkers.size());
        for (Worker e : workers) {
            Assert.assertTrue(testWorkers.contains(e));
        }

        Assert.assertEquals(workerDAO.getByPostypeName("Name").size(), 0);

        workers = workerDAO.getByHireDate(Timestamp.valueOf("2000-01-01 00:00:00.0"), Timestamp.valueOf("2010-01-01 00:00:00.0"));
        testWorkers = Arrays.asList(
                workerDAO.getByID((long) 1),
                workerDAO.getByID((long) 2),
                workerDAO.getByID((long) 4),
                workerDAO.getByID((long) 7),
                workerDAO.getByID((long) 9)
        );
        Assert.assertEquals(workers.size(), testWorkers.size());
        for (Worker e : workers) {
            Assert.assertTrue(testWorkers.contains(e));
        }

        Assert.assertEquals(workerDAO.getByHireDate(Timestamp.valueOf("2040-01-01 00:00:00.0"), Timestamp.valueOf("2050-01-01 00:00:00.0")).size(), 0);

        workers = workerDAO.getAll();
        testWorkers = Arrays.asList(
                workerDAO.getByID((long) 1),
                workerDAO.getByID((long) 2),
                workerDAO.getByID((long) 3),
                workerDAO.getByID((long) 4),
                workerDAO.getByID((long) 5),
                workerDAO.getByID((long) 6),
                workerDAO.getByID((long) 7),
                workerDAO.getByID((long) 8),
                workerDAO.getByID((long) 9),
                workerDAO.getByID((long) 10)
        );
        Assert.assertEquals(workers.size(), testWorkers.size());
        for (Worker e : workers) {
            Assert.assertTrue(testWorkers.contains(e));
        }

        transaction.commit();
    }
    @Test(dependsOnMethods = "getWorker")
    public void addWorker() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        Worker worker = new Worker();
        worker.setName("Test Name");
        worker.setBirth_date(Timestamp.valueOf("1990-01-01 00:00:00.0"));
        worker.setAddress("Test");
        worker.setPhone_number("Test phone");
        worker.setHire_date(Timestamp.valueOf("2020-01-01 00:00:00.0"));
        worker.setEducation_degree(Worker.DegreeType.bachelor);
        workerDAO.save(worker);
        test_worker_id = worker.getWorker_id();

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertEquals(workerDAO.getByID(test_worker_id), worker);
        transaction.commit();
    }
    @Test(dependsOnMethods = "addWorker")
    public void updateWorker() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        Worker worker = workerDAO.getByID(test_worker_id);
        Assert.assertNotNull(worker);
        worker.setAddress("Updated test");
        workerDAO.update(worker);

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        worker = workerDAO.getByID(test_worker_id);
        transaction.commit();
        Assert.assertNotNull(worker);
        Assert.assertEquals(worker.getWorker_id(), test_worker_id);
        Assert.assertEquals(worker.getName(), "Test Name");
        Assert.assertEquals(worker.getBirth_date().toString(), "1990-01-01 00:00:00.0");
        Assert.assertEquals(worker.getAddress(), "Updated test");
        Assert.assertEquals(worker.getPhone_number(), "Test phone");
        Assert.assertEquals(worker.getHire_date().toString(), "2020-01-01 00:00:00.0");
        Assert.assertEquals(worker.getEducation_degree(), Worker.DegreeType.bachelor);
    }
    @Test(dependsOnMethods = "updateWorker")
    public void deleteWorker() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        Worker worker = workerDAO.getByID(test_worker_id);
        Assert.assertNotNull(worker);
        workerDAO.delete(worker);

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertNull(workerDAO.getByID(test_worker_id));
        transaction.commit();
    }
}