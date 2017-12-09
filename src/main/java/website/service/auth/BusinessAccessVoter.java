package website.service.auth;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import website.utils.BusinessTagUtils;

import java.util.Arrays;
import java.util.Collection;

public class BusinessAccessVoter implements AccessDecisionVoter<FilterInvocation>
{
    private final Collection<String> exemptBusinesses;

    public BusinessAccessVoter(final String... exemptBusinesses)
    {
        this.exemptBusinesses = Arrays.asList(exemptBusinesses);
    }

    @Override
    public boolean supports(final ConfigAttribute attribute)
    {
        return true;
    }

    @Override
    public boolean supports(final Class<?> clazz)
    {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(final Authentication authentication, final FilterInvocation object, final Collection<ConfigAttribute> attributes)
    {
        final String businessTag = BusinessTagUtils.getBusinessTag(object.getRequestUrl());
        if (authentication instanceof UsernamePasswordAuthenticationToken && !exemptBusinesses.contains(businessTag))
        {
            if (businessTag.equals(authentication.getDetails()))
            {
                return ACCESS_GRANTED;
            }
            else
            {
                return ACCESS_DENIED;
            }
        }
        return ACCESS_ABSTAIN;
    }
}
