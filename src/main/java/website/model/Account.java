package website.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model for representing an account.
 * 
 * @author Lawrence
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Account
{
	/** The username of the account, unique to a businessTag **/
    private String userName;
    
    /** The id of the business the account belongs to **/
    private String businessTag;

    /** An email address for the account **/
    private String email;

    /**
     * CLASS CONSTRUCTOR
     */
    public Account()
    {
        this.userName = null;
        this.businessTag = null;
        this.email = null;
    }

    /**
     * CLASS CONSTRUCTOR
     * 
     * @param username
     * @param businessTag
     * @param email
     */
    public Account(final String username,
                   final String businessTag, final String email)
    {
        this.userName = username;
        this.businessTag = businessTag;
        this.email = email;
    }
    
    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(final String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getBusinessTag()
    {
        return this.businessTag;
    }

    public void setBusinessTag(String businessTag)
    {
        this.businessTag = businessTag;
    }

    public String getName()
    {
        return this.userName;
    }

    @Override
    public String toString()
    {
        return this.userName;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof Account))
        {
            return false;
        }
        final Account otherAcc = (Account) obj;
        return this.userName.equals(otherAcc.userName)
                && this.email.equals(otherAcc.email);

    }
    
	/**
	 * Check to see if the account is valid.
	 * 
	 * @return true if the account is valid.
	 */
    public boolean isValid()
    {
    	return (!this.getBusinessTag().isEmpty()
    			&& !this.getUserName().isEmpty());
    }
}
