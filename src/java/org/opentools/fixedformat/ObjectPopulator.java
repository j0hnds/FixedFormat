package org.opentools.fixedformat;

/**
 * This interface is implemented by classes tuned to
 * populate an object.
 * 
 * @author djs
 *
 */
public interface ObjectPopulator
{
    /**
     * Populates the specified object with the property name and value.
     * 
     * @param objectToPopulate the object to populate
     * @param propertyName the name of the property on the object to populate
     * @param value the value of the object to populate.
     */
    void populateValue(Object objectToPopulate, String propertyName, Object value);
    
    Object getValue(Object objectToAccess, String propertyName);
}
