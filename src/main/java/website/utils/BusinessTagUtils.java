package website.utils;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BusinessTagUtils
{
    private static final Pattern BUSINESS_TAG_PATTERN = Pattern.compile("^\\/([^\\/]*)\\/.*");

    private BusinessTagUtils()
    {
        throw new AssertionError("utility class should not be instantiated");
    }

    public static String getBusinessTag(final CharSequence url)
    {
        final Matcher matcher = BUSINESS_TAG_PATTERN.matcher(url);
        matcher.find();
        return matcher.group(1);
    }

    public static String getBusinessTag(final URI uri)
    {
        return getBusinessTag(uri.toString());
    }
}
