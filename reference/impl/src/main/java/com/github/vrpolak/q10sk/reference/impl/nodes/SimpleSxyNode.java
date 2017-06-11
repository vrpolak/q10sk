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

package com.github.vrpolak.q10sk.reference.impl.sxy;  // to only allow sxy factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyzNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;

/**
 * Immutable object representing a Sxy hlwnpo node.
 *
 * @author Vratko Polak
 */
class SimpleSxyNode implements Q10skHlwnpoSxyNode {

    private final Q10skHlwnpoNode argumentX;
    private final Q10skHlwnpoNode argumentY;
    private final Q10skHlwnpoSxyzNodeFactory simpleSxyzFactory;

    SimpleSxyNode(final Q10skHlwnpoNode argumentX, final Q10skHlwnpoNode argumentY, final Q10skHlwnpoSxyzNodeFactory simpleSxyzFactory) {
        this.argumentX = argumentX;
        this.argumentY = argumentY;
        this.simpleSxyzFactory = simpleSxyzFactory;
    }

    @Override
    public SimpleSxyzNode apply(final Q10skHlwnpoNode argumentZ) {
        return this.simpleSxyzFactory.create(this.argumentX, this.argumentY, argumentZ);
    }

    @Override
    public SimpleSxyNode weaklyNormalize() {
        return this;
    }

}
