package org.opentools.textutils;

/**
 * Utility class for performing a variety of operations
 * against string data.
 * 
 * @author djs
 *
 */
public class TextUtils
{
    /**
     * Private constructor; cannot instantiate this class.
     *
     */
    private TextUtils()
    {
        
    }
    
    /**
     * Tests the specified string to determine if it null or the
     * empty string. This method will trim a non-null string to
     * verify emptiness.
     * 
     * @param testString the string to test
     * @return true if the string is null or empty.
     */
    public static boolean isBlank(String testString)
    {
        boolean blank = true;
        
        if (testString != null)
        {
            blank = testString.trim().length() == 0;
        }
        
        return blank;
    }
    
}
