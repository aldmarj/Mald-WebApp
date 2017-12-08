package website.service.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import website.utils.BusinessTagUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class BusinessAuthFailureHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final AuthenticationException exception) throws IOException, ServletException
    {
        final String businessTag = BusinessTagUtils.getBusinessTag(request.getRequestURI());
        request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        request.setAttribute("error", exception.getMessage()); //todo maybe dont print any old message to the user
        request.getRequestDispatcher("/" + businessTag +"/login")
                .forward(new HttpMethodRequestWrapper(request, "GET"), response);
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper
    {
        private final String method;

        public HttpMethodRequestWrapper(final HttpServletRequest request, final String method)
        {
            super(request);
            this.method = method.toUpperCase(Locale.ENGLISH);
        }

        @Override
        public String getMethod()
        {
            return this.method;
        }
    }
}
