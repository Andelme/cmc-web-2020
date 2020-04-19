package spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.entity.PositionType;

@Component
public class PositionTypeValidator implements Validator {

    @Override
    public boolean supports(Class<?> c) {
        return c == PositionType.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PositionType positionType = (PositionType) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "postype_name", "NotEmpty.positionType"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "responsibilities", "NotEmpty.positionType"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "salary", "NotEmpty.positionType"
        );
    }
}
