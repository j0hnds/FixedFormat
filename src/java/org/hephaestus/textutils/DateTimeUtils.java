package org.hephaestus.textutils;
/*
 * Copyright (c) 2009 Dave Sieh
 *
 * This file is part of FixedFormat.
 *
 * FixedFormat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FixedFormat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FixedFormat.  If not, see <http://www.gnu.org/licenses/>.
 */

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
