/*
 *    Copyright (c) 2008-2011 Graham Allan
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.mutabilitydetector.locations;

import java.util.Collections;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Function;

/**
 * @author Graham Allan / Grundlefleck at gmail dot com
 */
@Immutable
public final class ClassNameConverter {

	public static final ClassNameConverter CONVERTER = new ClassNameConverter();
    public static final Function<String, String> TO_DOTTED_STRING = new Function<String, String>() {
		@Override public String apply(String input) { return CONVERTER.dotted(input); }
    };

	public String dotted(final String givenClassName) {
	    return Collections.singleton(givenClassName)
	            .map(input -> input.replaceAll("\\[+", "["))
	            .map(input -> input.startsWith("[L") ? input.replace("[L", "") : input)
	            .map(input -> input.endsWith(".class") ? input.replace(".class", "") : input)
	            .map(input -> input.replace(";", ""))
	            .map(input -> input.replace("/", "."))
	            .getFirst();
    }

}
