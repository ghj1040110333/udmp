<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="../webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="../webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
<script src="../webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
</head>
<body>
	<input type="button" value="send message" onclick="send()">
	<input type="button" value="subscribe" onclick="subscribe()">
</body>
<script type="text/javascript">
	var socket = new SockJS("/udmp_websocket_test/portfolio");
	var stompClient = Stomp.over(socket);
	var conn = function() {
		stompClient.connect({}, function(frame) {
			alert("conneted");
			subscribe();
		});
	};
	//连接websocket
	conn();
	var callback = function(message) {
		// called when the client receives a STOMP message from the server
		alert("call back");
		if (message.body) {
			alert("got message with body " + message.body)
		} else {
			alert("got empty message");
		}
	};
	var subscribe = function() {
		stompClient.subscribe("/topic/greeting", callback);
		stompClient.subscribe("/topic/price.stock", callback);
	};
	var send = function(){
// 		if (stompClient == null||stompClient.connected==false) {
// 			conn();
// 		}
		stompClient.send("/app/greeting", {
			priority : 9
		}, "Hello, STOMP");
	};
	
// 	var subscribeSingle =function(){
// 		stompClient.subscribeSingle("/queue/greeting");
// 	}
</script>

</html>