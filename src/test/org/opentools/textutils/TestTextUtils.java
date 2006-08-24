package org.opentools.textutils;

import junit.framework.TestCase;

public class TestTextUtils extends TestCase
{

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testIsBlankNullString()
    {
        assertTrue(TextUtils.isBlank(null));
    }
    
    public void testIsBlankZeroLengthString()
    {
        assertTrue(TextUtils.isBlank(""));
    }
    
    public void testIsBlankSpaceOnlyString()
    {
        assertTrue(TextUtils.isBlank("     "));
    }
    
    public void testIsBlankNonBlankString()
    {
        assertFalse(TextUtils.isBlank("  TestString  "));
    }
    
    public void testLeftPadStringNullString()
    {
        assertEquals("    ", TextUtils.leftPadString(null, 4, ' '));
        assertEquals("    ", TextUtils.leftPadString(null, 4));
    }
    
    public void testLeftPadStringEmptyString()
    {
        assertEquals("****", TextUtils.leftPadString("", 4, '*'));
    }
    
    public void testLeftPadStringShortString()
    {
        assertEquals("**aa", TextUtils.leftPadString("aa", 4, '*'));
    }
    
    public void testLeftPadStringEqualString()
    {
        assertEquals("abcd", TextUtils.leftPadString("abcd", 4));
    }
    
    public void testLeftPadStringLongString()
    {
        assertEquals("bcde", TextUtils.leftPadString("abcde", 4));
    }

}
