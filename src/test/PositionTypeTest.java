package test;

import dao.PositionTypeDAO;
import entity.PositionType;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PositionTypeTest extends GenericTest {
    private PositionTypeDAO positionTypeDAO;
    private long test_postype_id;

    @BeforeClass(dependsOnMethods = "setupSession")
    protected void setDAO() {
        positionTypeDAO = new PositionTypeDAO();
        positionTypeDAO.setSession(testSession);
    }
    @Test
    public void getPositionType() {
        PositionType positionType = positionTypeDAO.getByID((long) 1);
        Assert.assertNotNull(positionType);
        Assert.assertEquals((long) positionType.getPostype_id(), 1);
        Assert.assertEquals(positionType.getPostype_name(), "Начальник отдела кадров");
        Assert.assertEquals(positionType.getResponsibilities(), "" +
                "Возглавляет работу по набору и распределению работников по рабочим местам, " +
                "Проводит анализ кадровой работы в компании, " +
                "разрабатывает пути её улучшения, " +
                "Руководит работниками отдела, " +
                "Разрабатывает мероприятия по укреплению трудовой дисчиплины, " +
                "снижению текучести кадров");
        Assert.assertEquals(positionType.getSalary(), (Integer) 160000);

        Assert.assertNull(positionTypeDAO.getByID((long) 11));

        List<PositionType> posTypes = positionTypeDAO.getAll();
        List<PositionType> testPosTypes = Arrays.asList(
                positionTypeDAO.getByID((long) 1),
                positionTypeDAO.getByID((long) 2),
                positionTypeDAO.getByID((long) 3),
                positionTypeDAO.getByID((long) 4),
                positionTypeDAO.getByID((long) 5),
                positionTypeDAO.getByID((long) 6),
                positionTypeDAO.getByID((long) 7),
                positionTypeDAO.getByID((long) 8),
                positionTypeDAO.getByID((long) 9),
                positionTypeDAO.getByID((long) 10)
        );
        Assert.assertEquals(posTypes.size(), testPosTypes.size());
        for (PositionType e : posTypes) {
            Assert.assertTrue(testPosTypes.contains(e));
        }
    }
    @Test(dependsOnMethods = "getPositionType")
    public void addPositionType() {
        Transaction transaction = testSession.beginTransaction();

        PositionType positionType = new PositionType();
        positionType.setPostype_name("Test Name");
        positionType.setResponsibilities("Test");
        positionType.setSalary(10000);
        positionTypeDAO.save(positionType);
        test_postype_id = positionType.getPostype_id();

        transaction.commit();

        Assert.assertEquals(positionTypeDAO.getByID(test_postype_id), positionType);
    }
    @Test(dependsOnMethods = "addPositionType")
    public void updatePositionType() {
        Transaction transaction = testSession.beginTransaction();

        PositionType positionType = positionTypeDAO.getByID(test_postype_id);
        Assert.assertNotNull(positionType);
        positionType.setPostype_name("Updated Test Name");
        positionTypeDAO.update(positionType);

        transaction.commit();

        positionType = positionTypeDAO.getByID(test_postype_id);
        Assert.assertNotNull(positionType);
        Assert.assertEquals((long) positionType.getPostype_id(), test_postype_id);
        Assert.assertEquals(positionType.getPostype_name(), "Updated Test Name");
        Assert.assertEquals(positionType.getResponsibilities(), "Test");
        Assert.assertEquals(positionType.getSalary(), (Integer) 10000);
    }
    @Test(dependsOnMethods = "updatePositionType")
    public void deletePositionType() {
        Transaction transaction = testSession.beginTransaction();

        PositionType positionType = positionTypeDAO.getByID(test_postype_id);
        Assert.assertNotNull(positionType);
        positionTypeDAO.delete(positionType);

        transaction.commit();

        Assert.assertNull(positionTypeDAO.getByID(test_postype_id));
    }
}
