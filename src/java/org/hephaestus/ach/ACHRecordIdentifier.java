package org.hephaestus.ach;
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

import org.hephaestus.fixedformat.RecordIdentifier;
import org.hephaestus.textutils.TextUtils;

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
