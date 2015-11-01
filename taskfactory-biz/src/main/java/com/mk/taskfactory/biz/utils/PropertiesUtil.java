package com.mk.taskfactory.biz.utils;

import java.util.Properties;

public  class PropertiesUtil {
	public static String  getValue(String path,String key){
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
			if (cl == null) {
				// No thread context class loader -> use class loader of this class.
				cl = PropertiesUtil.class.getClassLoader();
			}
			
			Properties props = new Properties();
			props.load(cl.getResourceAsStream(path));
			return props.getProperty(key);
		} catch (Exception ex) {
			return null;
			// Cannot access thread context ClassLoader - falling back to system
			// class loader...
		}
		

	}
}
