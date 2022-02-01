package org.eagleinvsys.test.converters.impl;

import java.util.Collection;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class ConvertibleCollectionImpl implements ConvertibleCollection {
	Collection<String> headers;
	Iterable<ConvertibleMessage> records;

	@Override
	public Collection<String> getHeaders() {
		return headers;
	}

	@Override
	public Iterable<ConvertibleMessage> getRecords() {
		return records;
	}

	public void setHeaders(Collection<String> headers) {
		this.headers = headers;
	}

	public void setRecords(Iterable<ConvertibleMessage> records) {
		this.records = records;
	}

}
