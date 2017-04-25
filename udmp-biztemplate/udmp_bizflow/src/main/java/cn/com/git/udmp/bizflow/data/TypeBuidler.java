package cn.com.git.udmp.bizflow.data;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.git.udmp.bizflow.utils.XmlUtils;

/**
 * 构建类型
 * @author updated by Spring Cao
 * @version v1.0 2013-4-28
 */
public class TypeBuidler {
	public Type build(InputStream in){
		Document doc = XmlUtils.read(in);
		Element root = doc.getRootElement();
		Type type = new DefaultType();
		if(root.elements().size()>0){
			type = buildType((Element) root.elements().get(0));
		}

		return type;
	}

	private Type buildType(Element el){
		DefaultType type = new DefaultType();
		type.setName(el.attributeValue("name"));
		String sclass = el.attributeValue("class");
		if(StringUtils.isBlank(sclass)){
			type.setInstanceClass(DataObject.class);
		}else{
			try {
				type.setInstanceClass(Class.forName(sclass));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e.getMessage(),e);
			}
		}

		type.setDataObject(true);
		for(Iterator<?> it = el.elementIterator();it.hasNext();){
			Element ce = (Element) it.next();
			if("element".equals(ce.getName())){
				Property property = buildProperty(ce);
				type.addProperty(property);
			}
		}
		return type;
	}

	private Property buildProperty(Element el){
		DefaultProperty p = new DefaultProperty();


		p.setName(el.attributeValue("name"));
		p.setValue(el.attributeValue("value"));
		String many = el.attributeValue("many");
		if(many!=null && "true".equals(many)){
			p.setMany(true);
		}
		if(el.elements().size()>0){
			for(Iterator<?> it = el.elementIterator();it.hasNext();){
				Element ce = (Element) it.next();
				if("complexType".equals(ce.getName())){
					p.setType(buildType(ce));
				}
			}
		}
		if(p.getType() == null){
			String type = el.attributeValue("type");
			if(StringUtils.isNotBlank(type)){
				p.setType(new DefaultType(type));
			}else{
				p.setType(new DefaultType("String"));
			}
		}
		return p;
	}
}
