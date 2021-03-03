package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:ss");

		System.out.println("Enter rental data");
		System.out.println("Car model:");
		String carModel = scan.nextLine();
		Vehicle vehicle = new Vehicle(carModel);
		System.out.println("Pickup (dd/MM/yyyy hh:ss):");
		Date start = sdf.parse(scan.nextLine());
		System.out.println("Return (dd/MM/yyyy hh:ss):");
		Date finish = sdf.parse(scan.nextLine());

		CarRental carRental = new CarRental(start, finish, vehicle);

		System.out.println("Enter price per hour:");
		double pricePerHour = scan.nextDouble();
		System.out.println("Enter price per day:");
		double pricePerDay = scan.nextDouble();

		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

		rentalService.processInvoice(carRental);

		System.out.println("INVOICE:");
		System.out.println("Basic payment:" + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax:" + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total payment:" + String.format("%.2f", carRental.getInvoice().getTotalPayment()));

		scan.close();
	}

}
