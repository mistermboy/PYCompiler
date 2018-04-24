package ast;

import tipo.Type;

public abstract class AbstractExpression implements Expression {

	private boolean lValue = false;
	private Type type;

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
	}

}