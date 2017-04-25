package cn.git.flow.mapping;

import java.util.ArrayList;
import java.util.List;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.FlowSession;
import cn.com.git.udmp.bizflow.activity.ParamsStoreUtils;
import cn.com.git.udmp.bizflow.data.DataObject;
import cn.com.git.udmp.bizflow.mapping.MappingAction;
import cn.git.flow.mapping.helper.AddressInfo;
import cn.git.flow.mapping.helper.CustomerBaseInfo;
import cn.git.flow.mapping.helper.CustomerInfo;
import junit.framework.TestCase;

public class TestMappingProcessorMapping extends TestCase{
	
	
	/**
	 * 
	 */
	public void testGetCustomerBaseInfo(){
		DataObject info = buildCustomerInfo();
		MappingAction processor = new MappingAction();
		processor.setPath("classpath:cn\\git\\flow\\mapping\\helper\\getCustomerBaseInfo.xml");
		processor.initialise();
		
		FlowSession session = new FlowSession();
		FlowMessage message = new FlowMessage(session,info);
		ParamsStoreUtils.saveParam("start", message);
		
		processor.process(message);
		
		DataObject params = (DataObject) message.getPayload();
		
		DataObject obj = (DataObject) params.get("request");
		
		assertNotNull(obj);
		
		assertTrue(obj instanceof CustomerBaseInfo);
		
		assertEquals("baseInfoName", obj.get("name"));
		
	}
	
	/**
	 * ���list,ֻ��ȡcustomerInfo�е�list
	 */
	public void testGetAddressList(){
		DataObject info = buildCustomerInfo();
		MappingAction processor = new MappingAction();
		processor.setPath("classpath:cn\\git\\flow\\mapping\\helper\\getAddressInfoList.xml");
		processor.initialise();
		
		FlowSession session = new FlowSession();
		FlowMessage message = new FlowMessage(session,info);
		ParamsStoreUtils.saveParam("start", message);
		
		processor.process(message);
		
		DataObject params = (DataObject) message.getPayload();
		
		List<AddressInfo> obj =  (List<AddressInfo>) params.get("request");
		assertNotNull(obj);
		assertTrue(obj instanceof List);
		assertEquals(2, obj.size());
		AddressInfo addressInfo = obj.get(1);
		assertEquals("address2", addressInfo.getAddress());
	}
	
	public void testCustomerInfo(){
		DataObject info = buildCustomerInfo();
		MappingAction processor = new MappingAction();
		processor.setPath("classpath:cn\\git\\flow\\mapping\\helper\\getCustomerInfo.xml");
		processor.initialise();
		
		FlowSession session = new FlowSession();
		FlowMessage message = new FlowMessage(session,info);
		ParamsStoreUtils.saveParam("start", message);
		
		processor.process(message);
		
		DataObject params = (DataObject) message.getPayload();
		
		CustomerInfo ci =  (CustomerInfo) params.get("request");
		assertNotNull(ci);
		System.out.println(ci.get("testName"));
		List<AddressInfo> ais = ci.getAddressInfos();
		assertEquals(2, ais.size());
		AddressInfo addressInfo = ais.get(1);
		assertEquals("address2", addressInfo.getAddress());
		
		CustomerBaseInfo bi = ci.getCustomerBaseInfo();
		assertNotNull(bi);
		assertTrue(bi instanceof CustomerBaseInfo);
		assertEquals("baseInfoName", bi.get("name"));
	}
	
	private DataObject buildCustomerInfo(){
		DataObject info = new DataObject();
		info.set("ecifId", "ecifId");
		DataObject baseInfo = new DataObject();
		baseInfo.set("name","baseInfoName");
		info.set("customerBaseInfo",baseInfo);
		
		List<DataObject> addressInfos = new ArrayList<DataObject>();
		DataObject addressInfo = new DataObject();
		addressInfo.set("address","address1");
		addressInfos.add(addressInfo);
		
		addressInfo = new AddressInfo();
		addressInfo.set("address","address2");
		addressInfos.add(addressInfo);
		info.set("addressInfos",addressInfos);
		
		return info;
		
	}
	
}
