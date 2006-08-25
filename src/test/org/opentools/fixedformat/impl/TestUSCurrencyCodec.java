package org.opentools.fixedformat.impl;

import java.math.BigDecimal;

import org.opentools.fixedformat.ValueCodec;

import junit.framework.TestCase;

public class TestUSCurrencyCodec extends TestCase
{
    private ValueCodec codec;
    
    protected void setUp() throws Exception
    {
        codec = new USCurrencyCodec();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testEncodeValueNull()
    {
        try
        {
            codec.encodeValue(null, 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }

    public void testEncodeValueWrongType()
    {
        try
        {
            codec.encodeValue("", 0);
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
            codec.encodeValue(new BigDecimal("23.1"), 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
    
    public void testEncodeValueShort()
    {
        codec.setPaddable(true);
        codec.setPadCharacter('0');
        codec.setJustification(ValueCodec.RIGHT_JUSTIFIED);
        assertEquals("002310", codec.encodeValue(new BigDecimal("23.1"), 6));
    }
    
    public void testEncodeValueEqual()
    {
        assertEquals("232", codec.encodeValue(new BigDecimal("2.32"), 3));
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
        catch (NumberFormatException e)
        {
            
        }
    }
    
    public void testDecodeValueInvalid()
    {
        try
        {
            codec.decodeValue("ab23d");
            fail("Should have thrown exception");
        }
        catch (NumberFormatException e)
        {
            
        }
    }
    
    public void testDecodeValueNoPadding()
    {
        BigDecimal num = (BigDecimal) codec.decodeValue("232");
        assertEquals(232L, num.longValue());
    }

    public void testDecodeValueZeroPadding()
    {
        BigDecimal num = (BigDecimal) codec.decodeValue("000232");
        assertEquals(232L, num.longValue());
    }

}
