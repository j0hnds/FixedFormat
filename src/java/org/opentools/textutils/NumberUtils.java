package org.opentools.textutils;

public class NumberUtils
{
    private NumberUtils()
    {
        
    }
    
    public static String zeroPadNumber(String numValue, int length)
    {
        if (numValue.length() > length)
        {
            throw new IllegalArgumentException("Long value is too long to pad");
        }
                
        return TextUtils.leftPadString(numValue, length, '0');
    }
    
    public static String zeroPadLong(long longValue, int length)
    {
        String originalValue = Long.toString(longValue);
        
        if (originalValue.length() > length)
        {
            throw new IllegalArgumentException("Long value is too long to pad");
        }
                
        return TextUtils.leftPadString(originalValue, length, '0');
    }
}
