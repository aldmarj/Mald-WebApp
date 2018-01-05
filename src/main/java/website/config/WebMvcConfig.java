package website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  public static final String MOMENTJS_DATE_FORMAT = "DD-MM-YYYY HH:mm";
  public static final String SIMPLE_DATE_FORMAT = "dd-MM-yy HH:mm";

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new DateFormatter(SIMPLE_DATE_FORMAT));
  }

}
