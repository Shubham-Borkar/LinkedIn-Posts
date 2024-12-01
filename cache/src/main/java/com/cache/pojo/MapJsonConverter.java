package com.cache.pojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MapJsonConverter implements AttributeConverter<Map<String, String>, String> {

	private static ObjectMapper mapper;

	static {
		// To avoid instantiating ObjectMapper again and again.
		mapper = new ObjectMapper();
	}

	@Override
	public String convertToDatabaseColumn(Map<String, String> data) {
		if (null == data) {
			// You may return null if you prefer that style
			return "{}";
		}

		try {
			return mapper.writeValueAsString(data);

		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting map to JSON", e);
		}
	}

	@Override
	public Map<String, String> convertToEntityAttribute(String s) {
		if (null == s) {
			// You may return null if you prefer that style
			return new HashMap<>();
		}

		try {
			return mapper.readValue(s, new TypeReference<Map<String, String>>() {
			});

		} catch (IOException e) {
			throw new IllegalArgumentException("Error converting JSON to map", e);
		}
	}
}






















/*
 	@Column(name = "parameters")
	@Convert(converter = MapJsonConverter.class)
	private Map<String, String> parameters;
	
	insert into sbsb_product (product_id,price,name,parameters)values(1,'2999.00','Watch','{"name": "Shubham", "RollNo": "45"}');

 * */
