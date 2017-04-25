/**
 * Description: 工具库
 * @author Seadon
 * @date 2016年2月16日
 *
 */

idefine({
  //弹出窗口
  showInfo: function(type, msg){
    toastr.remove();
    toastr.options = {
      "progressBar": true,
      "positionClass": "toast-bottom-center",
      "timeOut": "15000"
    }
    toastr[type](msg);
    toastr.options = {
      "progressBar": false,
      "positionClass": "toast-top-right",
    }
  },
  showBase: function(title,text,type,confirmtext,canceltext,showconfirm,showcancel,isconfirm,iscancel,callback,error,timer){
    //type: primary info success warning danger
    swal({
      title: title,
      text: text,
      type: type,
      showConfirmButton: showconfirm,
      showCancelButton: showcancel,
      confirmButtonText: '确定',
      confirmButtonClass: "btn-danger",
      confirmButtonText: confirmtext,
      cancelButtonText: canceltext,
      closeOnConfirm: isconfirm,
      closeOnCancel: iscancel,
      showLoaderOnConfirm: true,
      timer: timer
    },function(isConfirm){
      if(isConfirm){
    	if(callback) callback();
      } else {
        if(error) error();
      }
    })
  },
  showConfirm: function(title,callback){
    this.showBase(title,"删除功能，请谨慎操作！","warning","确认删除","取消",true,true,false,true,callback,function(){console.warn('取消删除操作')},null);
  },
  hideConfirm: function(){
	swal.close();
  },
  showMsg: function(type,title,callback){
    this.showBase(title,"",type,"","",true,false,true,true,callback||null,null,3000);
  },
  showPrompt: function(type,title,callback){
    bootbox.prompt({
      title: title,
      inputType: type,
      callback: callback
    })
  },
  //字符串过长处理
  abbr: function(str, length) {
    if (str && str.length > length) {
      str = str.substr(0, length) + "...";
    }
    return str;
  },
  //清除最后出现字符
  clearEndStr: function(str, flag) {
    var i_reg = eval("/" + flag + "$/gi");
    str = str.replace(i_reg, "");
    return str;
  },
  //获取当前日期
  getToday: function() {
    var now = new Date(),
      date = now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
    return date;
  },
  //将form表单元素的值序列化成对象
  serializeObject: function($form) {
    var o = {}, that = this;
    jQuery.each($form.serializeArray(), function(index) {
      var name = this['name'],
        value = this['value'];
      if (o[name]) {
        if(name.indexOf('>')===-1){
          o[name] = o[name] + "," + value;
        }
      } else {
        if(name.indexOf('>')!==-1){
          var arr = name.split('>');
          that.nameRecursion(o,arr,value,0);
        } else{
          o[name] = value;
        }
      }
    });
    return o;
  },
  //递归name属性
  nameRecursion: function(obj,arr,value,num){
    var length = arr.length;
    if(num == length-1){
      return obj[arr[num]] = value;
    }else{
      obj[arr[num]] = obj[arr[num]] ? obj[arr[num]] : {};
      return arguments.callee(obj[arr[num]],arr,value,num+1);
    }
  },
  //获取URL地址参数
  getQueryString: function(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == "") {
      url = window.location.search;
    } else {
      url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null) return unescape(r[2]);
    return null;
  }
});
