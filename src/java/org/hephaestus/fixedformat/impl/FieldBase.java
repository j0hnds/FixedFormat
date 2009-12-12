package org.hephaestus.fixedformat.impl;

import org.hephaestus.fixedformat.Field;
import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.textutils.TextUtils;

/**
 * Base implementation of fixed format field.
 * 
 * @author djs
 *
 */
public class FieldBase implements Field
{
    private int length;
    private String name;
    private ValueCodec valueCodec;
    private String description;
    private boolean required;
    
    public final void setLength(int length)
    {
        this.length = length;

    }

    public final int getLength()
    {
        return length;
    }

    public final void setName(String name)
    {
        this.name = name;

    }

    public final String getName()
    {
        return name;
    }

    public boolean isRequired()
    {
        return required;
    }

    public void setRequired(boolean required)
    {
        this.required = required;
    }

    public final void setValueCodec(ValueCodec codec)
    {
        this.valueCodec = codec;

    }

    public final ValueCodec getValueCodec()
    {
        return valueCodec;
    }

    public final void setDescription(String description)
    {
        this.description = description;

    }

    public final String getDescription()
    {
        return description;
    }

    public final String extractRawValue(String record, int position)
    {
        String value = null;
        
        if (TextUtils.isBlank(record))
        {
            throw new IllegalArgumentException("Cannot extract field from null or empty record");
        }
        
        if (position < 0)
        {
            throw new IllegalArgumentException("position cannot be less than 0");
        }
        
        if (position >= record.length())
        {
            throw new IllegalArgumentException("Starting position is beyond the end of the record");
        }
        
        int endIndex = position + length;
        if (endIndex > record.length())
        {
            throw new IllegalArgumentException("Record not long enough for field");
        }
        
        value = record.substring(position, endIndex);
        
        return value;
    }

    public final Object extractTypedValue(String record, int position)
    {
        if (valueCodec == null)
        {
            throw new IllegalStateException("No value codec configured for field");
        }
        
        String value = extractRawValue(record, position);
        
        return valueCodec.decodeValue(value);
    }

    public final String formatValue(Object value)
    {
        if (valueCodec == null)
        {
            throw new IllegalStateException("No value codec configured for field");
        }
        
        String encodedValue = valueCodec.encodeValue(value, length);
        
        if (required && valueCodec.isEmpty(encodedValue))
        {
            throw new IllegalArgumentException("Required field must have non-empty value");
        }
        
        return encodedValue;
    }

}
