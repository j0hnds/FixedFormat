package org.opentools.textutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils
{
    private DateTimeUtils()
    {
        
    }
    
    public static final Date parseDate(String dateString, String formatString)
    {
        Date parsedDate = null;
        
        if (TextUtils.isBlank(dateString))
        {
            throw new IllegalArgumentException("Date to parse must be non-null, non-empty");
        }
        
        if (TextUtils.isBlank(formatString))
        {
            throw new IllegalArgumentException("Format string must be non-null, non-empty");
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        
        try
        {
            parsedDate = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("Cannot parse date string [" + dateString + "] with format string [" + formatString + "]", e);
        }
        
        return parsedDate;
    }
    
    public static final String formatDate(Date date, String formatString)
    {
        String formattedDate = null;
        
        if (date == null)
        {
            throw new IllegalArgumentException("Must specify a non-null date");
        }
        
        if (TextUtils.isBlank(formatString))
        {
            throw new IllegalArgumentException("Must specify a non-null, non-empty format string");
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        
        formattedDate = sdf.format(date);
        
        return formattedDate;
    }
}
