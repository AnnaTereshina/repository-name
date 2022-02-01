package org.eagleinvsys.test.converters.impl;

import java.util.HashMap;
import java.util.Map;

import org.eagleinvsys.test.converters.ConvertibleMessage;

public class ConvertibleMessageImpl implements ConvertibleMessage {
	Map<String, String> map = new HashMap<String, String>();

	public ConvertibleMessageImpl(Map<String, String> map) {
		super();
		this.map = map;
	}

	@Override
	public String getElement(String elementId) {
		return map.get(elementId);
	}

}
