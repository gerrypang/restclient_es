package com.mjbmall.elasticsearch.core.until;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

class ImageURLSerialize extends JsonSerializer<String> implements ContextualSerializer {
	
    protected final String _prefix;
    
	public ImageURLSerialize() {
		this("");
	}

	public ImageURLSerialize(String p) {
		_prefix = p;
	}
	
    @Override
    public void serialize (String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    	String url = "";
        if (!StringUtils.isEmpty (value)) {
            url = AppConst.IMG_URL + value;
        }
        jgen.writeString (url);
    }

	public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
		if (beanProperty != null) { // 为空直接跳过
			ImageURL imageURL = null;
            if (Objects.equals (beanProperty.getType ().getRawClass (), String.class)) { // 非 String 类直接跳过
                imageURL = beanProperty.getAnnotation (ImageURL.class);
                if (imageURL == null) {
                    imageURL = beanProperty.getContextAnnotation (ImageURL.class);
                }
                if (imageURL != null) { // 如果能得到注解，就将注解的 value 传入 ImageURLSerialize
                    return new ImageURLSerialize (imageURL.value ());
                }
            }
            return serializerProvider.findValueSerializer (beanProperty.getType (), beanProperty);
        }
        return serializerProvider.findNullValueSerializer (beanProperty);
	}
}
