package aima;


/**
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class DynamicAction extends ObjectWithDynamicAttributes implements
		Action {
	//changed the attribute name from name to stop to make it relevant to code
	public static final String ATTRIBUTE_NAME = "State";

	//

	public DynamicAction(String name) {
		this.setAttribute(ATTRIBUTE_NAME, name);
	}

	/**
	 * Returns the value of the name attribute.
	 * 
	 * @return the value of the name attribute.
	 */
	public String getName() {
		return (String) getAttribute(ATTRIBUTE_NAME);
	}

	//
	// START-Action
	public boolean isNoOp() {
		return false;
	}

	// END-Action
	//

	@Override
	public String describeType() {
		return Action.class.getSimpleName();
	}
}