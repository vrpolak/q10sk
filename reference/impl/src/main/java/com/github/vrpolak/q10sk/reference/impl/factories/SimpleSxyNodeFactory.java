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

package com.github.vrpolak.q10sk.reference.impl.sxy;

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoSxyzNodeFactory;

/**
 * Immutable object for creating Sxy nodes restricted to SimpleSxyNode implementation.
 *
 * @author Vratko Polak
 */
public class SimpleSxyNodeFactory implements Q10skHlwnpoSxyNodeFactory {

    private final Q10skHlwnpoSxyzNodeFactory simpleSxyzFactory;

    // Package-private constructor for *Factory to use.
    SimpleSxyNodeFactory(final Q10skHlwnpoSxyzNodeFactory simpleSxyzFactory) {
        this.simpleSxyzFactory = simpleSxyzFactory;
    }

    @Override
    public SimpleSxyNode create(final Q10skHlwnpoNode argumentX, final Q10skHlwnpoNode argumentY) {
        return new SimpleSxyNode(argumentX, argumentY, this.simpleSxyzFactory);
    }

}
