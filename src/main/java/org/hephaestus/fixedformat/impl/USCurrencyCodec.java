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

import java.math.BigDecimal;

public final class USCurrencyCodec extends ValueCodecBase {

    protected String objectToString(Object value) {
        String encodedValue = "";

        if (value == null) {
            throw new IllegalArgumentException("Must specify non-empty value");
        }

        if (!(value instanceof BigDecimal)) {
            throw new IllegalArgumentException("Value must be BigDecimal");
        }

        BigDecimal cValue = (BigDecimal) value;

        encodedValue = cValue.setScale(2, BigDecimal.ROUND_HALF_EVEN).abs()
                .unscaledValue().toString();

        return encodedValue;
    }

    protected Object stringToObject(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Must specify non-null value");
        }

        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}
