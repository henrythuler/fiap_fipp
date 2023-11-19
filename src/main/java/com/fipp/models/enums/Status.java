package com.fipp.models.enums;

public enum Status {
	PAID(0, "Pago"),
	INSTALLMENT(1, "Parcelado"),
	OVERDUE(2, "Atrasado"),
	CANCELLED(3, "Cancelado"),
	SCHEDULED(4, "Agendado");


	private final int id;
	private final String description;


	Status(int id, String description) {
		this.id = id;
		this.description = description;
	}


	public static Status valueById(int id) {
		for(Status s : Status.values()){
			if(s.getId() == id) return s;
		}
		return null;
	}


	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}