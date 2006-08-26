package org.opentools.fixedformat;

/**
 * Implement this interface to construct new records.
 * 
 * @author djs
 *
 */
public interface RecordFactory
{
    /**
     * Sets the record identifier to use.
     * 
     * @param identifier the record identifier to use
     */
    void setRecordIdentifier(RecordIdentifier identifier);
    
    /**
     * Gets the record identifier being used.
     * 
     * @return the record identifier being used.
     */
    RecordIdentifier getRecordIdentifier();
    
    /**
     * Creates a populated record from the specified
     * fixed-length record.
     * 
     * @param record the fixed length record to read
     * @return newly created fixed length record. null
     * if unable to determine the record type.
     */
    Object createRecord(String record);
    
    String formatRecord(RecordTypeAccessor valueObject);
}
