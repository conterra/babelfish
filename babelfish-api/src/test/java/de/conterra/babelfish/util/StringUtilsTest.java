package de.conterra.babelfish.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
	@Test
	public void empty() {
		Assert.assertTrue(StringUtils.EMPTY.isEmpty());
	}
	
	@Test
	public void replaceAllNonAlphaNum() {
		Assert.assertEquals("HelloWorld", StringUtils.replaceAllNonAlphaNum("Hello World!", StringUtils.EMPTY));
		Assert.assertEquals("Hello_World_", StringUtils.replaceAllNonAlphaNum("Hello World!", "_"));
		Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", StringUtils.replaceAllNonAlphaNum("a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z", StringUtils.EMPTY));
		Assert.assertEquals("\u00E4\u00F6\u00FC\u00DF", StringUtils.replaceAllNonAlphaNum("\u00E4\u00F6\u00FC\u00DF", "_"));
		Assert.assertEquals("0123456789", StringUtils.replaceAllNonAlphaNum("0-1-2-3-4-5-6-7-8-9", StringUtils.EMPTY));
	}
}
