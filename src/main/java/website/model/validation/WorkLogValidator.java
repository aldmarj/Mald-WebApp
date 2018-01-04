package website.model.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import website.model.WorkLog;

@Component
public class WorkLogValidator implements Validator {

  private static final long ONE_MINUTE_IN_MILLISECONDS = 60000;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(WorkLog.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    WorkLog workLog = (WorkLog) target;

    if (endTimeIsBefore(workLog)) {
      // finds 'endTime.before' in message_{locale}.properties
      errors.rejectValue("endTime", "endTime.before");
      return;
    }

    if (durationIsZero(workLog)) {
      errors.rejectValue("endTime", "endTime.zeroDuration");
    }

  }

  private boolean endTimeIsBefore(WorkLog workLog) {
    return workLog.getEndTime().before(workLog.getStartTime());
  }

  private boolean durationIsZero(WorkLog workLog) {
    long start = workLog.getStartTime().getTime();
    long end = workLog.getEndTime().getTime();

    long differenceMinutes = (end - start) / ONE_MINUTE_IN_MILLISECONDS;

    return differenceMinutes == 0;
  }

}
