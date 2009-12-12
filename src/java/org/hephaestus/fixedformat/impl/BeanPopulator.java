package org.hephaestus.fixedformat.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.textutils.TextUtils;

public class BeanPopulator implements ObjectPopulator
{

    public final void populateValue(Object objectToPopulate, String propertyName,
            Object value)
    {
        if (objectToPopulate == null)
        {
            throw new IllegalArgumentException("Object to populate must be non-null");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        try
        {
            PropertyUtils.setProperty(objectToPopulate, propertyName, value);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error setting property value", e);
        }

    }

    public final Object getValue(Object objectToAccess, String propertyName)
    {
        if (objectToAccess == null)
        {
            throw new IllegalArgumentException("Object to access must be non-null");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        try
        {
            return PropertyUtils.getProperty(objectToAccess, propertyName);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error getting property value", e);
        }
        
    }

}
