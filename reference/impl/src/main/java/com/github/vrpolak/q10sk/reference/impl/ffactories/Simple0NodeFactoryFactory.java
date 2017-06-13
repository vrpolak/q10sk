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

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0NodeFactoryFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo0xNodeFactory;

/**
 * Immutable object for creating hlwnpo 0 node factories from 0x node factories.
 *
 * <p>As 0 apply method creates 0x nodes, it needs access to an 0x node factory.
 * This is the way to create a 0 factory which has such an access.
 *
 * @author Vratko Polak
 */
public class Simple0NodeFactoryFactory implements Q10skHlwnpo0NodeFactoryFactory {

    // The implicit zero-argument constructor is public for anyone to use.

    @Override
    public Simple0NodeFactory create(final Q10skHlwnpo0xNodeFactory simple0xFactory) {
        return new Simple0NodeFactory(simple0xFactory);
    }

}
