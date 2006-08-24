package org.opentools.fixedformat.impl;

import org.opentools.fixedformat.ValueCodec;
import org.opentools.textutils.NumberUtils;

public class LongCodec implements ValueCodec
{

    public String encodeValue(Object value, int length)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Input value must be non-null");
        }
        
        if (! ((value instanceof Long)
                || (value instanceof Integer)
                || (value instanceof Short)))
        {
            throw new IllegalArgumentException("Input Value must be Long");
        }
        
        return NumberUtils.zeroPadLong(((Number) value).longValue(), length);
    }

    public Object decodeValue(String value)
    {
        return new Long(value);
    }

}
