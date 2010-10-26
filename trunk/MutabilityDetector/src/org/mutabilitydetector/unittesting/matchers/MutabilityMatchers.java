/*
 * Mutability Detector
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Further licensing information for this project can be found in 
 * 		license/LICENSE.txt
 */

package org.mutabilitydetector.unittesting.matchers;

import static org.hamcrest.Matchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;
import org.mutabilitydetector.IAnalysisSession.AnalysisResult;
import org.mutabilitydetector.IAnalysisSession.IsImmutable;

public class MutabilityMatchers {

	public static AnalysisResultMatcher noWarningsAllowed() {
		return noWarningsAllowedMatcher();
	}

	public static AnalysisResultMatcher noWarningsAllowedMatcher() {
		return new NoWarningsAllowedMatcher();
	}
	
	private static class NoWarningsAllowedMatcher extends AnalysisResultMatcher {
		private Matcher<AnalysisResult> isAnything = not(IsAnything.<AnalysisResult>anything());
		@Override public void describeTo(Description description) {
			isAnything.describeTo(description);
		}
		@Override protected boolean matchesSafely(AnalysisResult item, Description mismatchDescription) {
			return isAnything.matches(item);
		}
	}
	

	public static Matcher<Class<?>> isImmutable() {
		return new ClassAnalysisResultMatcher(new IsImmutableMatcher(IsImmutable.DEFINITELY));
	}
}
