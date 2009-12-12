package org.hephaestus.fixedformat.impl;

import java.util.Date;

import org.hephaestus.textutils.DateTimeUtils;

public class DateTimeCodec extends ValueCodecBase
{
    private String formatString;
    
    protected Object stringToObject(String value)
    {
        if (formatString == null)
        {
            throw new IllegalStateException("No format string configured for codec");
        }
        
        return DateTimeUtils.parseDate(value, formatString);
    }

    protected String objectToString(Object value)
    {
        String formattedDateTime = null;
        
        if (value == null)
        {
            throw new IllegalArgumentException("Value must be non-null");
        }
        
        if (formatString == null)
        {
            throw new IllegalStateException("No format string configured for codec");
        }
        
        if (value instanceof Date)
        {
            formattedDateTime = DateTimeUtils.formatDate((Date) value, formatString);
        }
        else if (value instanceof String)
        {
            // Make sure the string passed in is parseable by the format
            Date parsedDate = DateTimeUtils.parseDate((String) value, formatString);
            
            // If no exception thrown, we should be able to format the date.
            formattedDateTime = DateTimeUtils.formatDate(parsedDate, formatString);
        }
        else
        {
            throw new IllegalArgumentException("Unsupported value type for DateTimeCodec");
        }
        
        return formattedDateTime;
    }

    public String getFormatString()
    {
        return formatString;
    }

    public void setFormatString(String formatString)
    {
        this.formatString = formatString;
    }

}
