package test;

import dao.DepartmentDAO;
import dao.PositionTypeDAO;
import dao.WorkPositionDAO;
import dao.WorkerDAO;
import entity.WorkPosition;
import entity.Worker;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class WorkPositionTest extends GenericTest {
    private WorkPositionDAO workPositionDAO;
    private WorkerDAO workerDAO;
    private PositionTypeDAO positionTypeDAO;
    private DepartmentDAO departmentDAO;
    private Long test_position_id;

    @BeforeClass(dependsOnMethods = "setupSession")
    protected void setDAO() {
        workPositionDAO = new WorkPositionDAO();
        workerDAO = new WorkerDAO();
        positionTypeDAO = new PositionTypeDAO();
        departmentDAO = new DepartmentDAO();
        workPositionDAO.setSessionFactory(testSessionFactory);
        workerDAO.setSessionFactory(testSessionFactory);
        positionTypeDAO.setSessionFactory(testSessionFactory);
        departmentDAO.setSessionFactory(testSessionFactory);
    }
    @Test
    public void getWorkPosition() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        WorkPosition workPosition = workPositionDAO.getByID((long) 1);
        Assert.assertNotNull(workPosition);
        Assert.assertEquals((long) workPosition.getPosition_id(), 1);
        Assert.assertEquals((long) workPosition.getWorker_id().getWorker_id(), 1);
        Assert.assertEquals((long) workPosition.getPostype_id().getPostype_id(), 1);
        Assert.assertEquals(workPosition.getAppointment_date().toString(), "2005-01-05 00:00:00.0");
        Assert.assertNull(workPosition.getRetire_date());
        Assert.assertEquals(workPosition.getWork_rate(), 1.0);
        Assert.assertEquals((long) workPosition.getDepartment_id().getDepartment_id(), 1);

        Assert.assertNull(workPositionDAO.getByID((long) 18));

        List<WorkPosition> workPositions = workPositionDAO.getVacancy();
        List<WorkPosition> testWorkPositions = Arrays.asList(
                workPositionDAO.getByID((long) 13),
                workPositionDAO.getByID((long) 14),
                workPositionDAO.getByID((long) 15),
                workPositionDAO.getByID((long) 16),
                workPositionDAO.getByID((long) 17)
        );
        Assert.assertEquals(workPositions.size(), testWorkPositions.size());
        for (WorkPosition e : workPositions) {
            Assert.assertTrue(testWorkPositions.contains(e));
        }

        workPositions = workPositionDAO.getByWorkerId((long) 7);
        testWorkPositions = Arrays.asList(
                workPositionDAO.getByID((long) 7),
                workPositionDAO.getByID((long) 8),
                workPositionDAO.getByID((long) 9)
        );
        Assert.assertEquals(workPositions.size(), testWorkPositions.size());
        for (WorkPosition e : workPositions) {
            Assert.assertTrue(testWorkPositions.contains(e));
        }

        workPositions = workPositionDAO.getAll();
        testWorkPositions = Arrays.asList(
                workPositionDAO.getByID((long) 1),
                workPositionDAO.getByID((long) 2),
                workPositionDAO.getByID((long) 3),
                workPositionDAO.getByID((long) 4),
                workPositionDAO.getByID((long) 5),
                workPositionDAO.getByID((long) 6),
                workPositionDAO.getByID((long) 7),
                workPositionDAO.getByID((long) 8),
                workPositionDAO.getByID((long) 9),
                workPositionDAO.getByID((long) 10),
                workPositionDAO.getByID((long) 11),
                workPositionDAO.getByID((long) 12),
                workPositionDAO.getByID((long) 13),
                workPositionDAO.getByID((long) 14),
                workPositionDAO.getByID((long) 15),
                workPositionDAO.getByID((long) 16),
                workPositionDAO.getByID((long) 17)
        );
        Assert.assertEquals(workPositions.size(), testWorkPositions.size());
        for (WorkPosition e : workPositions) {
            Assert.assertTrue(testWorkPositions.contains(e));
        }

        transaction.commit();
    }
    @Test(dependsOnMethods = "getWorkPosition")
    public void addWorkPosition() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();
        WorkPosition workPosition = new WorkPosition();
        workPosition.setWorker_id(workerDAO.getByID((long) 1));
        workPosition.setPostype_id(positionTypeDAO.getByID((long) 1));
        workPosition.setAppointment_date(Timestamp.valueOf("2020-01-01 00:00:00.0"));
        workPosition.setWork_rate(1.0);
        workPosition.setDepartment_id(departmentDAO.getByID((long) 1));
        workPositionDAO.save(workPosition);
        test_position_id = workPosition.getPosition_id();

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertEquals(workPositionDAO.getByID(test_position_id), workPosition);
        transaction.commit();
    }
    @Test(dependsOnMethods = "addWorkPosition")
    public void updateWorkPosition() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        WorkPosition workPosition = workPositionDAO.getByID(test_position_id);
        Assert.assertNotNull(workPosition);
        workPosition.setRetire_date(Timestamp.valueOf("2030-01-01 00:00:00.0"));
        workPositionDAO.update(workPosition);

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        workPosition = workPositionDAO.getByID(test_position_id);
        transaction.commit();
        Assert.assertNotNull(workPosition);
        Assert.assertEquals(workPosition.getPosition_id(), test_position_id);
        Assert.assertEquals((long) workPosition.getWorker_id().getWorker_id(), 1);
        Assert.assertEquals((long) workPosition.getPostype_id().getPostype_id(), 1);
        Assert.assertEquals(workPosition.getAppointment_date().toString(), "2020-01-01 00:00:00.0");
        Assert.assertEquals(workPosition.getRetire_date().toString(), "2030-01-01 00:00:00.0");
        Assert.assertEquals((long) workPosition.getDepartment_id().getDepartment_id(), 1);
    }
    @Test(dependsOnMethods = "updateWorkPosition")
    public void deleteWorkPosition() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        WorkPosition workPosition = workPositionDAO.getByID(test_position_id);
        Assert.assertNotNull(workPosition);
        workPositionDAO.delete(workPosition);

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertNull(workPositionDAO.getByID(test_position_id));
        transaction.commit();
    }
}
