package test;

import dao.DepartmentDAO;
import entity.Department;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DepartmentTest extends GenericTest {
    private DepartmentDAO departmentDAO;
    private Long test_department_id;

    @BeforeClass(dependsOnMethods = "setupSession")
    protected void setDAO() {
        departmentDAO = new DepartmentDAO();
        departmentDAO.setSessionFactory(testSessionFactory);
    }
    @Test
    public void getDepartment() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Department department = departmentDAO.getByID((long) 1);
        Assert.assertNotNull(department);
        Assert.assertEquals((long) department.getDepartment_id(), 1);
        Assert.assertEquals(department.getDepartment_name(), "Andelme Headquarters");
        Assert.assertNull(department.getHead_department());
        Assert.assertEquals(departmentDAO.getByID((long) 2).getHead_department(), department);

        Assert.assertNull(departmentDAO.getByID((long) 9));

        Assert.assertEquals(departmentDAO.getByDepartmentName("Andelme Headquarters"), department);
        Assert.assertNull(departmentDAO.getByDepartmentName("Test"));

        List<Department> departments = departmentDAO.getByHeadDepartment(department.getDepartment_id());
        List<Department> testDepartments = Arrays.asList(
                departmentDAO.getByID((long) 2),
                departmentDAO.getByID((long) 3),
                departmentDAO.getByID((long) 4),
                departmentDAO.getByID((long) 5),
                departmentDAO.getByID((long) 6)
        );
        Assert.assertEquals(departments.size(), testDepartments.size());
        for (Department e : departments) {
            Assert.assertTrue(testDepartments.contains(e));
        }

        Assert.assertEquals(departmentDAO.getByHeadDepartment((long) 9).size(), 0);

        departments = departmentDAO.getAll();
        testDepartments = Arrays.asList(
                departmentDAO.getByID((long) 1),
                departmentDAO.getByID((long) 2),
                departmentDAO.getByID((long) 3),
                departmentDAO.getByID((long) 4),
                departmentDAO.getByID((long) 5),
                departmentDAO.getByID((long) 6),
                departmentDAO.getByID((long) 7),
                departmentDAO.getByID((long) 8)
        );
        Assert.assertEquals(departments.size(), testDepartments.size());
        for (Department e : departments) {
            Assert.assertTrue(testDepartments.contains(e));
        }
        transaction.commit();
    }
    @Test(dependsOnMethods = "getDepartment")
    public void addDepartment() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertNull(departmentDAO.getByDepartmentName("Test"));

        Department department = new Department();
        department.setDepartment_name("Test");
        department.setHead_department(departmentDAO.getByID((long) 1));
        departmentDAO.save(department);
        test_department_id = department.getDepartment_id();

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertEquals(departmentDAO.getByID(test_department_id), department);
        transaction.commit();
    }
    @Test(dependsOnMethods = "addDepartment")
    public void updateDepartment() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        Department department = departmentDAO.getByID(test_department_id);
        Assert.assertNotNull(department);
        department.setDepartment_name("Updated test");
        departmentDAO.update(department);

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        department = departmentDAO.getByID(test_department_id);
        Assert.assertNotNull(department);
        Assert.assertEquals(department.getDepartment_id(), test_department_id);
        Assert.assertEquals(department.getDepartment_name(), "Updated test");
        Assert.assertEquals(department.getHead_department(), departmentDAO.getByID((long) 1));
        transaction.commit();
    }
    @Test(dependsOnMethods = "updateDepartment")
    public void deleteDepartment() {
        Transaction transaction = testSessionFactory.getCurrentSession().beginTransaction();

        Department department = departmentDAO.getByID(test_department_id);
        Assert.assertNotNull(department);
        departmentDAO.delete(department);

        transaction.commit();

        transaction = testSessionFactory.getCurrentSession().beginTransaction();
        Assert.assertNull(departmentDAO.getByID(test_department_id));
        transaction.commit();
    }
}