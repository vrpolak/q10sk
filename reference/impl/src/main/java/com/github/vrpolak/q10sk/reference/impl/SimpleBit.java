/* Project q10sk, a minimalistic model of computation.
 * Copyright (C) 2017  Vratko Polak
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
// TODO: Also add information on how to contact you by electronic and paper mail.

package com.github.vrpolak.q10sk.reference.impl;

import com.github.vrpolak.q10sk.reference.api.ImmutableBit;

/**
 * Simple implementation of single immutable bit value. Only "zero" and "one" instances are possible.
 *
 * @author Vratko Polak
 */
public class SimpleBit implements ImmutableBit {

    /**
     * Zero bit, one of two allowed instances available as static field.
     */
    public static final SimpleBit ZERO = new SimpleBit(false);
    /**
     * One bit, one of two allowed instances available as static field.
     */
    public static final SimpleBit ONE = new SimpleBit(true);

    /**
     * Private field to hold the boolean value.
     */
    private final boolean isOne;

    /**
     * Private constructor used to create the two instances.
     */
    private SimpleBit(final boolean isOne) {
        this.isOne = isOne;
    }

    /**
     * Return true if the value is one, return false if the value is zero.
     *
     * @return boolean value
     */
    @Override
    public boolean isOne() {
        return isOne;
    }

}
