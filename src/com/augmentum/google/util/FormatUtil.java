package com.augmentum.google.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import com.jfinal.kit.PathKit;

import de.hunsicker.jalopy.Jalopy;
import de.hunsicker.jalopy.storage.Convention;
import de.hunsicker.jalopy.storage.ConventionKeys;
import de.hunsicker.jalopy.storage.Environment;

public class FormatUtil {
	static URL convention = null;
	static Jalopy jalopy = null;

	private static void init() throws Exception {
			convention =  new URL("file","",PathKit.getWebRootPath() + "/WEB-INF/convertion.xml");
			Jalopy.setConvention(convention);
			jalopy = new Jalopy();
	}

	public static void format(File file) throws Exception {
		if (jalopy == null)
			init();
		try {
			String name = file.getName();
			if (name.endsWith(".java")) {
				Environment env = Environment.getInstance();
				env.set("author", "Jason");
				env.set("fileName", file.getName());
				Convention convention = Convention.getInstance();
				String classMask = "/**\n"
						+ " * <a href=\"$fileName$.html\"><b><i>View Source</i></b></a>\n" + " *\n"
						+ " * @author $author$\n" + " *\n" + "*/";
				convention.put(ConventionKeys.COMMENT_JAVADOC_TEMPLATE_CLASS, env.interpolate(classMask));
				jalopy.setInput(file);
				jalopy.setOutput(file);
				jalopy.format();
			}
		} catch (FileNotFoundException e) {
			new Exception(e.getMessage());
		}
	}

	public static void format(String fileName) throws Exception {
		if (jalopy == null)
			init();
		if (fileName == null)
			return;
		if (!fileName.endsWith(".java"))
			return;
		File file = new File(fileName);
		if (!file.exists())
			return;

		try {
			jalopy.setInput(file);
			jalopy.setOutput(file);
			jalopy.format();
		} catch (FileNotFoundException e) {
			new Exception(e.getMessage());
		}
	}
	

}