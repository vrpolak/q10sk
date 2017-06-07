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

import com.github.vrpolak.q10sk.reference.api.Q10skWnpoQNode;
import com.github.vrpolak.q10sk.reference.api.Q10skWnpoQNodeFactory;

/**
 * Immutable object for referencing a single Q node of q10sk state tree.
 *
 * @author Vratko Polak
 */
public class ReferencingQNodeFactory implements Q10skWnpoQNodeFactory {

    /**
     * The remembered node.
     */
    private final Q10skWnpoQNode node;

    /**
     * Constructor which remembers a node.
     */
    public ReferencingQNodeFactory(final Q10skWnpoQNode node) {
        this.node = node;
    }

    /**
     * The remembered node. May be called multiple times.
     *
     * @return Q node, each call references the same instance.
     */
    @Override
    public Q10skWnpoQNode create() {
        return node;
    }

}
