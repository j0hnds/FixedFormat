package org.opentools.fixedformat.impl;

import org.opentools.fixedformat.ValueCodec;
import org.opentools.textutils.TextUtils;

public abstract class ValueCodecBase implements ValueCodec
{
    private int justification = LEFT_JUSTIFIED;
    private boolean truncatable = false;
    private boolean paddable = false;
    private char padCharacter = ' ';
    private boolean required = false;
    
    
    public final Object decodeValue(String value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Value must be non-null");
        }
        
        String unpaddedValue = value;
        
        // Is the value paddable?
        if (paddable)
        {
            unpaddedValue = unpadValue(value);
        }

        return stringToObject(unpaddedValue);
    }
    
    protected abstract Object stringToObject(String value);
    
    protected String objectToString(Object value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Must specifie non-null value");
        }
        
        return value.toString();
    }
    
    public final String encodeValue(Object value, int length)
    {
        String convertedValue = null;
        
        if (value != null)
        {
            convertedValue = objectToString(value);
        }
        else if (paddable)
        {
            convertedValue = "";
        }
        else
        {
            throw new IllegalArgumentException("Unpaddable fields must be non-null");
        }
        
        int valueLength = convertedValue.length();
        if (valueLength > length)
        {
            if (truncatable)
            {
                convertedValue = convertedValue.substring(0, length);
            }
            else
            {
                throw new IllegalArgumentException("Value specified is too long to fit in field");
            }
        }
        else if (valueLength < length)
        {
            if (paddable)
            {
                convertedValue = padValue(convertedValue, length);
            }
        }
        
        if (required && TextUtils.allPadCharacters(convertedValue, padCharacter))
        {
            throw new IllegalArgumentException("Required field has no value");
        }
        
        return convertedValue;
    }
    
    public final void setJustification(int justification)
    {
        this.justification = justification;

    }

    public final int getJustification()
    {
        return justification;
    }

    public final void setTruncatable(boolean truncatable)
    {
        this.truncatable = truncatable;

    }

    public final boolean isTrucatable()
    {
        return truncatable;
    }

    public final void setPaddable(boolean paddable)
    {
        this.paddable = paddable;

    }

    public final boolean isPaddable()
    {
        return paddable;
    }

    public final void setPadCharacter(char padCharacter)
    {
        this.padCharacter = padCharacter;

    }

    public final char getPadCharacter()
    {
        return padCharacter;
    }

    public final boolean isRequired()
    {
        return required;
    }

    public final void setRequired(boolean required)
    {
        this.required = required;
        
    }

    private String padValue(String value, int length)
    {
        String paddedValue = null;
        
        switch (justification)
        {
        case LEFT_JUSTIFIED:
            paddedValue = TextUtils.leftJustifyText(value, length, padCharacter);
            break;
            
        case CENTER_JUSTIFIED:
            paddedValue = TextUtils.centerJustifyText(value, length, padCharacter);
            break;
            
        case RIGHT_JUSTIFIED:
            paddedValue = TextUtils.rightJustifyText(value, length, padCharacter);
            break;
            
        default:
            throw new IllegalStateException("Unknown justification value");
        }
        
        return paddedValue;
    }


    private String unpadValue(String value)
    {
        String unpaddedValue = value;

        switch (justification)
        {
        case LEFT_JUSTIFIED:
            unpaddedValue = TextUtils.undoLeftJustification(value, padCharacter);
            break;
            
        case CENTER_JUSTIFIED:
            unpaddedValue = TextUtils.undoCenterJustification(value, padCharacter);
            break;
            
        case RIGHT_JUSTIFIED:
            unpaddedValue = TextUtils.undoRightJustification(value, padCharacter);
            break;
        }
        
        return unpaddedValue;
    }
}
