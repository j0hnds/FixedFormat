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
    
    /**
     * Retrieves the value from the specified object.
     * 
     * @param objectToAccess the object from which to obtain the value.
     * @param propertyName the name of the property to access.
     * @return the value of the property.
     */
    Object getValue(Object objectToAccess, String propertyName);
}
