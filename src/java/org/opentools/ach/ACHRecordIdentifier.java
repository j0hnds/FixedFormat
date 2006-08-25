package org.opentools.ach;

import org.opentools.fixedformat.RecordIdentifier;
import org.opentools.textutils.TextUtils;

/**
 * This class is used to identify ACH record types from
 * the original fixed-format record.
 * 
 * @author djs
 *
 */
public class ACHRecordIdentifier implements RecordIdentifier
{

    public final String getRecordIdentifier(String record)
    {
        String recordIdentifier = null;
        
        if (! TextUtils.isBlank(record))
        {
            recordIdentifier = record.substring(0, 1);
        }
        
        return recordIdentifier;
    }

}
