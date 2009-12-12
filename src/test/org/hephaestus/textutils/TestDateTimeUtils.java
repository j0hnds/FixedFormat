package org.hephaestus.textutils;

import java.util.Calendar;
import java.util.Date;

import org.hephaestus.textutils.DateTimeUtils;

import junit.framework.TestCase;

public class TestDateTimeUtils extends TestCase
{
    private Date testDate;
    private String testDateFormat;
    private String testTimeFormat;
    
    protected void setUp() throws Exception
    {
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
        testTimeFormat = "HHmm";
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testParseDateNullDateString()
    {
        try
        {
            DateTimeUtils.parseDate(null, testDateFormat);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testParseDateEmptyDateString()
    {
        try
        {
            DateTimeUtils.parseDate("", testDateFormat);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testParseDateNullFormatString()
    {
        try
        {
            DateTimeUtils.parseDate("20060812", null);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testParseDateEmptyFormatString()
    {
        try
        {
            DateTimeUtils.parseDate("20060812", "");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testParseDateInvalidDateString()
    {
        try
        {
            DateTimeUtils.parseDate("abcdefg", testDateFormat);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testParseDateInvalidFormatString()
    {
        try
        {
            DateTimeUtils.parseDate("20060812", "qqqqrrss");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testFormatDateNullDate()
    {
        try
        {
            DateTimeUtils.formatDate(null, testDateFormat);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testFormatDateNullFormat()
    {
        try
        {
            DateTimeUtils.formatDate(testDate, null);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testFormatDateEmptyFormat()
    {
        try
        {
            DateTimeUtils.formatDate(testDate, "");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testParseDate()
    {
        Date dt = DateTimeUtils.parseDate("20060831", testDateFormat);
        
        assertNotNull(dt);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        
        assertEquals(2006, cal.get(Calendar.YEAR));
        assertEquals(Calendar.AUGUST, cal.get(Calendar.MONTH));
        assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MILLISECOND));
    }

    public void testParseTime()
    {
        Date dt = DateTimeUtils.parseDate("2333", testTimeFormat);
        
        assertNotNull(dt);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        
        assertEquals(1970, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(33, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MILLISECOND));
    }
    
    public void testFormatDate()
    {
        String formattedDate = DateTimeUtils.formatDate(testDate, testDateFormat);
        assertNotNull(formattedDate);
        
        assertEquals("20060831", formattedDate);
    }

    public void testFormatTime()
    {
        String formattedTime = DateTimeUtils.formatDate(testDate, testTimeFormat);
        assertNotNull(formattedTime);
        
        assertEquals("2333", formattedTime);
    }
}
