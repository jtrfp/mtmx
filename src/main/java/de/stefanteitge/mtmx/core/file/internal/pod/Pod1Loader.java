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
package de.stefanteitge.mtmx.core.file.internal.pod;

import java.io.IOException;
import java.io.InputStream;

import de.stefanteitge.mtmx.core.file.internal.Util;
import de.stefanteitge.mtmx.core.file.internal.pod.structure.Pod1Structure;
import de.stefanteitge.mtmx.core.file.pod.PodDataFormat;
import de.stefanteitge.mtmx.core.file.pod.PodFile;

public class Pod1Loader extends AbstractPodLoader {

	private final PodData podData;

	private final int fileCount;

	public Pod1Loader(InputStream is, PodFile podFile, int fileCount) {
		super(is, podFile);
		this.fileCount = fileCount;
		podData = new PodData(podFile, PodDataFormat.POD1);
	}

	public static final long PREFIX_LENGTH = PodDataLoader.HEADER_LENGTH + Pod1Structure.COMMENT_LENGTH;

	@Override
	public PodData load() throws IOException {
		// comment
		byte[] commentBuffer = new byte[Pod1Structure.COMMENT_LENGTH];
		read(commentBuffer);
		podData.setComment(Util.makeString(commentBuffer));

		// read entries
		readEntries();

		return podData;
	}

	private void readEntries() throws IOException {
		long offset = PREFIX_LENGTH + (long) fileCount * Pod1Structure.FILE_INFO_LENGTH;
		byte[] fileBuffer = new byte[Pod1Structure.FILE_INFO_LENGTH];
		for (int i = 0; i < fileCount; i++) {
			read(fileBuffer);

			// compute values
			int pos0 = Util.unsignedByteToInt(fileBuffer[Pod1Structure.FILE_PATH_LENGTH]);
			int pos1 = Util.unsignedByteToInt(fileBuffer[Pod1Structure.FILE_PATH_LENGTH + 1]) * 256;
			int pos2 = Util.unsignedByteToInt(fileBuffer[Pod1Structure.FILE_PATH_LENGTH + 2]) * 65536;
			int size = pos0 + pos1 + pos2;
			int length = 0;
			for (int j = 0; j < Pod1Structure.FILE_PATH_LENGTH; j++) {
				if (fileBuffer[j] == 0) {
					break;
				}
				length++;
			}
			String path = new String(fileBuffer, 0, length);

			PodFileEntry pfei = createPodFileEntry(podData, path);

			// set values
			pfei.setIndex(i);
			pfei.setPath(path);
			pfei.setSize(size);
			pfei.setOffset(offset);
			offset += size;
			podData.addEntry(pfei);
		}
	}
}
