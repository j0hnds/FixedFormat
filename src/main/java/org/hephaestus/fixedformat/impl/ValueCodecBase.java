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

import org.hephaestus.fixedformat.ValueCodec;
import org.hephaestus.textutils.TextUtils;

public abstract class ValueCodecBase implements ValueCodec {
    private int justification = LEFT_JUSTIFIED;
    private int capitalization = LITERAL_CASE;
    private boolean truncatable = false;
    private boolean paddable = false;
    private char padCharacter = ' ';

    public final Object decodeValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must be non-null");
        }

        String unpaddedValue = value;

        // Is the value paddable?
        if (paddable) {
            unpaddedValue = unpadValue(value);
        }

        return stringToObject(unpaddedValue);
    }

    protected abstract Object stringToObject(String value);

    protected String objectToString(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Must specifie non-null value");
        }

        return value.toString();
    }

    public final String encodeValue(Object value, int length) {
        String convertedValue = null;

        if (value != null) {
            convertedValue = objectToString(value);
        }
        else if (paddable) {
            convertedValue = "";
        }
        else {
            throw new IllegalArgumentException(
                    "Unpaddable fields must be non-null");
        }

        int valueLength = convertedValue.length();
        if (valueLength > length) {
            if (truncatable) {
                convertedValue = convertedValue.substring(0, length);
            }
            else {
                throw new IllegalArgumentException(
                        "Value specified is too long to fit in field");
            }
        }
        else if (valueLength < length) {
            if (paddable) {
                convertedValue = padValue(convertedValue, length);
            }
        }

        switch (capitalization) {
        case UPPER_CASE:
            convertedValue = convertedValue.toUpperCase();
            break;

        case LOWER_CASE:
            convertedValue = convertedValue.toLowerCase();
            break;
        }

        return convertedValue;
    }

    public final void setJustification(int justification) {
        this.justification = justification;

    }

    public final int getJustification() {
        return justification;
    }

    public final void setTruncatable(boolean truncatable) {
        this.truncatable = truncatable;

    }

    public final boolean isTrucatable() {
        return truncatable;
    }

    public final void setPaddable(boolean paddable) {
        this.paddable = paddable;

    }

    public final boolean isPaddable() {
        return paddable;
    }

    public final void setPadCharacter(char padCharacter) {
        this.padCharacter = padCharacter;

    }

    public final char getPadCharacter() {
        return padCharacter;
    }

    private String padValue(String value, int length) {
        String paddedValue = null;

        switch (justification) {
        case LEFT_JUSTIFIED:
            paddedValue = TextUtils
                    .leftJustifyText(value, length, padCharacter);
            break;

        case CENTER_JUSTIFIED:
            paddedValue = TextUtils.centerJustifyText(value, length,
                    padCharacter);
            break;

        case RIGHT_JUSTIFIED:
            paddedValue = TextUtils.rightJustifyText(value, length,
                    padCharacter);
            break;

        default:
            throw new IllegalStateException("Unknown justification value");
        }

        return paddedValue;
    }

    private String unpadValue(String value) {
        String unpaddedValue = value;

        switch (justification) {
        case LEFT_JUSTIFIED:
            unpaddedValue = TextUtils
                    .undoLeftJustification(value, padCharacter);
            break;

        case CENTER_JUSTIFIED:
            unpaddedValue = TextUtils.undoCenterJustification(value,
                    padCharacter);
            break;

        case RIGHT_JUSTIFIED:
            unpaddedValue = TextUtils.undoRightJustification(value,
                    padCharacter);
            break;
        }

        return unpaddedValue;
    }

    public int getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(int capitalization) {
        this.capitalization = capitalization;
    }

    public boolean isEmpty(String value) {
        return TextUtils.allPadCharacters(value, padCharacter);
    }

}
