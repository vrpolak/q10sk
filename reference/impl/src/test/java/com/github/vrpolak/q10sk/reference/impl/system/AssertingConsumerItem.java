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

package com.github.vrpolak.q10sk.reference.impl.test;

import com.github.vrpolak.q10sk.reference.api.BitSystem;
import com.github.vrpolak.q10sk.reference.api.ImmutableBit;
import com.github.vrpolak.q10sk.reference.impl.SimpleBit;
import org.junit.Assert;

/**
 * Immutable System performing asserts expecting to consume the expected bit.
 */
public class AssertingConsumerItem implements BitSystem {

    private final ImmutableBit bitToExpect;

    public AssertingConsumerItem(final ImmutableBit bitToExpect) {
        this.bitToExpect = bitToExpect;
    }

    @Override
    public void accept(final ImmutableBit actualBit) {
        Assert.assertEquals("Got wrong bit to consume", this.bitToExpect, actualBit);
    }

    @Override
    public ImmutableBit get() {
        Assert.fail("Asked to supply a bit instead of consuming it.");
        return SimpleBit.ZERO;
    }

}
