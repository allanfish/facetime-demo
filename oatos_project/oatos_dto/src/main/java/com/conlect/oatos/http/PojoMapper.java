package com.conlect.oatos.http;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;

/**
 * 串行化/反串行化 POJO对象
 * 
 * @author 郭天良
 */
public class PojoMapper {

	// logger
	private static final Logger logger = Logger.getLogger(PojoMapper.class);
	//
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		/**
		 * GWT 2.4 AutoBean 要求Long与Date类型至json时转换成字符串（需用双引号 " 引用）
		 */
		SimpleModule module = new SimpleModule("oatos", new Version(1, 0, 0,
				null));
		module.addSerializer(Date.class, new DateSerializer());
		// module.addSerializer(Long.class, new LongSerializer());
		module.addDeserializer(Date.class, new DateDeserializer());
		mapper.registerModule(module);
	}

	public static ObjectMapper getInstance() {
		return mapper;
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> T fromJsonAsArray(String jArrayString,
			TypeReference<?> valueTypeRef) {
		try {
			return (T) mapper.readValue(jArrayString, valueTypeRef);
		} catch (IOException ex) {
			logger.error(ex);
		}

		return null;
	}

	public static <T> T fromJsonAsObject(String jObjectString,
			Class<T> pojoClass) {
		try {
			return mapper.readValue(jObjectString, pojoClass);
		} catch (IOException ex) {
			logger.error(ex);
		}

		return null;
	}

	public static String toJson(Object pojo) {
		try {
			return mapper.writeValueAsString(pojo);
		} catch (IOException ex) {
			logger.error(ex);
		}

		return null;
	}

	private static class DateSerializer extends JsonSerializer<Date> {
		@Override
		public void serialize(Date value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			String str = "" + value.getTime();
			jgen.writeString(str);
		}
	}

	private static class DateDeserializer extends JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String str = jp.getText();
			Long value = Long.parseLong(str);
			Date date = new Date(value);
			return date;
		}

	}

	private static class LongSerializer extends JsonSerializer<Long> {
		@Override
		public void serialize(Long value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			String str = "" + value.toString();
			jgen.writeString(str);
		}
	}
}
