package com.deloitte.netflix.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVParsing {

	// Method to handle option 1
	private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/netflix_titles.csv";

	public static void fun1() {

		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
			System.out.print("Please enter the number of records to fetch with type(TV Show) :   ");
			int numberOfRecordToFetch = sc.nextInt();
			for (CSVRecord csvRecord : csvParser) {
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				// Accessing Values by Column Index
				String type = csvRecord.get(1);
				if (type.equals("TV Show")) {
					loopCount++;
					int i = 0;
					for (i = 0; i < 11; i++) {
						System.out.print(csvRecord.get(i) + ",");

					}
					System.out.println(csvRecord.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);

		}

	}

	public static void fun2() {

		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
			System.out.print(
					"Please enter the number of records to fetch where listed_in contains 'Horror Movies'  :   ");
			int numberOfRecordToFetch = sc.nextInt();
			for (CSVRecord csvRecord : csvParser) {
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				// Accessing Values by Column Index
				String listed_in = csvRecord.get(10);
				if (listed_in.contains("Horror Movies")) {
					loopCount++;
					int i = 0;
					for (i = 0; i < 11; i++) {
						System.out.print(csvRecord.get(i) + ",");

					}
					System.out.println(csvRecord.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);

		}

	}

	public static void fun3() {

		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
			System.out
					.print("Please enter the number of records to fetch from type 'Movie' where country is 'India':  ");
			int numberOfRecordToFetch = sc.nextInt();
			for (CSVRecord csvRecord : csvParser) {
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				// Accessing Values by Column Index
				String type = csvRecord.get(1);
				String country = csvRecord.get(5);
				if (type.equals("Movie") && country.equals("India")) {
					loopCount++;
					int i = 0;
					for (i = 0; i < 11; i++) {
						System.out.print(csvRecord.get(i) + ",");

					}
					System.out.println(csvRecord.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);

		}

	}

	// Method to handle option 4 date_added

	public static void fun4() {
		System.out.println("You have opted for option 4 \n");

	}

	private static void closeCsvParser(CSVParser csvParser) {
		if (null != csvParser) {
			try {
				csvParser.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		char ch = 'y';
		int menuCh = 0;
		int exitStatus = 0;

		while (ch == 'y' || ch == 'Y') {
			// Display commands
			System.out.println("Please select any one of below options:\n");
			System.out.println("1. List the first n records where type: TV Show\n");
			System.out
					.println("2. List the first n records where listed_in: Horror Movies (may contain other values)\n");
			System.out.println("3. List the first n type: Movie where country: India\n");
			System.out.println("4. Sorting based on field date_added\n");
			System.out.println("5. For Exitn\n\n");
			System.out.print("Enter Your Choice:  ");
			Scanner sc = new Scanner(System.in);
			menuCh = sc.nextInt();
			// Switch case
			switch (menuCh) {
			case 1:
				fun1();
				break;
			case 2:
				fun2();
				break;
			case 3:
				fun3();
				break;
			case 4:
				fun4();
				break;
			case 5:
				exitStatus = 1;
				break;
			default:
				System.out.println("Wrong Choice\n");
				break;
			}
			// Checking exit status
			if (exitStatus == 1) {
				break;
			}

			// Asking from user
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.print("Do you want to continue??? Yes/No : ");

			// skip /n ie newline
			ch = sc.next().charAt(0);

		}

	}

}
