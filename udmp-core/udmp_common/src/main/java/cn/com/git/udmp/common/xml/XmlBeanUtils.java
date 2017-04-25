package cn.com.git.udmp.common.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import cn.com.git.udmp.common.model.DataObject;


/**
 * xml和dataobject对象的转换工具类（针对"民生银行"对外报文的xml解析工具）
 * @author liang
 *
 */
public class XmlBeanUtils {
	
	/**
	 * 将dataobject转换为xml字符串
	 * @param data
	 * @return
	 */
	public static String genXml(DataObject data){
		return genXml("root", data);
	}
	
	/**
	 *  将dataobject转换为xml字符串
	 * @param rootName 根节点名称
	 * @param data
	 * @return
	 */
	public static String genXml(String rootName,DataObject data){
		Document doc = DocumentHelper.createDocument();
		Element root = new DefaultElement(rootName);
		doc.setRootElement(root);
		parseElement(root,data.getData());
		return XmlUtils.toString(doc, "UTF-8");
	}
	
	/**
	 * 往当前element节点放入数据值
	 * @param root
	 * @param data
	 */
	private static void parseElement(Element root,Map<String, Object> data) {
		for(String key:data.keySet()){
			Element element = new DefaultElement(key);
			if(data .get(key) instanceof String){
				element.setText((String) data.get(key));
			}else if(data.get(key) instanceof Map){
				parseElement(element,(Map<String, Object>) data.get(key));
			}else if(data.get(key) instanceof List){
				List objectList = (List) data.get(key);
				for(Object obj:objectList){
					//这里list的子集只能是map对象才能转换为xml
					if(obj instanceof Map){
						parseElement(element, (Map) obj);
					}
				}
			}
			root.add(element);
		}
	}
	
	
	/**
	 * @param xml 
	 * @return
	 * 解析xml为DataObject
	 */
	public static DataObject parse(String xml) {
		DataObject obj = new DataObject();

		Document doc = XmlUtils.read(xml);

		Element re = doc.getRootElement();
		if (re != null) {
			Map<String, Object> rm = parseMap(re);
			obj.setData(rm);
		}else{
			throw new RuntimeException("响应报文解析异常，没有response节点");
		}

		return obj;
	}
	
	/**
	 * XMLElement解析为Map
	 * 
	 * @param el
	 * @return
	 */
	private static Map<String, Object> parseMap(Element el) {
		Map<String,Object> map = new HashMap<String, Object>();
		//先判断如果是根节点的情况
		if(el.elements().isEmpty()){
			map.put(el.getName(), el.getText().trim());
		}
		//再判断如果是list节点的情况
		else{
			List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
			for(Iterator iterator = el.elementIterator();iterator.hasNext();){
				Element element = (Element) iterator.next();
				list.add(parseMap(element));
			}
			map.put(el.getName(), list);
		}
		return map;
	}
	
 	
	public static void main(String[] args) {
		//测试生成对象
		String xml = "<response> <sequence>1001803809710</sequence> <domain>850</domain> <type>S</type>  <error>  <error-code>000</error-code> </error> <result>   <user>   <user-id>0000000011</user-id>   <user-name>张三</user-name>   <user-sex>男</user-sex>   <user-birth>1980-05-12</user-birth>   <user-email>zhangsan@test.com</user-email>   <user-telephone>12345678</user-telephone>   <org-id></org-id>   <b-orgs>     <b-org>       <b-org-id></b-org-id>       <level></level>       <match-mark></match-mark>     </b-org>   </b-orgs>   <user-privilege>     <privilege-version></privilege-version>     <roles>       <role-numbers>3</role-numbers>     <role-id>1</role-id><role-id>2</role-id>  <role-id>3</role-id>     </roles>   </user-privilege>  </user> </result></response>";
		DataObject parse = XmlBeanUtils.parse(xml);
		System.out.println(parse.getData());
		
		//测试生成xml
		System.out.println(XmlBeanUtils.genXml(parse));
		
	}
}
