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

package com.github.vrpolak.q10sk.reference.impl;

import com.github.vrpolak.q10sk.reference.api.Q10skWnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skWnpoSxNode;
import com.github.vrpolak.q10sk.reference.api.Q10skWnpoSxyNode;
import com.github.vrpolak.q10sk.reference.api.Q10skWnpoSxyNodeFactory;

/**
 * Immutable object as Sx node of q10sk state tree.
 *
 * @author Vratko Polak
 */
public class SimpleSxNode implements Q10skWnpoSxNode {

    /**
     * The x argument instance.
     */
    private final Q10skWnpoNode argumentX;

    /**
     * The remembered Sxy node factory.
     */
    private final Q10skWnpoSxyNodeFactory factory;

    /**
     * Constructor which remembers a node factory and the argument.
     */
    public SimpleSxNode(final Q10skWnpoSxyNodeFactory factory, final Q10skWnpoNode argumentX) {
        this.factory = factory;
        this.argumentX = argumentX;
    }

    /**
     * Return a new node which applies (without evaluating) this as a function to the given argument.
     *
     * @return result Sxy node, created by the remembered factory.
     */
    @Override
    public Q10skWnpoSxyNode apply(final Q10skWnpoNode argumentY) {
        return factory.create(argumentX, argumentY);
    }

}
-