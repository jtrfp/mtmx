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
package de.stefanteitge.mtmx.core.file.internal.sit;

import java.util.ArrayList;

import de.stefanteitge.mtmx.core.file.internal.KeyBasedData;
import de.stefanteitge.mtmx.core.file.sit.ISitData;
import de.stefanteitge.mtmx.core.file.sit.SitBox;


public class SitData extends KeyBasedData implements ISitData {

	private final ArrayList<SitBox> boxes;

	public SitData() {
		boxes = new ArrayList<SitBox>();
	}

	public void addBox(SitBox box) {
		boxes.add(box);
	}

	@Override
	public SitBox[] getBoxes() {
		return boxes.toArray(new SitBox[0]);
	}
}
