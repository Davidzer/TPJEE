package com.example.Tp1JEE;

import javax.persistence.*;

@Entity
public class Car extends Vehicule {

	private int numberOfSeats;


	public Car() {
	}

	public Car(String plateNumber, String marque, int seats) {
		super(plateNumber, marque);
		this.numberOfSeats = seats;

	}





	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@Override
	public String toString() {
		return "Cette à voiture à" + numberOfSeats + " places";
	}

}
