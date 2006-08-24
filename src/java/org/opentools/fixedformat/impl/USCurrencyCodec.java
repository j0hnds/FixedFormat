package org.opentools.fixedformat.impl;

import java.math.BigDecimal;

import org.opentools.fixedformat.ValueCodec;
import org.opentools.textutils.NumberUtils;

public class USCurrencyCodec implements ValueCodec
{

    public String encodeValue(Object value, int length)
    {
        String encodedValue = "";
        
        if (value == null)
        {
            throw new IllegalArgumentException("Must specify non-empty value");
        }
        
        if (! (value instanceof BigDecimal))
        {
            throw new IllegalArgumentException("Value must be BigDecimal");
        }
        
        BigDecimal cValue = (BigDecimal) value;
        
        encodedValue = NumberUtils.zeroPadNumber(cValue.setScale(2, BigDecimal.ROUND_HALF_EVEN).abs().unscaledValue().toString(), length);
        
        return encodedValue;
    }

    public Object decodeValue(String value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Must specify non-null value");
        }
        
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}
