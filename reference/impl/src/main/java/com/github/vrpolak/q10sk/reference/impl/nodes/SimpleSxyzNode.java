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

package com.github.vrpolak.q10sk.reference.impl.sxyz;  // to only allow sxyz factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyzNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoApplyNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoApplyNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWnizedNode;

/**
 * Immutable object representing a Sxyz hlwnpo node.
 *
 * @author Vratko Polak
 */
public class SimpleSxyzNode implements Q10skHlwnpoSxyzNode {

    private final Q10skHlwnpoNode argumentX;
    private final Q10skHlwnpoNode argumentY;
    private final Q10skHlwnpoNode argumentZ;
    private final Q10skHlwnpoApplyNodeFactory simpleApplyFactory;

    // Package-private constructor for *Factory to use.
    SimpleSxyzNode(final Q10skHlwnpoNode argumentX, final Q10skHlwnpoNode argumentY, final Q10skHlwnpoNode argumentZ, final Q10skHlwnpoApplyNodeFactory simpleApplyFactory) {
        this.argumentX = argumentX;
        this.argumentY = argumentY;
        this.argumentZ = argumentZ;
        this.simpleApplyFactory = simpleApplyFactory;
    }

    @Override
    public Q10skHlwnpoApplyNode apply(final Q10skHlwnpoNode argumentW) {
        return this.simpleApplyFactory.create(this, argumentW);
    }

    @Override
    public Q10skHlwnpoWnizedNode weaklyNormalize() {
        // Half eager would use this.argumentZ.weaklyNormalize() value instead of this.argumentZ value.
        return this.argumentX.apply(this.argumentZ).apply(this.argumentY.apply(this.argumentZ)).weaklyNormalize();
    }

}
