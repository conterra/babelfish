package de.conterra.babelfish.sample.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.FieldType;
import de.conterra.babelfish.plugin.v10_02.feature.Table;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * defines a simple {@link Table} of the ESRI examples
 *
 * @author ChrissW-R1
 * @version 0.1.1
 * @since 0.1.0
 */
public class SimpleTable
		extends SimpleLayer<FeatureObject>
		implements Table<FeatureObject> {
	/**
	 * the con terra GmbH
	 *
	 * @since 0.1.1
	 */
	public static final Feature<FeatureObject> CON_TERRA;
	/**
	 * Esri Deutschland GmbH
	 *
	 * @since 0.1.1
	 */
	public static final Feature<FeatureObject> ESRI;
	/**
	 * Agentur f&uuml;r Arbeit M&uuml;nster
	 *
	 * @since 0.1.1
	 */
	public static final Feature<FeatureObject> AGENTUR_FUER_ARBEIT;
	
	static {
		Field address = new SimpleField("address", FieldType.String, StringUtils.EMPTY, false, 50, null);
		
		FeatureObject feature = new FeatureObject();
		feature.addAttribute(SimpleField.NAME_FIELD, "con terra - Gesellschaft für Angewandte Informationstechnologie mbH");
		feature.addAttribute(address, "Martin-Luther-King-Weg 24, D-48155 Münster, Germany");
		CON_TERRA = new SimpleFeature<>(feature);
		
		feature = new FeatureObject();
		feature.addAttribute(SimpleField.NAME_FIELD, "Esri Deutschland GmbH, Niederlassung Münster");
		feature.addAttribute(address, "Martin-Luther-King-Weg 20, D-48155 Münster, Germany");
		ESRI = new SimpleFeature<>(feature);
		
		feature = new FeatureObject();
		feature.addAttribute(SimpleField.NAME_FIELD, "Agentur für Arbeit Münster");
		feature.addAttribute(address, "Martin-Luther-King-Weg 22, D-48155 Münster, Germany");
		AGENTUR_FUER_ARBEIT = new SimpleFeature<>(feature);
	}
	
	/**
	 * constructor, with all necessary attributes
	 *
	 * @param id   the unique identifier
	 * @param name the name shown to the user
	 * @param desc the description
	 * @since 0.1.0
	 */
	public SimpleTable(int id, String name, String desc) {
		super(id, name, desc);
	}
	
	@Override
	public Set<? extends Feature<FeatureObject>> getFeatures() {
		Set<Feature<FeatureObject>> result = new LinkedHashSet<>();
		
		result.add(SimpleTable.CON_TERRA);
		result.add(SimpleTable.ESRI);
		result.add(SimpleTable.AGENTUR_FUER_ARBEIT);
		
		return result;
	}
}
