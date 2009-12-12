package org.hephaestus.fixedformat.impl;
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

import java.util.Calendar;
import java.util.Date;

import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.fixedformat.impl.DateTimeCodec;

import junit.framework.TestCase;

public class TestDateTimeCodec extends TestCase
{
    private ValueCodec codec;
    private Date testDate;
    private String testDateFormat;

    protected void setUp() throws Exception
    {
        codec = new DateTimeCodec();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2006);
        cal.set(Calendar.MONTH, Calendar.AUGUST);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 33);
        cal.set(Calendar.SECOND, 21);
        cal.set(Calendar.MILLISECOND, 1);
        
        testDate = cal.getTime();
        
        testDateFormat = "yyyyMMdd";
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testEncodeValueNull()
    {
        try
        {
            codec.encodeValue(null, 4);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testEncodeValueEmptyString()
    {
        ((DateTimeCodec) codec).setFormatString(testDateFormat);
        try
        {
            codec.encodeValue("", 4);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testDecodeValueNull()
    {
        try
        {
            codec.decodeValue(null);
            fail("Should have thrown exception");
        }
        catch (Exception e)
        {
            
        }
    }
    
    public void testDecodeValueEmpty()
    {
        ((DateTimeCodec) codec).setFormatString(testDateFormat);
        
        try
        {
            codec.decodeValue("");
            fail("Should have thrown exception");
        }
        catch (Exception e)
        {
            
        }
    }
    
    public void testDecodeValueInvalid()
    {
        ((DateTimeCodec) codec).setFormatString(testDateFormat);
        
        try
        {
            codec.decodeValue("abcdefghij");
            fail("Should have thrown exception");
        }
        catch (Exception e)
        {
            
        }
    }
    
    public void testEncodeValueDate()
    {
        ((DateTimeCodec) codec).setFormatString(testDateFormat);
        String encodedValue = codec.encodeValue(testDate, 8);
        
        assertNotNull(encodedValue);
        assertEquals("20060831", encodedValue);
    }

    public void testEncodeValueString()
    {
        ((DateTimeCodec) codec).setFormatString(testDateFormat);
        String encodedValue = codec.encodeValue("20060831", 8);
        
        assertNotNull(encodedValue);
        assertEquals("20060831", encodedValue);
    }

    public void testDecodeValue()
    {
        ((DateTimeCodec) codec).setFormatString(testDateFormat);
        
        Date decodedDate = (Date) codec.decodeValue("20060831");
        
        assertNotNull(decodedDate);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(decodedDate);
        
        assertEquals(2006, cal.get(Calendar.YEAR));
        assertEquals(Calendar.AUGUST, cal.get(Calendar.MONTH));
        assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MILLISECOND));

    }
    
}
