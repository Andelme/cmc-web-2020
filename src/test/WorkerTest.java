package test;

import dao.WorkerDAO;
import entity.Worker;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class WorkerTest extends GenericTest {
    private WorkerDAO workerDAO;
    private Long test_worker_id;

    @BeforeClass(dependsOnMethods = "setupSession")
    protected void setDAO() {
        workerDAO = new WorkerDAO();
        workerDAO.setSession(testSession);
    }
    @Test
    public void getWorker() {
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

        workers = workerDAO.getByHireDate(Date.valueOf("2000-01-01"), Date.valueOf("2010-01-01"));
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

        Assert.assertEquals(workerDAO.getByHireDate(Date.valueOf("2040-01-01"), Date.valueOf("2050-01-01")).size(), 0);

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
    }
    @Test(dependsOnMethods = "getWorker")
    public void addWorker() {
        Transaction transaction = testSession.beginTransaction();

        Worker worker = new Worker();
        worker.setName("Test Name");
        worker.setBirth_date(Date.valueOf("1990-01-01"));
        worker.setAddress("Test");
        worker.setPhone_number("Test phone");
        worker.setHire_date(Date.valueOf("2020-01-01"));
        worker.setEducation_degree(Worker.DegreeType.bachelor);
        workerDAO.save(worker);
        test_worker_id = worker.getWorker_id();

        transaction.commit();

        Assert.assertEquals(workerDAO.getByID(test_worker_id), worker);
    }
    @Test(dependsOnMethods = "addWorker")
    public void updateWorker() {
        Transaction transaction = testSession.beginTransaction();

        Worker worker = workerDAO.getByID(test_worker_id);
        Assert.assertNotNull(worker);
        worker.setAddress("Updated test");
        workerDAO.update(worker);

        transaction.commit();

        worker = workerDAO.getByID(test_worker_id);
        Assert.assertNotNull(worker);
        Assert.assertEquals(worker.getWorker_id(), test_worker_id);
        Assert.assertEquals(worker.getName(), "Test Name");
        Assert.assertEquals(worker.getBirth_date().toString(), "1990-01-01");
        Assert.assertEquals(worker.getAddress(), "Updated test");
        Assert.assertEquals(worker.getPhone_number(), "Test phone");
        Assert.assertEquals(worker.getHire_date().toString(), "2020-01-01");
        Assert.assertEquals(worker.getEducation_degree(), Worker.DegreeType.bachelor);
    }
    @Test(dependsOnMethods = "updateWorker")
    public void deleteWorker() {
        Transaction transaction = testSession.beginTransaction();

        Worker worker = workerDAO.getByID(test_worker_id);
        Assert.assertNotNull(worker);
        workerDAO.delete(worker);

        transaction.commit();

        Assert.assertNull(workerDAO.getByID(test_worker_id));
    }
}