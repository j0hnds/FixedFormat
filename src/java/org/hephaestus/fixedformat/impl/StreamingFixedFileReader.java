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
