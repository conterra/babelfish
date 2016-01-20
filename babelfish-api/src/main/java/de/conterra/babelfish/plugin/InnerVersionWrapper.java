package de.conterra.babelfish.plugin;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * defines an {@link Annotation} to separate different API versions
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface InnerVersionWrapper
{
	/**
	 * the {@link Class} of the {@link ServiceWrapper}, which handles version
	 * specific request
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Class} of the version specified request
	 */
	Class<? extends ServiceWrapper> value();
}