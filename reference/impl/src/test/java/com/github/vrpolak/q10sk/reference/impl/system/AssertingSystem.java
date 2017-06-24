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
import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.Assert;

/**
 * Mutable system which maintains a queue of assering input/output operations.
 *
 * @author Vratko Polak
 */
public class AssertingSystem implements BitSystem {

    private Queue<BitSystem> operationsQueue = new ArrayDeque<BitSystem>(1);

    // The implicit zero-argument constructor is public for anyone to use.

    public AssertingSystem shallAccept(final ImmutableBit expectedBit) {
        this.operationsQueue.add(new AssertingConsumerItem(expectedBit));
        return this;
    }

    public AssertingSystem shallGet(final ImmutableBit expectedBit) {
        this.operationsQueue.add(new AssertingSupplierItem(expectedBit));
        return this;
    }

    @Override
    public void accept(final ImmutableBit actualBit) {
        Assert.assertFalse("System does not expect any output (or input) at this point.", this.operationsQueue.isEmpty());
        this.operationsQueue.remove().accept(actualBit);
    }

    @Override
    public ImmutableBit get() {
        Assert.assertFalse("System does not expect any input (or output) at this point.", this.operationsQueue.isEmpty());
        return this.operationsQueue.remove().get();
    }

}
