package org.hephaestus.fixedformat;

import java.util.List;

public interface Record
{
    /**
     * Reads the record into the specified value object. Uses
     * the field names to map to properties in the value object
     * to set the values.
     * 
     * @param record the record to read.
     * @param valueObject the object in which to set the values.
     */
    void scanRecord(String record, Object valueObject);
    
    /**
     * Formats the fixed length record using the data in the
     * specified object. If the specified object is a map, it
     * is assumed that the keys in the map are the field names. If
     * it is just a POJO, it is assumed that the property names
     * map to the field names (bean-style).
     * 
     * @param record the data record.
     * @return formatted fixed-field record.
     */
    String formatRecord(Object record);
    
    /**
     * Sets the field definitions on the record.
     * 
     * @param fieldList the list of field definitions to set.
     */
    void setFieldDefinitions(List<Field> fieldList);
    
    /**
     * Returns the list of field definitions for the record.
     * 
     * @return the list of field definitions.
     */
    List<Field> getFieldDefinitions();
    
    void setPopulator(ObjectPopulator populator);
    
    ObjectPopulator getPopulator();
}
