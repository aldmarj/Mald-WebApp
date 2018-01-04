package website.model.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import website.model.Client;

@Component
public class ClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Client.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clientName", "clientName.empty");
    }
}
