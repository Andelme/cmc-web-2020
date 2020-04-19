package spring.service;

import spring.entity.Worker;
import spring.service.common.GenericService;

import java.util.List;

public interface WorkerService extends GenericService<Worker, Long> {
    List<Worker> getSpareWorkers();
}
