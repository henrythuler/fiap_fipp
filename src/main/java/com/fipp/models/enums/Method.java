
package com.fipp.models.enums;

public enum Method {
	DEBIT(0, "Débito"),
	CREDIT(1, "Crédito"),
	BOLETO(2, "Boleto"),
	PIX(3, "Pix"),
	CASH(4, "Dinheiro");


	private final int id;
	private final String description;


	Method(int id, String description) {
		this.id = id;
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public String getDescription() {
		return description;
	}


	public static Method valueById(int id) {
		for (Method m : Method.values()) {
			if (m.getId() == id) return m;
		}
		return null;
	}
	
}
