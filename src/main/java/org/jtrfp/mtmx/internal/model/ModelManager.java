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
package org.jtrfp.mtmx.internal.model;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.bin.IBinData;
import org.jtrfp.jtrfp.bin.IBinPodFileEntry;
import org.jtrfp.jtrfp.pod.IPodFileEntry;
import org.jtrfp.jtrfp.pod.PodFile;
import org.jtrfp.mtmx.Engine;
import org.jtrfp.mtmx.EngineException;
import org.jtrfp.mtmx.ITextureManager;
import org.jtrfp.mtmx.model.IModel;
import org.jtrfp.mtmx.model.IModelManager;

public class ModelManager implements IModelManager {

	private final Engine engine;

	public ModelManager(Engine engine) {
		this.engine = engine;
	}

	@Override
	public IModel load(PodFile podFile, String modelPath) throws EngineException {
		System.out.println("Loading " + modelPath); // XXX
		Model model = new Model();
		try {
			IPodFileEntry entry = podFile.getData().findEntry(modelPath);
			if (entry == null || !(entry instanceof IBinPodFileEntry)) {
				throw new EngineException("Model '" + modelPath + " does not exist.");
			}

			IBinData binData = ((IBinPodFileEntry) entry).getData();
			model.setBinData(binData);

			ITextureManager tm = engine.getResourceManager().getTextureManager();
			for (String textureName : binData.getTextureNames()) {
				tm.load(podFile, textureName);

			}
		} catch (FileLoadException e) {
			throw new EngineException(e);
		}
		return model;
	}


}
