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

package com.github.vrpolak.q10sk.reference.impl._1x;

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpo1xNodeFactory;

/**
 * Immutable object for creating 1x nodes restricted to Simple1xNode implementation.
 *
 * @author Vratko Polak
 */
public class Simple1xNodeFactory implements Q10skHlwnpo1xNodeFactory {

    // The implicit zero-argument constructor is public for anyone to use.

    @Override
    public Simple1xNode create(final Q10skHlwnpoNode argumentX) {
        return new Simple1xNode(argumentX, this);
    }

}
