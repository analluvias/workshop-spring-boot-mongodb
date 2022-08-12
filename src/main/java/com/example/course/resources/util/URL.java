package com.example.course.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
	
	//tratando datas recebidas
	
	//Vamos receber uma string, mas tb teremos uma data padrão, caso a conversão falhe
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return defaultValue;
		}
	}
	
}
