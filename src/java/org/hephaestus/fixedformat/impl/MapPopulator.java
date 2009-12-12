package org.hephaestus.fixedformat.impl;

import java.util.Map;

import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.textutils.TextUtils;

public class MapPopulator implements ObjectPopulator
{

    @SuppressWarnings("unchecked")
	public final void populateValue(Object objectToPopulate, String propertyName,
            Object value)
    {
        if (objectToPopulate == null)
        {
            throw new IllegalArgumentException("Object to populate must be non-null");
        }
        
        if (! (objectToPopulate instanceof Map<?,?>))
        {
            throw new IllegalArgumentException("Object to populate must be a map");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        Map<String,Object> mapObject = (Map<String,Object>) objectToPopulate;
        
        mapObject.put(propertyName, value);
    }

    @SuppressWarnings("unchecked")
	public final Object getValue(Object objectToAccess, String propertyName)
    {
        if (objectToAccess == null)
        {
            throw new IllegalArgumentException("Object to populate must be non-null");
        }
        
        if (! (objectToAccess instanceof Map<?,?>))
        {
            throw new IllegalArgumentException("Object to populate must be a map");
        }
        
        if (TextUtils.isBlank(propertyName))
        {
            throw new IllegalArgumentException("Property name must be non-null and not empty");
        }
        
        Map<String,Object> mapObject = (Map<String,Object>) objectToAccess;
        
        return mapObject.get(propertyName);
    }

}
