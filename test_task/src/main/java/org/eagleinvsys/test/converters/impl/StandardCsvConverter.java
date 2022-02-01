package org.eagleinvsys.test.converters.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;
import org.eagleinvsys.test.converters.StandardConverter;

public class StandardCsvConverter implements StandardConverter {

	private final CsvConverter csvConverter;

	public StandardCsvConverter(CsvConverter csvConverter) {
		if (csvConverter == null) {
			throw new IllegalArgumentException("Caught IllegalArgumentException: input data must not be a null.");
		}
		this.csvConverter = csvConverter;
	}

	/**
	 * Converts given {@link List<Map>} to CSV and outputs result as a text to the
	 * provided {@link OutputStream}
	 *
	 * @param collectionToConvert collection to convert to CSV format. All maps must
	 *                            have the same set of keys
	 * @param outputStream        output stream to write CSV conversion result as
	 *                            text to
	 */
	@Override
	public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) {

		if (collectionToConvert == null || outputStream == null) {
			throw new IllegalArgumentException("Caught IllegalArgumentException: input data must not be a null.");
		}

		if (collectionToConvert.isEmpty()) {
			throw new IllegalArgumentException("Caught IllegalArgumentException: input data must not be empty.");
		}

		if (!validateSetOfKeys(collectionToConvert)) {
			throw new IllegalArgumentException(
					"Caught IllegalArgumentException: All maps must have the same set of keys.");
		}

		ConvertibleCollection turnedCollection = turnListIntoConvertibleCollection(collectionToConvert);

		csvConverter.convert(turnedCollection, outputStream);
	}

	public boolean validateSetOfKeys(List<Map<String, String>> collectionToConvert) {
		if (collectionToConvert == null || collectionToConvert.isEmpty()) {
			throw new IllegalArgumentException("Caught IllegalArgumentException: invalid input data.");
		}

		Set<String> correctKeys = collectionToConvert.get(0).keySet();
		for (Map<String, String> map : collectionToConvert) {
			if (!map.keySet().equals(correctKeys)) {
				return false;
			}
		}
		return true;
	}

	public ConvertibleCollection turnListIntoConvertibleCollection(List<Map<String, String>> collectionToConvert) {
		if (collectionToConvert == null || collectionToConvert.isEmpty()) {
			throw new IllegalArgumentException("Caught IllegalArgumentException: invalid input data.");
		}

		ConvertibleCollectionImpl convertibleCollectionImpl = new ConvertibleCollectionImpl();

		Set<String> headers = collectionToConvert.get(0).keySet();
		convertibleCollectionImpl.setHeaders(headers);

		Iterable<ConvertibleMessage> records = new ArrayList<ConvertibleMessage>();
		for (Map<String, String> map : collectionToConvert) {
			((ArrayList<ConvertibleMessage>) records).add(new ConvertibleMessageImpl(map));
		}

		convertibleCollectionImpl.setRecords(records);

		return convertibleCollectionImpl;
	}

}