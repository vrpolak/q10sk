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

package com.github.vrpolak.q10sk.reference.impl.sxyz;

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoApplyNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyzNodeFactory;

/**
 * Immutable object for creating Sxyz nodes restricted to SimpleSxyzNode implementation.
 *
 * @author Vratko Polak
 */
public class SimpleSxyzNodeFactory implements Q10skHlwnpoSxyzNodeFactory {

    private final Q10skHlwnpoApplyNodeFactory simpleApplyFactory;

    // Package-private constructor for *Factory to use.
    SimpleSxyzNodeFactory(final Q10skHlwnpoApplyNodeFactory simpleApplyFactory) {
        this.simpleApplyFactory = simpleApplyFactory;
    }

    @Override
    public SimpleSxyzNode create(final Q10skHlwnpoNode argumentX, final Q10skHlwnpoNode argumentY, final Q10skHlwnpoNode argumentZ) {
        return new SimpleSxyzNode(argumentX, argumentY, argumentZ, this.simpleApplyFactory);
    }

}
