package org.opentools.fixedformat.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opentools.fixedformat.Field;
import org.opentools.fixedformat.ObjectPopulator;
import org.opentools.fixedformat.Record;

/**
 * Base implementation of fixed format record.
 * 
 * @author djs
 *
 */
public class RecordBase implements Record
{
    private List fieldDefinitions;
    private ObjectPopulator mapPopulator;
    private ObjectPopulator beanPopulator;
    
    public final Map scanRecord(String record)
    {
        Map recordMap = new HashMap();
        
        populateObject(record, recordMap, mapPopulator);
        
        return recordMap;
    }

    public final void scanRecord(String record, Object valueObject)
    {
        populateObject(record, valueObject, beanPopulator);
    }

    public final String formatRecord(Object record)
    {
        String formattedRecord = null;
        
        if (record == null)
        {
            throw new IllegalArgumentException("Must specify non-null record to format");
        }
        
        if (record instanceof Map)
        {
            formattedRecord = formatObject(record, mapPopulator);
        }
        else
        {
            formattedRecord = formatObject(record, beanPopulator);
        }
        
        return formattedRecord;
    }

    public final List getFieldDefinitions()
    {
        return fieldDefinitions;
    }

    public final void setFieldDefinitions(List fieldDefinitions)
    {
        this.fieldDefinitions = fieldDefinitions;
    }

    public final ObjectPopulator getBeanPopulator()
    {
        return beanPopulator;
    }

    public final void setBeanPopulator(ObjectPopulator beanPopulator)
    {
        this.beanPopulator = beanPopulator;
    }

    public final ObjectPopulator getMapPopulator()
    {
        return mapPopulator;
    }

    public final void setMapPopulator(ObjectPopulator mapPopulator)
    {
        this.mapPopulator = mapPopulator;
    }
    
    private final String formatObject(Object objectToFormat, ObjectPopulator populator)
    {
        StringBuffer sb = new StringBuffer();
        
        if (fieldDefinitions == null)
        {
            throw new IllegalStateException("No field definitions configured for record");
        }
        
        if (objectToFormat == null)
        {
            throw new IllegalArgumentException("Must specify non-null object to format");
        }
        
        if (populator == null)
        {
            throw new IllegalArgumentException("Must specify non-null populator");
        }

        Iterator it = fieldDefinitions.iterator();
        while (it.hasNext())
        {
            Field field = (Field) it.next();
            
            Object value = populator.getValue(objectToFormat, field.getName());

            sb.append(field.formatValue(value));
        }

        return sb.toString();
    }

    private void populateObject(String record, Object objectToPopulate, ObjectPopulator populator)
    {
        if (fieldDefinitions == null)
        {
            throw new IllegalStateException("No field definitions configured for record");
        }
        
        if (objectToPopulate == null)
        {
            throw new IllegalArgumentException("Must specify non-null object to populate");
        }
        
        if (populator == null)
        {
            throw new IllegalArgumentException("Must specify non-null populator");
        }
        
        int position = 0;
        Iterator it = fieldDefinitions.iterator();
        while (it.hasNext())
        {
            Field field = (Field) it.next();
            
            Object value = field.extractTypedValue(record, position);
            
            populator.populateValue(objectToPopulate, field.getName(), value);
            
            position += field.getLength();
        }
    }
    
}
