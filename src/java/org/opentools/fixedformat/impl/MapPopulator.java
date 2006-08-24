package org.opentools.fixedformat.impl;

import java.util.Map;

import org.opentools.fixedformat.ObjectPopulator;
import org.opentools.textutils.TextUtils;

public class MapPopulator implements ObjectPopulator
{

    public void populateValue(Object objectToPopulate, String propertyName,
            Object value)
    {
        if (objectToPopulate == null)
        {
            throw new IllegalArgumentException("Object to populate must be non-null");
        }
        
        if (! (objectToPopulate instanceof Map))
        {
            throw new IllegalArgumentException("Object to populate must be a map");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        Map mapObject = (Map) objectToPopulate;
        
        mapObject.put(propertyName, value);
    }

    public Object getValue(Object objectToAccess, String propertyName)
    {
        if (objectToAccess == null)
        {
            throw new IllegalArgumentException("Object to populate must be non-null");
        }
        
        if (! (objectToAccess instanceof Map))
        {
            throw new IllegalArgumentException("Object to populate must be a map");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        Map mapObject = (Map) objectToAccess;
        
        return mapObject.get(propertyName);
    }

}
