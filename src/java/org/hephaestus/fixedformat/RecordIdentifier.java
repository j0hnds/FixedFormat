package org.hephaestus.fixedformat;

/**
 * Implement this interface to identify the type of record
 * entering the system. We cannot tell which record type
 * to create to read the record unless we know what kind
 * of record it is.
 * 
 * Implicit in this is that the fixed format records have
 * a consistent means of determinine the record type.
 * 
 * @author djs
 */
public interface RecordIdentifier
{
    /**
     * Scan the input record and return the unique identifier
     * of the record type.
     * 
     * @param record the full record to be identified.
     * @return the record type. null if unable to determine 
     * the record type.
     */
    String getRecordIdentifier(String record);
}
