package org.hephaestus.fixedformat.impl;

public final class LongCodec extends ValueCodecBase
{
    protected Object stringToObject(String value)
    {
        return new Long(value);
    }

    protected String objectToString(Object value)
    {
        String stringValue = null;
        
        if (value instanceof String)
        {
            stringValue = new Long((String) value).toString();
        }
        else if (value instanceof Number)
        {
            stringValue = Long.toString(((Number) value).longValue());
        }
        else
        {
            throw new IllegalArgumentException("Value must be a Number or convertable to number");
        }

        return stringValue;
    }

}
