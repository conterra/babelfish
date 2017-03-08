package de.conterra.babelfish.plugin;

import java.lang.annotation.*;

/**
 * defines an {@link Annotation} to separate different API versions
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface InnerVersionWrapper {
	/**
	 * the {@link Class} of the {@link ServiceWrapper}, which handles version specific request
	 *
	 * @return the {@link Class} of the version specified request
	 *
	 * @since 0.1.0
	 */
	Class<? extends ServiceWrapper> value();
}