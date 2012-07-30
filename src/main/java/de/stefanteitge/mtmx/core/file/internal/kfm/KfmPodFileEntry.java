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
package de.stefanteitge.mtmx.core.file.internal.kfm;

import java.io.IOException;

import de.stefanteitge.mtmx.core.file.FileLoadException;
import de.stefanteitge.mtmx.core.file.internal.pod.PodFileEntry;
import de.stefanteitge.mtmx.core.file.kfm.IKfmData;
import de.stefanteitge.mtmx.core.file.kfm.IKfmPodFileEntry;
import de.stefanteitge.mtmx.core.file.pod.PodFile;


public class KfmPodFileEntry extends PodFileEntry implements IKfmPodFileEntry {

	public KfmPodFileEntry(PodFile podFile) {
		super(podFile);
	}

	@Override
	public IKfmData getData() throws FileLoadException {
		try {
			KfmDataLoader kfmDataLoader = new KfmDataLoader();
			return kfmDataLoader.load(getInputStreamFromPod());
		} catch (IOException e) {
			throw new FileLoadException(e);
		}
	}
}
