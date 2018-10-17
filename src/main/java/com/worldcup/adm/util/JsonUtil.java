package com.worldcup.adm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtil {

	public static String obj2Json(Object object) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}

	public static <T> T json2Obj(String content, Class<T> valueType) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(content, valueType);
		} catch (IOException e) {
			log.error("JSON READ ERROR:{}",e);
		}
		return null;
	}
}
