package org.opentools.fixedformat.impl;

import java.math.BigDecimal;

public final class USCurrencyCodec extends ValueCodecBase
{

    
    protected String objectToString(Object value)
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
        
        encodedValue = cValue.setScale(2, BigDecimal.ROUND_HALF_EVEN).abs().unscaledValue().toString();
        
        return encodedValue;
    }

    protected Object stringToObject(String value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Must specify non-null value");
        }
        
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}
