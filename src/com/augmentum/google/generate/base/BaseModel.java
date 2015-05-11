/**
 * 
 */
package com.augmentum.google.generate.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author Jason.Zhu
 * @date 2014-3-20
 * @email jasonzhu@augmentum.com.cn
 */
public abstract class BaseModel<M extends BaseModel<?>> extends Model<M> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String tableName;
	private String LIST_SQL = null;
	private String QUERY_ATTR_SQL = null;

	@SuppressWarnings("unchecked")
	public BaseModel() {
		Class<M> modelClass = (Class<M>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		tableName = modelClass.getAnnotation(TableBind.class).tableName();
		LIST_SQL = "SELECT * FROM " + tableName;
		QUERY_ATTR_SQL = "SELECT * FROM " + tableName + " WHERE {0} = ?";
	}

	/**
	 * Find the unique record by the attr in the database
	 * 
	 * @param attr
	 * @param value
	 * @return
	 */
	public Record findByAttrUnique(String attr, String value) {
		Record record = Db.findFirst(
				MessageFormat.format(QUERY_ATTR_SQL, attr), value);
		return record;
	}
	
	public List<Record> findByAttrList(String attr,String value){
		List<Record> records = new ArrayList<Record>();
		records = Db.find(
				MessageFormat.format(QUERY_ATTR_SQL, attr), value);
		return records;
	}


	public List<Record> findByList() {
		List<Record> records = new ArrayList<Record>();
		records = Db.find(LIST_SQL);
		return records;
	}

	public Map<String,Object> getAttrs(){
		return super.getAttrs();
	}
	
	public abstract List<String> showAttrs();
}
