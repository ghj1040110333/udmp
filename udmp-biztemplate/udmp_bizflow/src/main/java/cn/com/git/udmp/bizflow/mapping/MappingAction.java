package cn.com.git.udmp.bizflow.mapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.action.Action;
import cn.com.git.udmp.bizflow.activity.ParamsStoreUtils;
import cn.com.git.udmp.bizflow.data.DataObject;
import cn.com.git.udmp.bizflow.data.Type;
import cn.com.git.udmp.bizflow.data.TypeBuidler;
import cn.com.git.udmp.bizflow.lifecycle.Initialisable;

/**
 * 参数映射活动
 * TODO 此对象还不完善
 * @author updated by Spring Cao
 *
 */
public class MappingAction extends Action implements Initialisable{

	Type type = null;

	String path = null;
	TypeBuidler builder = new TypeBuidler();
	MappingHandler mapping = new MappingHandler();

	public FlowMessage process(FlowMessage flowMessage) {

		ValueObject vo = new ValueObject();
		vo.setCur(flowMessage.getPayload());
		vo.setParamsStore(ParamsStoreUtils.getStore(flowMessage.getSession()));
		vo.setSession(flowMessage.getSession());

		DataObject data = mapping.mapping(type, vo);

		flowMessage.setPayload(data);
		return flowMessage;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public void initialise() {
		try {

			File file = ResourceUtils.getFile(path);
			type = builder.build(new FileInputStream(file));

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}


}
