package org.hephaestus.fixedformat.impl;
/*
 * Copyright (c) 2009 Dave Sieh
 *
 * This file is part of FixedFormat.
 *
 * FixedFormat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FixedFormat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FixedFormat.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Map;

import org.hephaestus.fixedformat.Record;
import org.hephaestus.fixedformat.RecordFactory;
import org.hephaestus.fixedformat.RecordIdentifier;
import org.hephaestus.fixedformat.RecordTypeAccessor;

public class RecordFactoryBase implements RecordFactory
{
    private RecordIdentifier recordIdentifier;
    private Map<String,Record> recordFormats;
    private Map<String,Object> recordDataMap;
    
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

    public Map<String,Object> getRecordDataMap()
    {
        return recordDataMap;
    }

    public void setRecordDataMap(Map<String,Object> recordDataMap)
    {
        this.recordDataMap = recordDataMap;
    }

    public Map<String,Record> getRecordFormats()
    {
        return recordFormats;
    }

    public void setRecordFormats(Map<String,Record> recordFormatMap)
    {
        this.recordFormats = recordFormatMap;
    }

}
