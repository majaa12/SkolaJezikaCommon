package rs.ac.bg.fon.nprog.common.transfer;

import java.io.Serializable;
import rs.ac.bg.fon.nprog.common.operation.Operation;

public class Request implements Serializable {

	private Operation operation;
	private Object argument;

	public Request() {
	}

	public Request(Operation operation, Object argument) {
		this.operation = operation;
		this.argument = argument;
	}

	public Object getArgument() {
		return argument;
	}

	public void setArgument(Object argument) {
		this.argument = argument;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
