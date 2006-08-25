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
    
    public void testRightJustifyTextNullString()
    {
        assertEquals("    ", TextUtils.rightJustifyText(null, 4, ' '));
    }
    
    public void testRightJustifyTextEmptyString()
    {
        assertEquals("****", TextUtils.rightJustifyText("", 4, '*'));
    }
    
    public void testRightJustifyTextShortString()
    {
        assertEquals("**aa", TextUtils.rightJustifyText("aa", 4, '*'));
    }
    
    public void testRightJustifyTextEqualString()
    {
        assertEquals("abcd", TextUtils.rightJustifyText("abcd", 4, ' '));
    }
    
    public void testRightJustifyTextLongString()
    {
        assertEquals("abcde", TextUtils.rightJustifyText("abcde", 4, ' '));
    }

}
