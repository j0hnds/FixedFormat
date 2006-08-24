package org.opentools.textutils;

import junit.framework.TestCase;

public class TestNumberUtils extends TestCase
{

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testZeroPadLongZero()
    {
        assertEquals("00000", NumberUtils.zeroPadLong(0L, 5));
    }
    
    public void testZeroPadLongShortNumber()
    {
        assertEquals("00023", NumberUtils.zeroPadLong(23L, 5));
    }
    
    public void testZeroPadLongEqualNumber()
    {
        assertEquals("12345", NumberUtils.zeroPadLong(12345L, 5));
    }
    
    public void testZeroPadLongBigNumber()
    {
        try 
        {
            NumberUtils.zeroPadLong(1234567890L, 5);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

}
