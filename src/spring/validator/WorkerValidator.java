package spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.entity.Worker;

import java.util.regex.Pattern;

@Component
public class WorkerValidator implements Validator {

    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^+7(\\d{3})\\d{3}-\\d{2}-\\d{2}$");

    @Override
    public boolean supports(Class<?> c) {
        return c == Worker.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Worker worker = (Worker) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "name", "NotEmpty.worker"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "address", "NotEmpty.worker"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "education_degree", "NotEmpty.worker"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "birth_date", "NotEmpty.worker"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "phone_number", "NotEmpty.worker"
        );
        if (!DATE_PATTERN.matcher(worker.getBirth_date().toString()).matches()) {
            errors.rejectValue("birth_date", "Pattern.worker.birth_date");
        }
        if (!PHONE_PATTERN.matcher(worker.getPhone_number().toString()).matches()) {
            errors.rejectValue("phone_number", "Pattern.worker.phone_number");
        }
    }
}
