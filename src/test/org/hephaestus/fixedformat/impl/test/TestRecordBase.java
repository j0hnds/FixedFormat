package org.hephaestus.fixedformat.impl.test;

import org.hephaestus.fixedformat.RecordTypeAccessor;

public class TestRecordBase implements RecordTypeAccessor
{
    private long recordType;

    public long getRecordType()
    {
        return recordType;
    }

    public void setRecordType(long recordType)
    {
        this.recordType = recordType;
    }

    public String getRecordTypeAsString()
    {
        return Long.toString(recordType);
    }

    
}
