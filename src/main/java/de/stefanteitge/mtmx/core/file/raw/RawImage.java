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
package de.stefanteitge.mtmx.core.file.raw;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.stefanteitge.mtmx.core.file.FileLoadException;
import de.stefanteitge.mtmx.core.file.FileStoreException;
import de.stefanteitge.mtmx.core.file.act.ActColor;
import de.stefanteitge.mtmx.core.file.act.IActData;



public class RawImage {

	private final IActData colorTable;

	private final IRawData rawData;

	public RawImage(IRawData rawFile, IActData actFile) {
		colorTable = actFile;
		rawData = rawFile;
	}

	public IActData getActFile() {
		return colorTable;
	}

	public IRawData getRawFile() {
		return rawData;
	}

	public BufferedImage toImage() throws FileStoreException {
		BufferedImage outImg;
		try {
			outImg = new BufferedImage(
					rawData.getWidth(),
					rawData.getHeight(),
					BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < rawData.getWidth(); i++) {
				for (int j = 0; j < rawData.getHeight(); j++) {
					outImg.setRGB(j, i, getRgb(rawData.getValueAt(i, j)));
				}
			}
		} catch (FileLoadException e) {
			throw new FileStoreException(e);
		}

		return outImg;
	}

	public void storeAsBitmap(File file) throws FileStoreException {
		// TODO: check file
		try {
			ImageIO.write(toImage(), "bmp", file);
		} catch (IOException e) {
			throw new FileStoreException(e);
		}
	}

	private int getRgb(int value) throws FileLoadException {
		ActColor color = colorTable.getColor(value);
		return color.toRgb();
	}
}
