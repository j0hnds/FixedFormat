package org.hephaestus.fixedformat;

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
