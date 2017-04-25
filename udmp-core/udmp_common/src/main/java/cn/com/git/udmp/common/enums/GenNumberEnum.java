package cn.com.git.udmp.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author guosg
 *
 */
public enum GenNumberEnum implements PersistentEnum {
	
	
	CUSTID ("01", "custid", "客户顺序号"),
	PRODID ("02", "prodid", "产品顺序号"),
	CONTID ("03", "contid", "合同顺序号"),
	ACCOID ("04", "accoid", "账户顺序号"),
	SEQUID ("05", "sequid", "流水顺序号"),
	LOYAID ("06", "loyaid", "积分顺序号"),
	PLANID ("07", "planid", "还款顺序号"),
	COGUID ("08", "coguid", "合作顺序号");
	
	private String index;
	private String name;
	private String descript;
	private static Map<String, GenNumberEnum> map = new LinkedHashMap<String, GenNumberEnum>();
	private static Map<String, String> valMap = new LinkedHashMap<String, String>();
	private static boolean inited = false;
	
	
	private GenNumberEnum(String _index, String _name, String _desc) {
		index = _index;
		name = _name;
		descript = _desc;
	}

	/**
	 * 初始化方法
	 * 
	 */
	static {
		if (!inited) {
			GenNumberEnum[] ins = GenNumberEnum.values();
			for (int i = 0; i < ins.length; i++) {
				map.put(ins[i].getName(), ins[i]);
				valMap.put(ins[i].getName(), ins[i].getDescript());
			}
			inited = true;
		}
	}

	public String getDescript() {
		return this.descript;
	}

	public String getName() {
		return this.name;
	}

	public static GenNumberEnum getByName(String name) {
		return map.get(name);
	}

	public static GenNumberEnum getByDesc(String desc) {
		for (String key : valMap.keySet()) {
			String _desc = valMap.get(key);
			if (_desc.equalsIgnoreCase(desc))
				return map.get(key);
		}
		return null;
	}

	public static Map<String, String> getValMap() {
		Map<String, String> _valMap = new LinkedHashMap<String, String>();
		_valMap.putAll(valMap);
		return _valMap;
	}

	public static String getDictName() {
		return "IMGSize";
	}

	public String getIndex() {
		return this.index;
	}
	
	
}
