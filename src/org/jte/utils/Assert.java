package org.jte.utils;

/**
 *
 * 断言工具类,用于参数验证.
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved 
 * @Company: 北京福富软件有限公司 
 * @author 陈作朋 
 * @version 1.00.00, 2010-8-19 上午11:16:42
 * @history:
 * 
 */
public abstract class Assert {
	
	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz);</pre>
	 * @param object the object to check
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}
	
	/**
	 * Assert that an String is not blank .
	 * @param input the String to check
	 * @throws IllegalArgumentException if the object is blank
	 */
	public static void notBlank(String input){
		if(input==null||"".equals(input.trim())){
			throw new IllegalArgumentException("[Assertion failed] - this argument is required; it must not be blank");
		}
	}
	
}
