package website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  public static final String BOOTSTRAP_DATETIMEPICKER_FORMAT = "dd-mm-yyyy HH:ii:ss";

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new DateFormatter("dd-MM-yy H:m:s"));
  }
}
