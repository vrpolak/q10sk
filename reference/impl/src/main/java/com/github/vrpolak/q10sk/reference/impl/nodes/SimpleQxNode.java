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

package com.github.vrpolak.q10sk.reference.impl.qx;  // to only allow qx factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoQxNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoQxyNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;

/**
 * Immutable object representing a Qx hlwnpo node.
 *
 * @author Vratko Polak
 */
class SimpleQxNode implements Q10skHlwnpoQxNode {

    private final Q10skHlwnpoNode argumentX;
    private final Q10skHlwnpoQxyNodeFactory simpleQxyFactory;

    SimpleQxNode(final Q10skHlwnpoNode argumentX, final Q10skHlwnpoQxyNodeFactory simpleQxyFactory) {
        this.argumentX = argumentX;
        this.simpleQxyFactory = simpleQxyFactory;
    }

    @Override
    public SimpleQxyNode apply(final Q10skHlwnpoNode argumentY) {
        return this.simpleQxyFactory.create(this.argumentX, argumentY);
    }

    @Override
    public SimpleQxNode weaklyNormalize() {
        return this;
    }

}
