package de.conterra.babelfish.util;

import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * utility class of some function on {@link Class} objects
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ClassUtils
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private ClassUtils()
	{
	}
	
	/**
	 * gives all super {@link Class}es and interfaces of a {@link Class}
	 * 
	 * @since 0.1
	 * 		
	 * @param child the {@link Class} to get the super types of
	 * @return a {@link Set}, which contains all super {@link Class}es and
	 *         interfaces of <code>child</code>
	 */
	public static Set<? extends Class<?>> getSuperTypes(Class<?> child)
	{
		LinkedHashSet<Class<?>> result = new LinkedHashSet<>();
		
		if (child != null)
		{
			String className = child.getName();
			
			ClassUtils.LOGGER.debug("Adds all superclasses of the given class " + className + " to the result set.");
			Set<? extends Class<?>> superClasses = ClassUtils.getSuperclasses(child);
			result.addAll(ClassUtils.getSuperclasses(child));
			
			ClassUtils.LOGGER.debug("Adds all interfaces (recursively) of the given class " + className + " to the result list.");
			result.addAll(ClassUtils.getRecursivlyInterfaces(child));
			
			ClassUtils.LOGGER.debug("Adds all interfaces of all super classes to the result list.");
			for (Class<?> clazz : superClasses)
			{
				result.addAll(ClassUtils.getRecursivlyInterfaces(clazz));
			}
		}
		
		return result;
	}
	
	/**
	 * gives all super {@link Class}es (recursively) of a {@link Class}
	 * 
	 * @since 0.1
	 * 		
	 * @param child the {@link Class} to get the super {@link Class}es of
	 * @return a {@link Set}, which contains all super {@link Class}es of
	 *         <code>child</code>
	 */
	public static Set<? extends Class<?>> getSuperclasses(Class<?> child)
	{
		LinkedHashSet<Class<?>> result = new LinkedHashSet<>();
		
		if (child != null)
		{
			Class<?> superclass = child.getSuperclass();
			if (superclass != null)
			{
				String superclassName = superclass.getName();
				
				ClassUtils.LOGGER.debug("Adds superclass " + superclassName + " of the given class " + child.getName() + " to the result set.");
				result.add(superclass);
				ClassUtils.LOGGER.debug("Adds all superclasses (recursively) of the superclass " + superclassName + " to the result set.");
				result.addAll(ClassUtils.getSuperclasses(superclass));
			}
		}
		
		return result;
	}
	
	/**
	 * gives all interfaces (recursively) of a {@link Class}
	 * 
	 * @since 0.1
	 * 		
	 * @param child the {@link Class} to get all interfaces of
	 * @return a {@link Set}, which contains all interfaces of
	 *         <code>child</code>
	 */
	public static Set<? extends Class<?>> getRecursivlyInterfaces(Class<?> child)
	{
		LinkedHashSet<Class<?>> result = new LinkedHashSet<>();
		
		if (child != null)
		{
			for (Class<?> in : child.getInterfaces())
			{
				String interfaceName = in.getName();
				
				ClassUtils.LOGGER.debug("Adds the interface " + interfaceName + " to the result set.");
				result.add(in);
				ClassUtils.LOGGER.debug("Adds all interfaces of the interface " + interfaceName + " to the result set.");
				result.addAll(ClassUtils.getRecursivlyInterfaces(in));
			}
		}
		
		return result;
	}
}