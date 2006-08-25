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
    
    public void testEncodeValueNullPaddable()
    {
        codec.setPaddable(true);
        assertEquals("    ", codec.encodeValue(null, 4));
    }
    
    public void testEncodeValueNullNotPaddable()
    {
        try
        {
            assertEquals("    ", codec.encodeValue(null, 4));
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testEncodeValueEmptyStringPaddable()
    {
        codec.setPaddable(true);
        assertEquals("    ", codec.encodeValue("", 4));
    }
    
    public void testEncodeValueEmptyStringNotPaddable()
    {
        assertEquals("", codec.encodeValue("", 4));
    }
    
    public void testEncodeValueShortStringNotPadded()
    {
        assertEquals("23", codec.encodeValue(new Long(23L), 4));
    }
    
    public void testEncodeValueShortStringPadded()
    {
        codec.setPaddable(true);
        assertEquals("23  ", codec.encodeValue(new Long(23L), 4));
    }
    
    public void testEncodeValueEqualString()
    {
        assertEquals("abcd", codec.encodeValue("abcd", 4));
    }
    
    public void testEncodeValueLongStringTrucated()
    {
        codec.setTruncatable(true);
        assertEquals("abcd", codec.encodeValue("abcde", 4));
    }
    
    public void testEncodeValueLongStringNotTrucated()
    {
        try
        {
            codec.encodeValue("abcde", 4);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
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
    
    public void testDecodeValueAllSpacesStringNotPaddable()
    {
        assertEquals("    ", codec.decodeValue("    "));
    }
    
    public void testDecodeValueAllSpacesStringPaddable()
    {
        codec.setPaddable(true);
        assertEquals("", codec.decodeValue("    "));
    }
    
    public void testDecodeValueLeftPaddedNoPadding()
    {
        assertEquals("  TestString", codec.decodeValue("  TestString"));
    }
    
    public void testDecodeValueLeftPaddedPadding()
    {
        codec.setPaddable(true);
        assertEquals("TestString", codec.decodeValue("TestString  "));
    }
    
    public void testDecodeValueRightPadded()
    {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        assertEquals("TestString", codec.decodeValue("  TestString"));
    }
    
    public void testDecodeValueRightLeftPadded()
    {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.CENTER_JUSTIFIED);
        assertEquals("TestString", codec.decodeValue("  TestString  "));
    }
    
    public void testEncodeValueCenterPadding()
    {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.CENTER_JUSTIFIED);
        codec.setPadCharacter('*');
        
        assertEquals("TestString", codec.encodeValue("TestString", 10));
        assertEquals("TestString*", codec.encodeValue("TestString", 11));
        assertEquals("*TestString*", codec.encodeValue("TestString", 12));
    }
    
    public void testDecodeValueCenterPaddingWStrangePad()
    {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.CENTER_JUSTIFIED);
        codec.setPadCharacter('*');
        
        assertEquals("TestString", codec.decodeValue("TestString"));
        assertEquals("TestString", codec.decodeValue("TestString*"));
        assertEquals("TestString", codec.decodeValue("*TestString*"));
        
    }

}
