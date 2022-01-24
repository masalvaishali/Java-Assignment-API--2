package com.deloitte.netflix.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.deloitte.netflix.entity.Movie;

@Service
public class MovieService {

	private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/netflix_titles.csv";

	public List<Movie> getMovies(Integer numberOfRecordToFetch, String movieType, String country, String startDate,
			String endDate) {

		CSVParser csvParser = null;
		List<Movie> movies = new ArrayList<Movie>();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			int recordNo = 0;
			for (CSVRecord csvRecord : csvParser) {
				if (numberOfRecordToFetch != null) {
					if (loopCount >= numberOfRecordToFetch) {
						break;
					}
					// Accessing Values by Column Index
					String type = csvRecord.get(1);
					if (type.equals("TV Show")) {
						loopCount++;
						Movie movie = new Movie(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
								csvRecord.get(4), csvRecord.get(5), csvRecord.get(6), csvRecord.get(7),
								csvRecord.get(8), csvRecord.get(9), csvRecord.get(10), csvRecord.get(11));

						movies.add(movie);
					}

				}
				// for movies type is Horror
				if (movieType != null && movieType.equals("horror")) {
					// Accessing Values by Column Index
					String listed_in = csvRecord.get(10);
					if (listed_in.contains("Horror Movies")) {
						Movie movie = new Movie(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
								csvRecord.get(4), csvRecord.get(5), csvRecord.get(6), csvRecord.get(7),
								csvRecord.get(8), csvRecord.get(9), csvRecord.get(10), csvRecord.get(11));

						movies.add(movie);
					}
				}
				// for TV show = Country :India
				if (country != null && country.equals("India")) {
					// Accessing Values by Column Index
					String countryFromCSV = csvRecord.get(5);
					if (countryFromCSV.equals("India")) {
						Movie movie = new Movie(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
								csvRecord.get(4), csvRecord.get(5), csvRecord.get(6), csvRecord.get(7),
								csvRecord.get(8), csvRecord.get(9), csvRecord.get(10), csvRecord.get(11));

						movies.add(movie);
					}
				}
				//For TV shows : Start Date & End Date
				if (startDate != null && endDate != null) {
					Date startDateCSV = null;
					Date endDateCSV = null;
					Date formated_date_added = null;
					recordNo++;
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat format2 = new SimpleDateFormat("MMMM dd, yyyy");
					if (null != startDate && startDate.trim().length() > 0) {
						try {
							startDateCSV = format.parse(startDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (null != endDate && endDate.trim().length() > 0) {
						try {
							endDateCSV = format.parse(endDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					// Accessing Values by Column Index
					try {
						if (recordNo > 1) {
							String date_added = csvRecord.get(6);
							formated_date_added = format2.parse(date_added);
						}
					} catch (ParseException e1) {
						// e1.printStackTrace();
					}
					if ((recordNo > 1) && (formated_date_added != null)
							&& (formated_date_added.compareTo(startDateCSV) > 0)
							&& (formated_date_added.compareTo(endDateCSV) < 0)) {
						Movie movie = new Movie(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
								csvRecord.get(4), csvRecord.get(5), csvRecord.get(6), csvRecord.get(7),
								csvRecord.get(8), csvRecord.get(9), csvRecord.get(10), csvRecord.get(11));

						movies.add(movie);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);
		}
		return movies;
	}
	private static void closeCsvParser(CSVParser csvParser) {
		if (null != csvParser) {
			try {
				csvParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
