package website.service.auth;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import website.utils.BusinessTagUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, String>
{
    @Override
    public String buildDetails(final HttpServletRequest context)
    {
        return BusinessTagUtils.getBusinessTag(context.getRequestURI());
    }
}
