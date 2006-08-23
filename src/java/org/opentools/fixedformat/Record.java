package org.opentools.fixedformat;

import java.util.Map;

public interface Record
{
    /**
     * Reads the record into the map.
     * 
     * @param record the record to read.
     * @return a map containing the values in the record. The
     * map keys are the field names.
     */
    Map scanRecord(String record);
    
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
}
