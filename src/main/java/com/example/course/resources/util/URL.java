package com.example.course.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	public static String decodeParam(String text) {
		//ou retorna ela decodificada
		try {
			return URLDecoder.decode(text, "UTF-8");
		}//ou retorna vazia, se der problema
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
