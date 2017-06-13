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

package com.github.vrpolak.q10sk.reference.impl.sx;  // to only allow sx factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;

/**
 * Immutable object representing a Sx hlwnpo node.
 *
 * @author Vratko Polak
 */
public class SimpleSxNode implements Q10skHlwnpoSxNode {

    private final Q10skHlwnpoNode argumentX;
    private final Q10skHlwnpoSxyNodeFactory simpleSxyFactory;

    // Package-private constructor for *Factory to use.
    SimpleSxNode(final Q10skHlwnpoNode argumentX, final Q10skHlwnpoSxyNodeFactory simpleSxyFactory) {
        this.argumentX = argumentX;
        this.simpleSxyFactory = simpleSxyFactory;
    }

    @Override
    public Q10skHlwnpoSxyNode apply(final Q10skHlwnpoNode argumentY) {
        return this.simpleSxyFactory.create(this.argumentX, argumentY);
    }

    @Override
    public SimpleSxNode weaklyNormalize() {
        return this;
    }

}
