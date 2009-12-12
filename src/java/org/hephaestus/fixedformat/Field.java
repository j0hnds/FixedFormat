package org.hephaestus.fixedformat;

/**
 * The interface of a fixed field. Allows the
 * definition of its length, description and
 * data type.
 * 
 * @author djs
 *
 */
public interface Field
{
    /**
     * Sets the length of the field.
     * 
     * @param length the length of the field
     */
    void setLength(int length);
    
    /**
     * Gets the length of the field.
     * 
     * @return the length of the field.
     */
    int getLength();
    
    /**
     * Sets the symbolic name of the field.
     * 
     * @param name the name of the field.
     */
    void setName(String name);
    
    /**
     * Gets the symbolic name of the field.
     * 
     * @return the name of the field.
     */
    String getName();
    
    /**
     * Sets the fields required state.
     * 
     * @param required true if field must not be blank.
     */
    void setRequired(boolean required);
    
    /**
     * Gets the required status of the field.
     * 
     * @return true means field is required.
     */
    boolean isRequired();
    
    /**
     * Sets the value encoder/decoder to use.
     * 
     * @param codec the value codec.
     */
    void setValueCodec(ValueCodec codec);
    
    /**
     * Gets the value encoder/decoder being used.
     * 
     * @return the codec.
     */
    ValueCodec getValueCodec();
    
    /**
     * Sets the description of the field.
     * 
     * @param description the description of the field.
     */
    void setDescription(String description);
    
    /**
     * Gets the description of the field.
     * 
     * @return the description of the field.
     */
    String getDescription();
    
    /**
     * Extracts the raw data value from the record.
     * 
     * @param record the record from which to extract the value.
     * @param position the starting position for the field
     * @return the raw value of the record.
     */
    String extractRawValue(String record, int position);
    
    /**
     * Extracts the typed value from the record.
     * 
     * @param record the record to extract the value from
     * @param position the starting position for the field
     * @return the value of the record.
     */
    Object extractTypedValue(String record, int position);
    
    /**
     * Formats the value for use in the fixed-length
     * record.
     * 
     * @param value the value to be formatted.
     * 
     * @return the formatted value.
     */
    String formatValue(Object value);
    
}
