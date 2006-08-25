package org.opentools.fixedformat.impl;

import org.opentools.fixedformat.ValueCodec;

import junit.framework.TestCase;

public class TestLongCodec extends TestCase
{
    private ValueCodec codec;
    
    protected void setUp() throws Exception
    {
        codec = new LongCodec();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testEncodeValueNullValue()
    {
        try 
        {
            codec.encodeValue(null, 3);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testEncodeValueWrongTypeValue()
    {
        try 
        {
            codec.encodeValue("a3", 3);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testEncodeValueTooLong()
    {
        try
        {
            codec.encodeValue(new Long(3), 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testEncodeValueEqual()
    {
        assertEquals("123", codec.encodeValue(new Long(123), 3));
    }
    
    public void testEncodeValueShort()
    {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        codec.setPadCharacter('0');
        assertEquals("001", codec.encodeValue(new Short("1"), 3));
    }
    
    public void testEncodeValueInteger()
    {
        codec.setPaddable(true);
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        codec.setPadCharacter('0');
        assertEquals("001", codec.encodeValue(new Integer("1"), 3));
    }
    
    public void testDecodeValueNull()
    {
        try
        {
            codec.decodeValue(null);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testDecodeValueEmpty()
    {
        try
        {
            codec.decodeValue("");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testDecodeValueZeroPadded()
    {
        codec.setPadCharacter('0');
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        assertEquals(new Long(23), codec.decodeValue("0023"));
    }
    
    public void testDecodeValueNotPadded()
    {
        assertEquals(new Long(44), codec.decodeValue("44"));
    }
    
    public void testDecodeValueInvalidLong()
    {
        try
        {
            codec.decodeValue("ab23");
            fail("Should have thrown exception");
        }
        catch (NumberFormatException e)
        {
            
        }
    }

}
