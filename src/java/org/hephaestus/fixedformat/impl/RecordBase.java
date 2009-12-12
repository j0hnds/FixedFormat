package org.hephaestus.fixedformat.impl;

import java.util.Iterator;
import java.util.List;

import org.hephaestus.fixedformat.Field;
import org.hephaestus.fixedformat.ObjectPopulator;
import org.hephaestus.fixedformat.Record;

/**
 * Base implementation of fixed format record.
 * 
 * @author djs
 *
 */
public class RecordBase implements Record
{
    private List fieldDefinitions;
    private ObjectPopulator populator;
    
    public final void scanRecord(String record, Object valueObject)
    {
        populateObject(record, valueObject, populator);
    }

    public final String formatRecord(Object record)
    {
        String formattedRecord = null;
        
        if (record == null)
        {
            throw new IllegalArgumentException("Must specify non-null record to format");
        }
        
        formattedRecord = formatObject(record, populator);
        
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

    public final ObjectPopulator getPopulator()
    {
        return populator;
    }

    public final void setPopulator(ObjectPopulator beanPopulator)
    {
        this.populator = beanPopulator;
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
