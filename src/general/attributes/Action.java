package general.attributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import general.actions.strategies.Strategy;
import general.actions.strategies.movement.NoMovement;
import general.fields.Field;
import general.fields.SelectionField;

/**
 * 
 * @author Alex
 *
 */
public abstract class Action extends Attribute<Strategy> {

	private Strategy previousStrategy;

	private ExtraParameterFactory factory;

	public Action(String name, Strategy value) {
		super(name, value);
		previousStrategy = getOptions().get(0);
	}

	public Action(String name) {
		this(name, new NoMovement(0));
	}

	public abstract List<Strategy> getOptions();

	@Override
	protected List<Field<?>> updateFields(Strategy value) {
		List<Field<?>> fields = new ArrayList<>();
		fields.add(new SelectionField<Strategy>("Strategy", value, getOptions()));
		// value.reInitialize();
		fields.addAll(addExtraParameters(value));
		// System.out.println(fields);
		return fields;
	}

	@Override
	protected Strategy updateValue(LinkedHashMap<String, Field<?>> oldFields) {
//		System.out.println(factory == null);
		if (factory == null)
			factory = new ExtraParameterFactory();
		Strategy strategy = oldFields.get("Strategy").getValue((Strategy.class));

		Collection<Field<?>> fields;
		if (previousStrategy != null && strategy != previousStrategy) {
			fields = updateFields(strategy);
			// System.out.println("extra update");
		} else {
			fields = oldFields.values();
		}
		strategy.setNumericalParameters(factory.generateNumericalMap(fields));
		strategy.setStringParameters(factory.generateStringMap(fields));

		previousStrategy = strategy;
		return strategy;
	}

	protected List<Field<?>> addExtraParameters(Strategy value) {
		List<Field<?>> extraFields = new ArrayList<Field<?>>();
		Map<String, Number> numericalParams = value.getNumericalParameters();
		// System.out.println(value.getStringRepresentation());
		// System.out.println("numParams for " + value.getStringRepresentation()
		// + "= " + numericalParams.values());
		Map<String, String> stringParams = value.getStringParameters();

		// TODO: Figure out why this is null when not instantiated here
		// factory = new ExtraParameterFactory();
//		System.out.println(factory == null);
		if (factory == null)
			factory = new ExtraParameterFactory();
		extraFields.addAll(factory.generateStringFields(stringParams));
		extraFields.addAll(factory.generateNumericalFields(numericalParams));

		// System.out.println("Extra fields for " +
		// value.getStringRepresentation() + "=" + extraFields);
		return extraFields;
	}

}