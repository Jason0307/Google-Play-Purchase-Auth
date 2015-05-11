/**
 * 
 */
package com.augmentum.google.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.bee.tl.core.BeeException;
import org.bee.tl.core.GroupTemplate;
import org.bee.tl.core.Template;

import com.augmentum.google.generate.base.Model;

/**
 * @author Jason.Zhu
 * @date 2014-3-19
 * @email jasonzhu@augmentum.com.cn
 */
public class GenerateBuilderUtil {

	public static void main(String[] args) throws Exception {
		GroupTemplate group = new GroupTemplate(new File(
				System.getProperty("user.dir") + "/WebContent/WEB-INF/templateroot"));
		Template template = group.getFileTemplate("/model.btl");
		Template template2 = group.getFileTemplate("/model_impl.btl");
		BuilderModel builderModel = XMLUtil.readModelsFromXML();
		List<Model> models = builderModel.getModels();
		String database = builderModel.getDb();
		String packageName = builderModel.getPackageName();
		generateModelFile(template, models, packageName);
		generateModelImplFile(template2, models, packageName);
		genrateSqlFile(models, database);

	}

	/**
	 * @param models
	 * @param database
	 * @throws Exception
	 * @throws IOException
	 */
	private static void genrateSqlFile(List<Model> models, String database)
			throws Exception, IOException {
		String sqlPath = System.getProperty("user.dir") + "/sql";
		File sqlDirectory = new File(sqlPath);
		if (!sqlDirectory.exists()) {
			sqlDirectory.mkdirs();
		}

		File sqlFile = new File(sqlPath + "/generate/tables.sql");
		if (!sqlFile.exists()) {
			sqlFile.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(sqlFile));
		String sql = XMLUtil.initTableSQL(models, database);
		bw.write(sql);
		bw.close();
	}

	/**
	 * @param template2
	 * @param models
	 * @param packageName
	 * @throws IOException
	 * @throws BeeException
	 * @throws Exception
	 */
	private static void generateModelImplFile(Template template2,
			List<Model> models, String packageName) throws IOException,
			BeeException, Exception {
		if (!models.isEmpty()) {
			for (Model model : models) {
				template2.set("model", model);
				String content = template2.getTextAsString();
				String filePath = PathUtil.getSrcPath()
						+ packageName.replace(".", "/");
				File directory = new File(filePath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				File modelFile = new File(filePath + "/" + model.getName()
						+ "Impl.java");
				if (!modelFile.exists()) {
					modelFile.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(
							modelFile));
					bw.write(content);
					bw.close();
				}
				FormatUtil.format(modelFile);
			}
		}
	}

	/**
	 * @param template
	 * @param models
	 * @param packageName
	 * @throws IOException
	 * @throws BeeException
	 * @throws Exception
	 */
	private static void generateModelFile(Template template,
			List<Model> models, String packageName) throws IOException,
			BeeException, Exception {
		if (!models.isEmpty()) {
			for (Model model : models) {
				template.set("model", model);
				String content = template.getTextAsString();
				String filePath = PathUtil.getSrcPath()
						+ packageName.replace(".", "/");
				File directory = new File(filePath);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				File modelImplFile = new File(filePath + "/" + model.getName()
						+ ".java");
				if (!modelImplFile.exists()) {
					modelImplFile.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(
							modelImplFile));
					bw.write(content);
					bw.close();
				}
				FormatUtil.format(modelImplFile);
			}
		}
	}
}
