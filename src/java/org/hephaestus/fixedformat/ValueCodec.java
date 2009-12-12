package org.hephaestus.fixedformat;
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
 * Defines the interface for a value encoder/decoder.
 * 
 * @author djs
 *
 */
public interface ValueCodec
{
    // Justification values
    int LEFT_JUSTIFIED = 1;
    int RIGHT_JUSTIFIED = 2;
    int CENTER_JUSTIFIED = 3;
    
    // Capitalization values
    int LITERAL_CASE = 1;
    int UPPER_CASE = 2;
    int LOWER_CASE = 3;
    
    /**
     * Encodes the value for use in fixed-format record
     * 
     * @param value the value to encode
     * @param length the length of the field
     * @return the encoded value.
     */
    String encodeValue(Object value, int length);
    
    /**
     * Decodes the encoded value to a typed value.
     * 
     * @param value the raw string value to decode.
     * @return the decoded value.
     */
    Object decodeValue(String value);
    
    void setJustification(int justification);
    
    int getJustification();
    
    void setTruncatable(boolean truncatable);
    
    boolean isTrucatable();
    
    void setPaddable(boolean paddable);
    
    boolean isPaddable();
    
    void setPadCharacter(char padCharacter);
    
    char getPadCharacter();
    
    void setCapitalization(int capitalization);
    
    int getCapitalization();
    
    boolean isEmpty(String value);
}
