package spring.dao;

import spring.dao.common.GenericDAO;
import spring.entity.Worker;

import java.sql.Timestamp;
import java.util.List;

public interface WorkerDAO extends GenericDAO<Worker, Long> {

    List<Worker> getByDepartmentName(String department_name);

    List<Worker> getByPostypeName(String postype_name);

    List<Worker> getByHireDate(Timestamp startDate, Timestamp endDate);
}
