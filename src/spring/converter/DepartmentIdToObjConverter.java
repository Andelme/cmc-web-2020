package spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.entity.Department;
import spring.service.DepartmentService;

@Component
public class DepartmentIdToObjConverter implements Converter<Object, Department> {

    @Autowired
    DepartmentService departmentService;

    public Department convert(Object element) {
        if (element.toString().isEmpty()) return null;
        Long id = Long.parseLong(element.toString());
        return departmentService.getByID(id);
    }
}