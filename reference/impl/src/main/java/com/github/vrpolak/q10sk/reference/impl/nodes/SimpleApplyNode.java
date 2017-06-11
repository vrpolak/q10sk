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

package com.github.vrpolak.q10sk.reference.impl.apply;  // to only allow apply factory acces to the constructor

import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoApplyNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoApplyNodeFactory;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWnableNode;
import com.github.vrpolak.q10sk.reference.api.Q10skHlwnpoWnizedNode;

/**
 * Immutable object representing a weakly normalizable hlwnpo node other than Sxyz.
 *
 * @author Vratko Polak
 */
class SimpleApplyNode implements Q10skHlwnpoApplyNode {

    private final Q10skHlwnpoWnableNode function;
    private final Q10skHlwnpoNode argument;
    private final Q10skHlwnpoApplyNodeFactory factory;

    SimpleApplyNode(final Q10skHlwnpoWnableNode argumentF, final Q10skHlwnpoNode argumentX, final Q10skHlwnpoApplyNodeFactory factory) {
        this.function = argumentF;
        this.argument = argumentX;
        this.factory = factory;
    }

    @Override
    public Q10skHlwnpoWnableNode apply(final Q10skHlwnpoNode argument) {
        return this.factory.create(this, argument);
    }

    @Override
    public Q10skHlwnpoWnizedNode weaklyNormalize() {
        // As this.function is NOT weakly normalized, the following is guaranteed to make progress.
        return this.function.weaklyNormalize().apply(this.argument).weaklyNormalize();
    }

}
