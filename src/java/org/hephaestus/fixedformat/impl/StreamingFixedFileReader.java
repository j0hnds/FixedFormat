package org.hephaestus.fixedformat.impl;

import java.io.BufferedReader;
import java.io.IOException;

import org.hephaestus.fixedformat.RecordIdentifier;
import org.hephaestus.fixedformat.StreamingRecordHandler;

public class StreamingFixedFileReader
{
    private RecordIdentifier recordIdentifier;
    private StreamingRecordHandler handler;
    
    void process(BufferedReader reader) throws IOException
    {
        if (recordIdentifier == null)
        {
            throw new IllegalStateException("No record identifier configured for this reader");
        }
        
        if (handler == null)
        {
            throw new IllegalStateException("No handler configured for this reader");
        }
        
        // Read one line at a time.
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null)
        {
            String recordID = recordIdentifier.getRecordIdentifier(inputLine);
            
            // Call the handler
            handler.handleRecord(recordID, inputLine);
        }
    }
}
