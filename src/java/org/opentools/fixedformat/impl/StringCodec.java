package org.opentools.fixedformat.impl;

import org.opentools.fixedformat.ValueCodec;
import org.opentools.textutils.TextUtils;

public class StringCodec implements ValueCodec
{

    public String encodeValue(Object value, int length)
    {
        return TextUtils.leftPadString(((value == null) ? null : value.toString()), length);
    }

    public Object decodeValue(String value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Value must not be null");
        }
        
        return value.trim();
    }

}
