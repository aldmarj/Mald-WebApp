package website.service.auth;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import website.utils.BusinessTagUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * AccessVoter that denies access to resources that are part of a different business than the current user.
 *
 * @author Matt
 */
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
    public int vote(final Authentication authentication, final FilterInvocation filter,
                    final Collection<ConfigAttribute> attributes)
    {
        final String businessTag = BusinessTagUtils.getBusinessTag(filter.getRequestUrl());
        if (authentication instanceof UsernamePasswordAuthenticationToken)
        {
            if (businessTag.equals(authentication.getDetails()) || exemptBusinesses.contains(businessTag))
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
