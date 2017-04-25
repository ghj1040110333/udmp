package cn.git.flow.helper;

public class TestLocalServiceBean {
	
	
	public TestLocalServiceResponser testService(TestLocalServiceRequester name){
		System.out.println("+++++++++++hello"+ name.getName());
		TestLocalServiceResponser responser = new TestLocalServiceResponser();
		responser.setName("hello world");
		return responser;
	}
	
	public TestLocalServiceResponser testService2(TestLocalServiceResponser responser){
		System.out.println("-----------------"+responser.getName());
		responser.setName("my god!");
		return responser;
	}
}
