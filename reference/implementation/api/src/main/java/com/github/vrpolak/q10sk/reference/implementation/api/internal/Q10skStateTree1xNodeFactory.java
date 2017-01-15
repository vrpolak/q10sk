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
 * Immutable object for creating 1x nodes from one argument.
 *
 * @author Vratko Polak
 */
public interface Q10skStateTree1xNodeFactory extends Q10skStateTreeInnerNodeOneArgumentFactory<Q10skStateTree1xNode> {

    /**
     * Create new 1x node using the argument. May be called multiple times.
     *
     * @return created 1x node, each call reference different instance.
     */
    @Override
    Q10skStateTree1xNode create(final Q10skStateTreeGeneralNode argumentX);

}
