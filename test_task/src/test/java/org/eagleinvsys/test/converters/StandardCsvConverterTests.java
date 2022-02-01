package org.eagleinvsys.test.converters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.ConvertibleMessageImpl;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Test;

class StandardCsvConverterTests {

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenOutputStreamIsNull() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		OutputStream actualStream = null;
		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.convert(collectionToConvert, actualStream);
		});
	}

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenInputListIsNull() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		OutputStream actualStream = new ByteArrayOutputStream();
		List<Map<String, String>> collectionToConvert = null;

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.convert(collectionToConvert, actualStream);
		});
	}

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenInputListIsEmpy() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		OutputStream actualStream = new ByteArrayOutputStream();
		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.convert(collectionToConvert, actualStream);
		});
	}

	@Test
	public void convert_shouldOutputCorrectResult_whenInputDataIsCorrect() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		OutputStream actualStream = new ByteArrayOutputStream();

		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		Map<String, String> record1 = new HashMap<String, String>();
		record1.put("number", "1");
		record1.put("last_name", "Ivanov");
		record1.put("first_name", "Ivan");

		Map<String, String> record2 = new HashMap<String, String>();
		record2.put("number", "2");
		record2.put("last_name", "Annovna");
		record2.put("first_name", "Anna");

		collectionToConvert.add(record1);
		collectionToConvert.add(record2);

		String expectedString = "number,last_name,first_name\n1,Ivanov,Ivan\n2,Annovna,Anna\n";

		standardCsvConverter.convert(collectionToConvert, actualStream);

		assertEquals(expectedString, actualStream.toString());
	}

	@Test
	public void validateSetOfKeys_shouldThrowIllegalArgumentException_whenInputListIsNull() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		List<Map<String, String>> collectionToConvert = null;

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.validateSetOfKeys(collectionToConvert);
		});
	}

	@Test
	public void validateSetOfKeys_shouldThrowIllegalArgumentException_whenInputListIsEmpy() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.validateSetOfKeys(collectionToConvert);
		});
	}

	@Test
	public void validateSetOfKeys_shouldReturnFalse_whenInputMapHasNotSameSetOfKeys() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		Map<String, String> record1 = new HashMap<String, String>();
		record1.put("number", "1");
		record1.put("last_name", "Ivanov");
		record1.put("first_name", "Ivan");

		Map<String, String> record2 = new HashMap<String, String>();
		record2.put("not_number", "2");
		record2.put("not_last_name", "Annovna");

		collectionToConvert.add(record1);
		collectionToConvert.add(record2);

		assertFalse(standardCsvConverter.validateSetOfKeys(collectionToConvert));
	}

	@Test
	public void validateSetOfKeys_shouldReturnTrue_whenInputMapHasSameSetOfKeys() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		Map<String, String> record1 = new HashMap<String, String>();
		record1.put("number", "1");
		record1.put("last_name", "Ivanov");
		record1.put("first_name", "Ivan");

		Map<String, String> record2 = new HashMap<String, String>();
		record2.put("number", "2");
		record2.put("last_name", "Annovna");
		record2.put("first_name", "Anna");

		collectionToConvert.add(record1);
		collectionToConvert.add(record2);

		assertTrue(standardCsvConverter.validateSetOfKeys(collectionToConvert));
	}

	@Test
	public void turnListIntoConvertibleCollection_shouldThrowIllegalArgumentException_whenInputListIsNull() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		List<Map<String, String>> collectionToConvert = null;

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.turnListIntoConvertibleCollection(collectionToConvert);
		});
	}

	@Test
	public void turnListIntoConvertibleCollection_shouldThrowIllegalArgumentException_whenInputListIsEmpy() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);
		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();

		assertThrows(IllegalArgumentException.class, () -> {
			standardCsvConverter.turnListIntoConvertibleCollection(collectionToConvert);
		});
	}

	@Test
	public void turnListIntoConvertibleCollection_shouldReturnCorrectConvertibleCollection_whenInputListIsCorect() {
		CsvConverter csvConverter = new CsvConverter();
		StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);

		List<Map<String, String>> collectionToConvert = new ArrayList<Map<String, String>>();
		Map<String, String> record1 = new HashMap<String, String>();
		record1.put("number", "1");
		record1.put("last_name", "Ivanov");
		record1.put("first_name", "Ivan");

		Map<String, String> record2 = new HashMap<String, String>();
		record2.put("number", "2");
		record2.put("last_name", "Annovna");
		record2.put("first_name", "Anna");

		collectionToConvert.add(record1);
		collectionToConvert.add(record2);

		ConvertibleCollection actualCollection = standardCsvConverter
				.turnListIntoConvertibleCollection(collectionToConvert);

		String expectedHeaders = "[number, last_name, first_name]";

		ArrayList<ConvertibleMessage> records = new ArrayList<ConvertibleMessage>();
		records.add(new ConvertibleMessageImpl(record1));
		records.add(new ConvertibleMessageImpl(record2));

		assertEquals(expectedHeaders, actualCollection.getHeaders().toString());

		ArrayList<ConvertibleMessage> actualRecords = (ArrayList<ConvertibleMessage>) actualCollection.getRecords();

		for (int i = 0; i < 2; i++) {
			ConvertibleMessage expectedConvertibleMessage = records.get(i);
			ConvertibleMessage actualConvertibleMessage = actualRecords.get(i);
			for (int j = 0; j < 3; j++) {
				String id = Integer.toString(j);
				String expectedValue = expectedConvertibleMessage.getElement(id);
				String actualValue = actualConvertibleMessage.getElement(id);
				assertEquals(expectedValue, actualValue);
			}
		}
	}

}