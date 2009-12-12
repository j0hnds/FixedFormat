package org.hephaestus.fixedformat.impl;

import org.hephaestus.fixedformat.Field;
import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.fixedformat.impl.FieldBase;
import org.hephaestus.fixedformat.impl.StringCodec;

import junit.framework.TestCase;

public class TestFieldBase extends TestCase
{
    private Field field;
    
    protected void setUp() throws Exception
    {
        ValueCodec codec = new StringCodec();
        codec.setPaddable(true);
        codec.setPadCharacter(' ');
        codec.setJustification(ValueCodec.LEFT_JUSTIFIED);
        
        field = new FieldBase();
        field.setDescription("FieldDescription");
        field.setLength(4);
        field.setName("name");
        field.setValueCodec(codec);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testProperties()
    {
        assertEquals("FieldDescription", field.getDescription());
        assertEquals(4, field.getLength());
        assertEquals("name", field.getName());
        assertNotNull(field.getValueCodec());
    }
    
    public void testExtractTypedValueNullRecord()
    {
        try
        {
            field.extractTypedValue(null, 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }
    
    public void testExtractTypedValueEmptyRecord()
    {
        try
        {
            field.extractTypedValue("", 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }
    
    public void testExtractTypedValuePositionNegative()
    {
        try
        {
            field.extractTypedValue("   abcdefg", -1);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public void testExtractTypedValuePositionOutOfRange()
    {
        try
        {
            field.extractTypedValue("   abcdefg", 12);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public void testExtractTypedValuePositionLengthOutOfRange()
    {
        try
        {
            field.extractTypedValue("   abcdefg", 8);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public void testExtractTypedValuePosition0()
    {
        assertEquals("a", field.extractTypedValue("a   bcdefg", 0));
    }

    public void testExtractTypedValuePositionEnd()
    {
        assertEquals("defg", field.extractRawValue("   abcdefg", 6));
    }
    
    public void testExtractRawValueNullRecord()
    {
        try
        {
            field.extractRawValue(null, 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }
    
    public void testExtractRawValueEmptyRecord()
    {
        try
        {
            field.extractRawValue("", 0);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }
    
    public void testExtractRawValuePositionNegative()
    {
        try
        {
            field.extractRawValue("   abcdefg", -1);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public void testExtractRawValuePositionOutOfRange()
    {
        try
        {
            field.extractRawValue("   abcdefg", 12);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public void testExtractRawValuePositionLengthOutOfRange()
    {
        try
        {
            field.extractRawValue("   abcdefg", 8);
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    public void testExtractRawValuePosition0()
    {
        assertEquals("   a", field.extractRawValue("   abcdefg", 0));
    }

    public void testExtractRawValuePositionEnd()
    {
        assertEquals("defg", field.extractRawValue("   abcdefg", 6));
    }
    
    public void testFormatValueNullValue()
    {
        assertEquals("    ", field.formatValue(null));
    }

    public void testFormatValueEmptyValue()
    {
        assertEquals("    ", field.formatValue(""));
    }

    public void testFormatValueShortValue()
    {
        assertEquals("aa  ", field.formatValue("aa"));
    }

    public void testFormatValueEqualValue()
    {
        assertEquals("abcd", field.formatValue("abcd"));
    }

    public void testFormatValueLongValue()
    {
        try
        {
            field.formatValue("abcde");
            fail("Should have thrown exception");
        }
        catch (IllegalArgumentException e)
        {
            
        }
    }
}
