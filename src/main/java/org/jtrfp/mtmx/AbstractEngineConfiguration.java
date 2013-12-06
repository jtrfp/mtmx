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
package org.jtrfp.mtmx;

public abstract class AbstractEngineConfiguration {

	private Engine engine;

	public abstract void initLight();

	public abstract void initView();

	public abstract void draw();

	public void initKeyboard() {
	}

	public void destroy() {
	}

	public void init(Engine engine) {
		this.engine = engine;
	}

	protected Engine getEngine() {
		return engine;
	}

	public abstract void initResources() throws EngineException;
}