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

package com.github.vrpolak.q10sk.reference.impl.q;

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoQNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoQxNodeFactory;

/**
 * Immutable object for creating Q nodes restricted to SimpleQNode implementation.
 *
 * @author Vratko Polak
 */
public class SimpleQNodeFactory implements Q10skHlwnpoQNodeFactory {

    private final Q10skHlwnpoQxNodeFactory simpleQxFactory;

    // Package-private constructor for *Factory to use.
    SimpleQNodeFactory(final Q10skHlwnpoQxNodeFactory simpleQxFactory) {
        this.simpleQxFactory = simpleQxFactory;
    }

    @Override
    public SimpleQNode create() {
        return new SimpleQNode(this.simpleQxFactory);
    }

}
