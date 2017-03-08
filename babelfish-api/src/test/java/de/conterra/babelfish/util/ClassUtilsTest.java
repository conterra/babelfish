package de.conterra.babelfish.util;

import de.conterra.babelfish.plugin.ServiceNotAvailableException;
import de.conterra.babelfish.plugin.WrongRequestException;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_11.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_11.feature.Relationship;
import de.conterra.babelfish.plugin.v10_11.feature.Table;
import de.conterra.babelfish.plugin.v10_21.RestService;
import de.conterra.babelfish.plugin.v10_21.feature.FeatureService;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * tests the class {@link ClassUtils}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class ClassUtilsTest {
	/**
	 * Test method for {@link de.conterra.babelfish.util.ClassUtils#getSuperTypes(java.lang.Class)}.
	 */
	@Test
	public void testGetSuperTypes() {
		Set<? extends Class<?>> superTypes = ClassUtils.getSuperTypes(ServiceNotAvailableException.class);
		
		Assert.assertTrue(superTypes.contains(ExecutionException.class));
		Assert.assertTrue(superTypes.contains(Exception.class));
		Assert.assertTrue(superTypes.contains(Throwable.class));
		Assert.assertTrue(superTypes.contains(Object.class));
		
		Assert.assertTrue(superTypes.contains(Serializable.class));
		
		Assert.assertFalse(superTypes.contains(WrongRequestException.class));
		Assert.assertFalse(superTypes.contains(Feature.class));
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.util.ClassUtils#getSuperclasses(java.lang.Class)}.
	 */
	@Test
	public void testGetSuperclasses() {
		Set<? extends Class<?>> superClasses = ClassUtils.getSuperclasses(ServiceNotAvailableException.class);
		
		Assert.assertTrue(superClasses.contains(ExecutionException.class));
		Assert.assertTrue(superClasses.contains(Exception.class));
		Assert.assertTrue(superClasses.contains(Throwable.class));
		Assert.assertTrue(superClasses.contains(Object.class));
		
		Assert.assertFalse(superClasses.contains(Serializable.class));
		Assert.assertFalse(superClasses.contains(WrongRequestException.class));
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.util.ClassUtils#getRecursivlyInterfaces(java.lang.Class)}.
	 */
	@Test
	public void testGetRecursivlyInterfaces() {
		Set<? extends Class<?>> interfaces = ClassUtils.getRecursivlyInterfaces(FeatureService.class);
		
		Assert.assertTrue(interfaces.contains(de.conterra.babelfish.plugin.v10_11.feature.FeatureService.class));
		Assert.assertTrue(interfaces.contains(RestService.class));
		Assert.assertTrue(interfaces.contains(de.conterra.babelfish.plugin.v10_02.feature.FeatureService.class));
		Assert.assertTrue(interfaces.contains(de.conterra.babelfish.plugin.v10_11.RestService.class));
		Assert.assertTrue(interfaces.contains(de.conterra.babelfish.plugin.v10_02.RestService.class));
		Assert.assertTrue(interfaces.contains(de.conterra.babelfish.plugin.v9_3.RestService.class));
		Assert.assertTrue(interfaces.contains(de.conterra.babelfish.plugin.RestService.class));
		
		Assert.assertFalse(interfaces.contains(FeatureLayer.class));
		Assert.assertFalse(interfaces.contains(Table.class));
		Assert.assertFalse(interfaces.contains(Relationship.class));
	}
}