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
    
    public static String leftPadString(String originalValue, int length, char padCharacter)
    {
        String encodedValue = null;
        
        if (originalValue == null)
        {
            // We will assume that null indicates an empty value
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<length; i++)
            {
                sb.append(padCharacter);
            }
            
            encodedValue = sb.toString();
        }
        else 
        {
            int len = originalValue.length();
            if (len > length)
            {
                encodedValue = originalValue.substring(len - length, len);
            }
            else
            {
                int numPads = length - len;
                StringBuffer sb = new StringBuffer();
                for (int i=0; i<numPads; i++)
                {
                    sb.append(padCharacter);
                }
                sb.append(originalValue);
                
                encodedValue = sb.toString();
            }
        }
        
        return encodedValue;
        
    }
    
    public static String leftPadString(String originalValue, int length)
    {
        return leftPadString(originalValue, length, ' ');
    }
    
}
