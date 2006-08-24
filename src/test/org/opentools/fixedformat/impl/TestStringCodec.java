package org.opentools.fixedformat.impl;

import org.opentools.fixedformat.ValueCodec;

import junit.framework.TestCase;

public class TestStringCodec extends TestCase
{
    private ValueCodec codec;
    
    protected void setUp() throws Exception
    {
        codec = new StringCodec();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testEncodeValueNull()
    {
        assertEquals("    ", codec.encodeValue(null, 4));
    }
    
    public void testEncodeValueEmptyString()
    {
        assertEquals("    ", codec.encodeValue("", 4));
    }
    
    public void testEncodeValueShortString()
    {
        assertEquals("  23", codec.encodeValue(new Long(23L), 4));
    }
    
    public void testEncodeValueEqualString()
    {
        assertEquals("abcd", codec.encodeValue("abcd", 4));
    }
    
    public void testEncodeValueLongString()
    {
        assertEquals("bcde", codec.encodeValue("abcde", 4));
    }
    
    public void testDecodeValueNullString()
    {
        try
        {
            codec.decodeValue(null);
            fail("Should have thrown an exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testDecodeValueEmptyString()
    {
        assertEquals("", codec.decodeValue(""));
    }
    
    public void testDecodeValueAllSpacesString()
    {
        assertEquals("", codec.decodeValue("    "));
    }
    
    public void testDecodeValueLeftPadded()
    {
        assertEquals("TestString", codec.decodeValue("  TestString"));
    }
    
    public void testDecodeValueRightPadded()
    {
        assertEquals("TestString", codec.decodeValue("TestString  "));
    }
    
    public void testDecodeValueRightLeftPadded()
    {
        assertEquals("TestString", codec.decodeValue("  TestString  "));
    }

}
