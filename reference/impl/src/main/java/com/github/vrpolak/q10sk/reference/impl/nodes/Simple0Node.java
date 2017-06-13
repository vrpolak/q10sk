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

package com.github.vrpolak.q10sk.reference.impl._0;  // to only allow 0 factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0Node;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0xNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0xNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;

/**
 * Immutable object representing a 0 hlwnpo node.
 *
 * @author Vratko Polak
 */
public class Simple0Node implements Q10skHlwnpo0Node {

    private final Q10skHlwnpo0xNodeFactory simple0xFactory;

    // Package-private constructor for *Factory to use.
    Simple0Node(final Q10skHlwnpo0xNodeFactory simple0xFactory) {
        this.simple0xFactory = simple0xFactory;
    }

    @Override
    public Q10skHlwnpo0xNode apply(final Q10skHlwnpoNode argumentX) {
        return this.simple0xFactory.create(argumentX);
    }

    @Override
    public Simple0Node weaklyNormalize() {
        return this;
    }

}
