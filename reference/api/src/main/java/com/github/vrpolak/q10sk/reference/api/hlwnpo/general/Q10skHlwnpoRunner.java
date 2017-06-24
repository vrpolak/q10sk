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

package com.github.vrpolak.q10sk.reference.api;

/**
 * Object used for running q10sk programs given as hlwnpo nodes.
 *
 * <p>The behavior of the implementation should repeatedly call
 * weak normalization on the root node and perform the known operation,
 * until a known type of halt (or indefinitely).
 *
 * @author Vratko Polak
 */
public interface Q10skHlwnpoRunner extends Q10skRunner<Q10skHlwnpoNode, Q10skHlwnpoWnizedNode> {

    // Nothing to add to what parent interfaces imply.

}
