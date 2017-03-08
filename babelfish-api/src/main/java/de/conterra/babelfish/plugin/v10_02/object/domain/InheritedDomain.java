package de.conterra.babelfish.plugin.v10_02.object.domain;

/**
 * defines a Inherited Domain
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class InheritedDomain
		extends DomainObject {
	@Override
	public String getType() {
		return "inherited";
	}
}