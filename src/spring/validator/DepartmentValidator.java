package spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.entity.Department;
import spring.service.DepartmentService;

@Component
public class DepartmentValidator implements Validator {
    @Autowired
    private DepartmentService departmentService;

    @Override
    public boolean supports(Class<?> c) {
        return c == Department.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "department_name", "NotEmpty.department"
        );
        if (departmentService.getByDepartmentName(department.getDepartment_name()) != null) {
            errors.rejectValue("department_name", "Exists.department.department_name");
        }
    }
}