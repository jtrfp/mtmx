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
package de.stefanteitge.mtmx.core;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import de.stefanteitge.mtmx.core.file.FileLoadException;
import de.stefanteitge.mtmx.core.file.FileStoreException;
import de.stefanteitge.mtmx.core.file.act.IActPodFileEntry;
import de.stefanteitge.mtmx.core.file.pod.IPodData;
import de.stefanteitge.mtmx.core.file.pod.PodFile;
import de.stefanteitge.mtmx.core.file.raw.IRawPodFileEntry;
import de.stefanteitge.mtmx.core.tri.GameDirFactory;
import de.stefanteitge.mtmx.core.tri.ITriGameDir;

public class RawDecodeTest {

	private void testSkeleton(ITriGameDir gameDir) throws FileLoadException, FileStoreException {
		PodFile[] podFiles = gameDir.getPodFiles();

		for (PodFile podFile : podFiles) {
			IPodData podData = podFile.getData();

			IRawPodFileEntry[] rawEntries = podData.findEntries(IRawPodFileEntry.class);

			for (IRawPodFileEntry rawEntry : rawEntries) {
				IActPodFileEntry actEntry = gameDir.getActSearchStrategy().find(rawEntry);

				String message = "No ACT entry found for " + rawEntry.getPath() + " in " + podFile.getFile().getName() + " in " + gameDir.getGameName();
				Assert.assertNotNull(message, actEntry);
			}
		}
	}

	@Test
	public void testMtm2() throws FileLoadException, FileStoreException {
		ITriGameDir gameDir = GameDirFactory.create(new File(ITestConfig.MTM2_DIR));

		testSkeleton(gameDir);
	}
}
