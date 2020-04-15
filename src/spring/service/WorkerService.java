package spring.service;

import spring.entity.Worker;
import spring.service.common.GenericService;

import java.sql.Timestamp;
import java.util.List;

public interface WorkerService extends GenericService<Worker, Long> {

    List<Worker> getByDepartmentName(String department_name);

    List<Worker> getByPostypeName(String postype_name);

    List<Worker> getByHireDate(Timestamp startDate, Timestamp endDate);
}
