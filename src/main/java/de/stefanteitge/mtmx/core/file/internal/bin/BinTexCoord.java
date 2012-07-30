/*
 * This file is part of mtmX.
 *
 * mtmX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mtmX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mtmX.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.stefanteitge.mtmx.core.file.internal.bin;

import de.stefanteitge.mtmx.core.file.bin.IBinTexCoord;

public class BinTexCoord implements IBinTexCoord {

	private int vertexIndex;

	private float u;

	private float v;

	@Override
	public float getU() {
		return u;
	}

	@Override
	public float getV() {
		return v;
	}

	public void setU(float u) {
		this.u = u;
	}

	public void setV(float v) {
		this.v = v;
	}

	@Override
	public int getVertexIndex() {
		return vertexIndex;
	}

	public void setVertexIndex(int vertexIndex) {
		this.vertexIndex = vertexIndex;
	}
}
