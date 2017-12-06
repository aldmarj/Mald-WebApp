package website.model.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import website.model.WorkLog;

@Component
public class WorkLogValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(WorkLog.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    WorkLog workLog = (WorkLog) target;

    if (workLog.getEndTime().before(workLog.getStartTime())) {
      // finds 'endTime.before' in message_{locale}.properties
      errors.rejectValue("endTime", "endTime.before");
    }
  }
}
