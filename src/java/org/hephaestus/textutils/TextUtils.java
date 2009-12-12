package org.hephaestus.textutils;
/*
 * Copyright (c) 2009 Dave Sieh
 *
 * This file is part of FixedFormat.
 *
 * FixedFormat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FixedFormat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FixedFormat.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Utility class for performing a variety of operations
 * against string data.
 * 
 * @author djs
 *
 */
public class TextUtils
{

    /**
     * Private constructor; cannot instantiate this class.
     *
     */
    private TextUtils()
    {
        
    }
    
    /**
     * Tests the specified string to determine if it null or the
     * empty string. This method will trim a non-null string to
     * verify emptiness.
     * 
     * @param testString the string to test
     * @return true if the string is null or empty.
     */
    public static boolean isBlank(String testString)
    {
        boolean blank = true;
        
        if (testString != null)
        {
            blank = testString.trim().length() == 0;
        }
        
        return blank;
    }
    
    public static String rightJustifyText(String originalValue, int length, char padCharacter)
    {
        String encodedValue = originalValue;
        
        if (originalValue == null)
        {
            // We will assume that null indicates an empty value
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<length; i++)
            {
                sb.append(padCharacter);
            }
            
            encodedValue = sb.toString();
        }
        else 
        {
            int len = originalValue.length();
            if (len < length)
            {
                int numPads = length - len;
                StringBuffer sb = new StringBuffer(originalValue);
                for (int i=0; i<numPads; i++)
                {
                    sb.insert(0, padCharacter);
                }
                
                encodedValue = sb.toString();
            }
        }
        
        return encodedValue;
    }
    
    public static String undoRightJustification(String justifiedValue, char padCharacter)
    {
        String unjustifiedText = "";
        int pos = firstNonPadFromLeft(justifiedValue.toCharArray(), padCharacter);
        if (pos >= 0)
        {
            unjustifiedText = justifiedValue.substring(pos);
        }

        return unjustifiedText;
    }
    
    public static String leftJustifyText(String originalValue, int length, char padCharacter)
    {
        String encodedValue = originalValue;
        
        if (originalValue == null)
        {
            // We will assume that null indicates an empty value
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<length; i++)
            {
                sb.append(padCharacter);
            }
            
            encodedValue = sb.toString();
        }
        else 
        {
            int len = originalValue.length();
            if (len < length)
            {
                int numPads = length - len;
                StringBuffer sb = new StringBuffer(originalValue);
                for (int i=0; i<numPads; i++)
                {
                    sb.append(padCharacter);
                }
                
                encodedValue = sb.toString();
            }
        }
        
        return encodedValue;
    }
    
    public static String undoLeftJustification(String justifiedValue, char padCharacter)
    {
        String unjustifiedText = "";
        int pos = firstNonPadFromRight(justifiedValue.toCharArray(), padCharacter);
        if (pos >= 0)
        {
            unjustifiedText = justifiedValue.substring(0, pos + 1);
        }

        return unjustifiedText;
    }
    
    public static String centerJustifyText(String originalValue, int length, char padCharacter)
    {
        String encodedValue = originalValue;
        
        if (originalValue == null)
        {
            // We will assume that null indicates an empty value
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<length; i++)
            {
                sb.append(padCharacter);
            }
            
            encodedValue = sb.toString();
        }
        else 
        {
            int len = originalValue.length();
            if (len < length)
            {
                int numPads = length - len;
                int numPadsLeft = numPads / 2;
                int numPadsRight = numPadsLeft;
                if ((numPadsLeft + numPadsRight) < numPads)
                {
                    numPadsRight++;
                }
                StringBuffer sb = new StringBuffer(originalValue);
                for (int i=0; i<numPadsLeft; i++)
                {
                    sb.insert(0, padCharacter);
                }
                for (int i=0; i<numPadsRight; i++)
                {
                    sb.append(padCharacter);
                }
                
                encodedValue = sb.toString();
            }
        }
        
        return encodedValue;
    }
    
    public static String undoCenterJustification(String justifiedValue, char padCharacter)
    {
        String unjustifiedText = "";
        char[] characters = justifiedValue.toCharArray();
        
        int pos = firstNonPadFromRight(characters, padCharacter);
        if (pos >= 0)
        {
            unjustifiedText = justifiedValue.substring(0, pos + 1);
            
            pos = firstNonPadFromLeft(characters, padCharacter);
            if (pos >= 0)
            {
                unjustifiedText = unjustifiedText.substring(pos);
            }
        }

        return unjustifiedText;
    }
    
    public static int firstNonPadFromLeft(char[] chars, char padCharacter)
    {
        int pos = -1;
        for (int i=0; i<chars.length && pos < 0; i++)
        {
            if (chars[i] != padCharacter)
            {
                pos = i;
            }
        }
        
        return pos;
    }
    
    public static int firstNonPadFromRight(char[] chars, char padCharacter)
    {
        int pos = -1;
        for (int i=(chars.length - 1); i>=0 && pos < 0; i--)
        {
            if (chars[i] != padCharacter)
            {
                pos = i;
            }
        }
        
        return pos;
    }

    public static boolean allPadCharacters(String value, char padCharacter)
    {
        boolean allPads = false;

        allPads = firstNonPadFromLeft(value.toCharArray(), padCharacter) == -1;

        return allPads;
    }
    
}
