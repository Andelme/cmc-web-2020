package spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.entity.Worker;
import spring.service.WorkerService;

@Component
public class WorkerIdToObjConverter implements Converter<Object, Worker> {

    @Autowired
    WorkerService workerService;

    public Worker convert(Object element) {
        if (element.toString().isEmpty()) return null;
        Long id = Long.parseLong(element.toString());
        return workerService.getByID(id);
    }
}
