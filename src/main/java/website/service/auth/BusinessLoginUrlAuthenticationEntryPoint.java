package website.service.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import website.utils.BusinessTagUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BusinessLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint
{
    /**
     * @param loginFormUrl
     */
    public BusinessLoginUrlAuthenticationEntryPoint(final String loginFormUrl)
    {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception)
    {
        final String businessTag = BusinessTagUtils.getBusinessTag(request.getRequestURI());
        if (businessTag == null || businessTag.isEmpty())
        {
            return this.getLoginFormUrl();
        }
        else
        {
            return "/" + businessTag + this.getLoginFormUrl();
        }
    }
}
