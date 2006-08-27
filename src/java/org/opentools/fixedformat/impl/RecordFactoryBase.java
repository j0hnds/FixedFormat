package org.opentools.fixedformat.impl;

import java.util.Map;

import org.opentools.fixedformat.Record;
import org.opentools.fixedformat.RecordFactory;
import org.opentools.fixedformat.RecordIdentifier;
import org.opentools.fixedformat.RecordTypeAccessor;

public class RecordFactoryBase implements RecordFactory
{
    private RecordIdentifier recordIdentifier;
    private Map recordFormats;
    private Map recordDataMap;
    
    public void setRecordIdentifier(RecordIdentifier identifier)
    {
        this.recordIdentifier = identifier;

    }

    public RecordIdentifier getRecordIdentifier()
    {
        return recordIdentifier;
    }

    public Object createRecord(String record)
    {
        if (recordIdentifier == null)
        {
            throw new IllegalStateException("No record identifier configured for this factory");
        }
        
        String recordId = recordIdentifier.getRecordIdentifier(record);
        if (recordId == null)
        {
            throw new RuntimeException("No record identifier found in record: " + record);
        }
        
        if (recordFormats == null)
        {
            throw new IllegalStateException("No Record Format Map configured for this factory");
        }
        
        Record recordFormat = (Record) recordFormats.get(recordId);
        if (recordFormat == null)
        {
            throw new IllegalStateException("No record format configured for this record ID: " + recordId);
        }
        
        if (recordDataMap == null)
        {
            throw new IllegalStateException("No record data map configured for this factory");
        }
        
        Object recordData = recordDataMap.get(recordId);
        if (recordData == null)
        {
            throw new IllegalStateException("No record data object configured for this record ID:" + recordId);
        }
        
        recordFormat.scanRecord(record, recordData);
        
        return recordData;
    }

    public String formatRecord(RecordTypeAccessor valueObject)
    {
        if (valueObject == null)
        {
            throw new IllegalArgumentException("Value object must not be null");
        }

        String recordType = valueObject.getRecordTypeAsString();
        if (recordType == null)
        {
            throw new IllegalArgumentException("Null record type");
        }

        if (recordFormats == null)
        {
            throw new IllegalStateException("No Record Format Map configured for this factory");
        }
        
        Record recordFormat = (Record) recordFormats.get(recordType);
        if (recordFormat == null)
        {
            throw new IllegalStateException("No record format configured for this record ID: " + recordType);
        }
        
        return recordFormat.formatRecord(valueObject);
    }

    public Map getRecordDataMap()
    {
        return recordDataMap;
    }

    public void setRecordDataMap(Map recordDataMap)
    {
        this.recordDataMap = recordDataMap;
    }

    public Map getRecordFormats()
    {
        return recordFormats;
    }

    public void setRecordFormats(Map recordFormatMap)
    {
        this.recordFormats = recordFormatMap;
    }

}
