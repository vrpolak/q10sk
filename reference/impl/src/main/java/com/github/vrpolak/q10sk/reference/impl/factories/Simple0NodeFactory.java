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

package com.github.vrpolak.q10sk.reference.impl._0;

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0NodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0xNodeFactory;

/**
 * Immutable object for creating 0 nodes restricted to Simple0Node implementation.
 *
 * @author Vratko Polak
 */
public class Simple0NodeFactory implements Q10skHlwnpo0NodeFactory {

    private final Q10skHlwnpo0xNodeFactory simple0xFactory;

    // Package-private constructor for *Factory to use.
    Simple0NodeFactory(final Q10skHlwnpo0xNodeFactory simple0xFactory) {
        this.simple0xFactory = simple0xFactory;
    }

    @Override
    public Simple0Node create() {
        return new Simple0Node(this.simple0xFactory);
    }

}
