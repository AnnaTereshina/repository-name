package org.eagleinvsys.test.converters.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.StringJoiner;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvConverter implements Converter {

	private static final String NEWLINE = "\n";

	/**
	 * Converts given {@link ConvertibleCollection} to CSV and outputs result as a
	 * text to the provided {@link OutputStream}
	 *
	 * @param collectionToConvert collection to convert to CSV format
	 * @param outputStream        output stream to write CSV conversion result as
	 *                            text to
	 */

	@Override
	public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {

		if (collectionToConvert == null || outputStream == null) {
			throw new IllegalArgumentException("Caught IllegalArgumentException: input data must not be a null.");
		}

		if (collectionToConvert.getHeaders() == null || collectionToConvert.getRecords() == null) {
			throw new IllegalArgumentException(
					"Caught IllegalArgumentException: collectionToConvert must not return a null.");
		}

		Collection<String> headers = collectionToConvert.getHeaders();
		Iterable<ConvertibleMessage> records = collectionToConvert.getRecords();

		StringJoiner outputDataJoiner = new StringJoiner("");
		int columnsNumber = headers.size();
		int i = 0;
		for (String header : headers) {
			outputDataJoiner.add(header);
			i++;
			if (i < columnsNumber) {
				outputDataJoiner.add(",");
			}
		}

		outputDataJoiner.add(NEWLINE);

		int j = 0;
		for (ConvertibleMessage map : records) {
			for (String header : headers) {
				outputDataJoiner.add(map.getElement(header));
				j++;
				if (j < columnsNumber) {
					outputDataJoiner.add(",");
				}
			}
			j = 0;
			outputDataJoiner.add(NEWLINE);
		}

		byte[] byteOutputDataArray = outputDataJoiner.toString().getBytes();

		try {
			outputStream.write(byteOutputDataArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}