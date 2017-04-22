package com.mjbmall.elasticsearch.core.until;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention (RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize (using = ImageURLSerialize.class)
public @interface ImageURL {
	public String value() default "";
}
