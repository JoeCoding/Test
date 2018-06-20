/**
 * 
 */
var req;
function delectUser(){
	var chks = document.getElementsByName("chk");
	var flag = false;
	for(var i=0;i<chks.length;i++){
		if(chks[i].checked==true){
			flag=true;
			break;
		}
	}
	if(flag){
		//提交请求
		document.forms[0].action="delectUserServlet";
		document.forms[0].submit();
	}else{
		//提示
		alert("请至少选择一个用户进行删除");
	}
}

function exportUser(){
	document.forms[0].action="exportUserServlet";
	document.forms[0].submit();
}

/*
 * ajax做数据查询显示
 */
function ajaxSelect(){
	if(window.XMLHttpRequest){
		//非IE浏览器
		req = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		//IE浏览器
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(req){
		//打开到服务器的连接
		req.open("post","userManagerServlet?action=ajaxSelect",true);
		//设置请求的头信息
		req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//设定回调函数
		req.onreadystatechange=loadmore;
		//发送请求
		req.send();
	}
}

function loadmore(){
	
}

