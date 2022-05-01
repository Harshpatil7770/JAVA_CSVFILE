package com.xoriant.delivery.validation;

import java.util.*;

public class InputValidation {

	private static Scanner sc = new Scanner(System.in);

	// Validation for user entered name
	public static String inputStringValidation() {
		while (!sc.hasNext("[A-Za-z]*")) {
			System.out.println("That's not an Alphabets!\n Please enter an Alphabets ::");
			sc.next();
		}
		String studentName = sc.next();
		System.out.println("Thank you! " + studentName + "\n");
		return studentName;
	}

	// Validation for user entered values
	public static int inputIntegerValidation() {
		int number;
		do {
			System.out.println("(Please enter a positive number) ::");
			while (!sc.hasNextInt()) {
				System.out.println("That's not a number!\n Please enter a positive number ::");
				sc.next(); // this is important!
			}
			number = sc.nextInt();
		} while (number <= 0);
		System.out.println("Thank you! " + number + "\n");
		return number;

	}

	public static double inputDoubleValidation() {
		double num;
		do {
			System.out.println("Please enter number ");
			while (!sc.hasNextDouble()) {
				System.out.println("That's not number \n Please enter number ");
				sc.next();
			}
			num = sc.nextDouble();
		} while (num <= 0);
		return num;
	}
}
