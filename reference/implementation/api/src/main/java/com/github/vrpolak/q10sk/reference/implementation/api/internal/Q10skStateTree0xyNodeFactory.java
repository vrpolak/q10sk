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

package com.github.vrpolak.q10sk.reference.implementation.api;

/**
 * Immutable object for creating 0xy nodes from two arguments.
 *
 * @author Vratko Polak
 */
public interface Q10skStateTree0xyNodeFactory extends Q10skStateTreeInnerNodeTwoArgumentFactory<Q10skStateTree0xyNode> {

    /**
     * Create new 0xy node using the arguments. May be called multiple times.
     *
     * @return created 0xy node, each call references different instance.
     */
    @Override
    Q10skStateTree0xyNode create(final Q10skStateTreeGeneralNode argumentX, final Q10skStateTreeGeneralNode argumentY);

}
