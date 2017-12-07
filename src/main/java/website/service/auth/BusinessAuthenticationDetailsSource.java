package website.service.auth;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, String>
{
    private static final Pattern BUSINESS_TAG_PATTERN = Pattern.compile("\\/([^\\/]*)\\/login");

    @Override
    public String buildDetails(final HttpServletRequest context)
    {
        final Matcher matcher = BUSINESS_TAG_PATTERN.matcher(context.getRequestURI());
        matcher.find();
        return matcher.group(1);
    }
}
