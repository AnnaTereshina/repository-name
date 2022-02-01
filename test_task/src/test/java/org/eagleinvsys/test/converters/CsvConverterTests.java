package org.eagleinvsys.test.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.ConvertibleMessageImpl;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CsvConverterTests {

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenCollectionToConvertIsNull() {
		CsvConverter csvConverter = new CsvConverter();
		ConvertibleCollection convertibleCollection = null;
		OutputStream actualStream = new ByteArrayOutputStream();

		assertThrows(IllegalArgumentException.class, () -> {
			csvConverter.convert(convertibleCollection, actualStream);
		});
	}

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenOutputStreamIsNull() {
		CsvConverter csvConverter = new CsvConverter();
		ConvertibleCollection convertibleCollectionMock = Mockito.mock(ConvertibleCollection.class);
		OutputStream actualStream = null;

		assertThrows(IllegalArgumentException.class, () -> {
			csvConverter.convert(convertibleCollectionMock, actualStream);
		});
	}

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenMethodGetHeadersReturnsNull() {
		CsvConverter csvConverter = new CsvConverter();
		ConvertibleCollection convertibleCollectionMock = Mockito.mock(ConvertibleCollection.class);
		OutputStream actualStream = new ByteArrayOutputStream();

		Mockito.when(convertibleCollectionMock.getHeaders()).thenReturn(null);
		Mockito.when(convertibleCollectionMock.getRecords()).thenReturn(null);

		assertThrows(IllegalArgumentException.class, () -> {
			csvConverter.convert(convertibleCollectionMock, actualStream);
		});

		Mockito.verify(convertibleCollectionMock, Mockito.times(1)).getHeaders();
		Mockito.verify(convertibleCollectionMock, Mockito.times(0)).getRecords();
	}

	@Test
	public void convert_shouldThrowIllegalArgumentException_whenMethodGetRecordsReturnsNull() {
		CsvConverter csvConverter = new CsvConverter();
		ConvertibleCollection convertibleCollectionMock = Mockito.mock(ConvertibleCollection.class);
		OutputStream actualStream = new ByteArrayOutputStream();

		Mockito.when(convertibleCollectionMock.getHeaders()).thenReturn(new ArrayList<String>());
		Mockito.when(convertibleCollectionMock.getRecords()).thenReturn(null);

		assertThrows(IllegalArgumentException.class, () -> {
			csvConverter.convert(convertibleCollectionMock, actualStream);
		});

		Mockito.verify(convertibleCollectionMock, Mockito.times(1)).getHeaders();
		Mockito.verify(convertibleCollectionMock, Mockito.times(1)).getRecords();
	}

	@Test
	public void convert_shouldOutputCorrectResult_whenInputDataIsCorrect() {
		CsvConverter csvConverter = new CsvConverter();
		ConvertibleCollection convertibleCollectionMock = Mockito.mock(ConvertibleCollection.class);
		OutputStream actualStream = new ByteArrayOutputStream();

		Collection<String> headers = new ArrayList<String>();
		headers.add("number");
		headers.add("last_name");
		headers.add("first_name");

		Map<String, String> record1 = new HashMap<String, String>();
		record1.put("number", "1");
		record1.put("last_name", "Ivanov");
		record1.put("first_name", "Ivan");

		Map<String, String> record2 = new HashMap<String, String>();
		record2.put("number", "2");
		record2.put("last_name", "Annovna");
		record2.put("first_name", "Anna");

		Iterable<ConvertibleMessage> records = new ArrayList<ConvertibleMessage>();
		((ArrayList<ConvertibleMessage>) records).add(new ConvertibleMessageImpl(record1));
		((ArrayList<ConvertibleMessage>) records).add(new ConvertibleMessageImpl(record2));

		Mockito.when(convertibleCollectionMock.getHeaders()).thenReturn(headers);
		Mockito.when(convertibleCollectionMock.getRecords()).thenReturn(records);

		String expectedString = "number,last_name,first_name\n1,Ivanov,Ivan\n2,Annovna,Anna\n";

		csvConverter.convert(convertibleCollectionMock, actualStream);

		assertEquals(expectedString, actualStream.toString());

		Mockito.verify(convertibleCollectionMock, Mockito.times(2)).getHeaders();
		Mockito.verify(convertibleCollectionMock, Mockito.times(2)).getRecords();
	}

}