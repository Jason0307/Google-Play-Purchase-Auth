/**
 * 
 */
package com.augmentum.google.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.augmentum.google.generate.base.Attr;
import com.augmentum.google.generate.base.Finder;
import com.augmentum.google.generate.base.Model;

/**
 * @author Jason.Zhu
 * @date 2014-3-19
 * @email jasonzhu@augmentum.com.cn
 */
public class XMLUtil {

	public static void main(String[] args) throws Exception {
		
	}

	@SuppressWarnings("unchecked")
	public static BuilderModel readModelsFromXML() throws Exception {
		BuilderModel builderModel = new BuilderModel();
		List<Model> models = new ArrayList<Model>();
		SAXReader reader = new SAXReader();
		InputStream is = XMLUtil.class.getClassLoader().getResourceAsStream(
				"Models.xml");
		Document doc = reader.read(is);
		Element root = doc.getRootElement();
		String database = root.attributeValue("db");
		String packageName = root.attributeValue("package");
		List<Element> modelElements = root.elements("Model");
		if (!modelElements.isEmpty()) {
			for (Element modelElement : modelElements) {
				Model model = new Model();
				String modelName = modelElement.attributeValue("name");
				String modelTableName = modelElement
						.attributeValue("tableName");
				String modelPkName = null;
				
				//read the attrs of model
				List<Element> attrElements = modelElement.elements("Attr");
				List<Attr> attrs = new ArrayList<Attr>();
				List<Finder> finders = new ArrayList<Finder>();
				if (!attrElements.isEmpty()) {
					for (Element attrElement : attrElements) {
						Attr attr = new Attr();
						String attrName = attrElement.attributeValue("name");
						String attrType = attrElement.attributeValue("type");
						Attribute pkAttribute = attrElement.attribute("pk");
						Attribute dbSizeAttribute = attrElement
								.attribute("db-size");
						Attribute notNullAttribute = attrElement
								.attribute("not-null");
						boolean attrPk = (null == pkAttribute) ? false
								: Boolean.parseBoolean(pkAttribute
										.getStringValue());
						int attrDbSize = (null == dbSizeAttribute) ? ConstantsUtil.DEFAULT_DB_SIZE
								: Integer.parseInt(dbSizeAttribute
										.getStringValue());
						boolean attrNotNull = (null == notNullAttribute) ? false
								: Boolean.parseBoolean(notNullAttribute
										.getStringValue());
						attr.setName(attrName);
						attr.setType(attrType);
						attr.setPk(attrPk);
						attr.setDbSize(attrDbSize);
						attr.setNotNull(attrNotNull);
						if(attrPk){
							modelPkName = attr.getName();
						}
						attrs.add(attr);
					}
				}
				
				//read the finders of model
				List<Element> finderElements = modelElement.elements("Finder");
				if(!finderElements.isEmpty()){
					for(Element finderElement : finderElements){
						Finder finder = new Finder();
						String finderName = finderElement.attributeValue("name");
						String finderReturnType = finderElement.attributeValue("return_type");
						int returnType = (finderReturnType.equals(modelName)) ? ConstantsUtil.MODEL_TYPE : ConstantsUtil.LIST_TYPE;
						List<Attr> finderAttrs = new ArrayList<Attr>();
						List<Element> finderAttrElements = finderElement.elements("FinderAttr");
						if(!finderAttrElements.isEmpty()){
							for(Element finderAttrElement : finderAttrElements){
								String finderAttrName = finderAttrElement.attributeValue("name");
								Attr attr = findFromAttrs(attrs,finderAttrName);
								finderAttrs.add(attr);
							}
						}
						finder.setFinderName(finderName);
						finder.setReturnType(returnType);
						finder.setFinderAttrs(finderAttrs);
						finders.add(finder);
					}
				}
				
				model.setName(modelName);
				model.setTableName(modelTableName);
				model.setAttrs(attrs);
				model.setFinders(finders);
				model.setPkName(modelPkName);
				models.add(model);
			}
		}
		builderModel.setDb(database);
		builderModel.setPackageName(packageName);
		builderModel.setModels(models);
		return builderModel;
	}

	/**
	 * @param attrs
	 * @param finderAttrName
	 * @return
	 */
	private static Attr findFromAttrs(List<Attr> attrs, String finderAttrName) {
		if(!attrs.isEmpty()){
			for(Attr attr : attrs){
				if(finderAttrName.equals(attr.getName())){
					return attr;
				}
			}
		}
		return null;
	}

	/**
	 * @param models
	 * @param database
	 * @return
	 */
	public static String initTableSQL(List<Model> models, String database) {
		StringBuilder sb = new StringBuilder("USE `").append(database).append(
				"`;\n\n");
		if (!models.isEmpty()) {
			for (Model model : models) {
				sb.append("DROP TABLE IF EXISTS ").append(model.getTableName())
						.append(";\n").append("CREATE TABLE IF NOT EXISTS ")
						.append(model.getTableName()).append(" (\n");
				List<Attr> attrs = model.getAttrs();
				initTableColumns(sb, attrs);
			}
		}
		return sb.toString();
	}

	/**
	 * @param sb
	 * @param attrs
	 */
	private static void initTableColumns(StringBuilder sb, List<Attr> attrs) {
		String pkName = null;
		if (!attrs.isEmpty()) {
			for (Attr attr : attrs) {
				String attrType = attr.getType();
				String type = getDBType(attrType);
				if (attr.isPk()) {
					pkName = attr.getName();
					int pkSize = getPKSize(attrType);
					sb.append("`").append(attr.getName()).append("` ")
							.append(type).append("(").append(pkSize)
							.append(") NOT NULL AUTO_INCREMENT,\n");
				} else {
					sb.append("`").append(attr.getName()).append("` ")
							.append(type);
					if (ConstantsUtil.DB_TYPE_STRING.equals(attrType)) {
						sb.append("(").append(attr.getDbSize()).append(")");
					}
					if (attr.isNotNull()) {
						sb.append(" NOT NULL ");
					}
					sb.append(",\n");
				}
			}
			sb.append("PRIMARY KEY (`")
					.append(pkName)
					.append("`)\n")
					.append(") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;\n\n");
		}
	}

	/**
	 * @param type
	 * @return
	 */
	private static int getPKSize(String type) {
		int pkSize = 11;
		switch (type) {
		case ConstantsUtil.DB_TYPE_INTEGER:
			pkSize = 11;
			break;
		case ConstantsUtil.DB_TYPE_LONG:
			pkSize = 20;
			break;
		default:
			break;
		}
		return pkSize;
	}

	/**
	 * @param type
	 * @return
	 */
	private static String getDBType(String type) {
		String dbType = null;
		switch (type) {
		case ConstantsUtil.DB_TYPE_INTEGER:
			dbType = "INT";
			break;
		case ConstantsUtil.DB_TYPE_LONG:
			dbType = "BIGINT";
			break;
		case ConstantsUtil.DB_TYPE_STRING:
			dbType = "VARCHAR";
			break;
		case ConstantsUtil.DB_TYPE_DATE:
			dbType = "DATETIME";
			break;
		case ConstantsUtil.DB_TYPE_DOUBLE:
			dbType = "DOUBLE";
			break;
		case ConstantsUtil.DB_TYPE_FLOAT:
			dbType = "FLOAT";
			break;
		default:
			break;
		}
		return dbType;
	}
}
