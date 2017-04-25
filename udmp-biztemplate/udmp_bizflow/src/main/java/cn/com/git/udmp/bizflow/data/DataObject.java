package cn.com.git.udmp.bizflow.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 *
 * 数据处理对象，流程的所有输入和输出必须依赖此对象
 * @author updated by Spring Cao
 *
 */
public class DataObject{
	private Map<String, Object> data = new HashMap<String, Object>();
	//@XmlJavaTypeAdapter(DefaultType.class)
	public Type type;

	public DataObject(){

	}

	public DataObject(Type type){
		this.type = type;
	}

	public Object get(String name){
		return data.get(name);
	}

	public void set(String name,Object value){
		data.put(name, value);
	}

	@SuppressWarnings("unchecked")
	public <T extends DataObject> T getDataObject(String name,Class<T> clazz){
		Object obj = data.get(name);
		if(obj == null){
			return null;
		}
		if(obj.getClass().equals(clazz)){
			return (T) obj;
		}else{
			T da = (T) BeanUtils.instantiateClass(clazz);
			if(obj instanceof DataObject){
				da.setData(((DataObject) obj).getData());
			}else if(obj instanceof Map){
				da.setData((Map<String, Object>) obj);
			}else{
				throw new RuntimeException(name+"类型转换出现错误,obj="+obj.getClass());
			}
			set(name, da);
			return da;
		}
	}

	public DataObject getDataObject(String name){
		Object obj = data.get(name);
		if(obj == null){
			return null;
		}

		if(obj instanceof DataObject){
			return (DataObject) obj;
		} else if (obj instanceof Map){
			DataObject d = new DataObject();
			d.setData((Map<String, Object>) obj);
			return d;
		}else{
			throw new RuntimeException(name+"类型转换出现错误,obj="+obj.getClass());
		}
	}

	public void setDataObject(String name,Object value){
		data.put(name, value);
	}

	public void setDataObject(String name,DataObject value){
		data.put(name, value);
	}

	public String getString(String name){
		return (String) data.get(name);
	}
	public void setString(String name,String value){
		data.put(name, value);
	}
	public Boolean getBoolean(String name){
		return (Boolean) get(name);
	}

	public void setBoolean(String name,Boolean value){
		set(name, value);
	}

	@SuppressWarnings("unchecked")
	public <T extends DataObject> List<T> getList(String name,Class<T> clazz){
		List<?> l = (List<?>) data.get(name);
		if(l ==null){
			return new ArrayList<T>();
		}
		List<T> r = new ArrayList<T>();
		if(l.size()>0){
			for(Object o : l){
				if(o.getClass().equals(clazz)){
					r= (List<T>) l;
					break;
				}else{
					T da = (T) BeanUtils.instantiateClass(clazz);
					if(o instanceof DataObject){
						da.setData(((DataObject) o).getData());
					}else if(o instanceof Map){
						da.setData((Map<String, Object>) o);
					}else{
						throw new RuntimeException(name+"类型转换出现错误,obj="+o.getClass());
					}
					r.add(da);
				}
			}
		}
		return r;

	}

	public void setList(String name,List<?> value){
		set(name, value);
	}

	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data.putAll(data);
	}

	public Collection<?> getValues(){
		return this.data.values();
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


}