/**
 * 
 */
package com.augmentum.google.util;

import java.io.File;

/**
 * @author Jason.Zhu
 * @date 2014-3-19
 * @email jasonzhu@augmentum.com.cn
 */
public class PathUtil {

	public static String getSrcPath() throws Exception{
		File directory = new File("");
		String courseFile = directory.getCanonicalPath();
		return courseFile + "/src/";
	}
	
}
