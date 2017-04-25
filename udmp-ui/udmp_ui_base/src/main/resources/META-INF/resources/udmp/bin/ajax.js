/**
 * AJAX Helper方法
 */

function udmpAjax() {
  this.ajax = function(url, type, dataType, data, callback,contentType) {
    $.ajax({
      url: url,
      type: type,
      dataType: dataType,
      contentType: contentType||"application/x-www-form-urlencoded",
      data: data,
      success: function(data){
        if(typeof data === "string"){
          callback(data);
        }else{
          if(data.techHeader){
	          if(data.techHeader.bizResCd !== "00000"){
	            window.top.showInfo('error',data.techHeader.bizResText);
	          }else{
	            callback(data);
	          }
          }
          else if(data.success){
            callback(data);
          } else {
            window.top.showInfo('error',data.message);
          }
        }
      },
      error: function(xhr, errorType, error) {
        window.top.showInfo('error','错误类型为: ' + errorType +  ', 错误详情: ' + error)
      }
    })
  }
}
udmpAjax.prototype.get = function(url, data, callback) {
  this.ajax(url, 'GET', 'json', data, callback)
}

udmpAjax.prototype.post = function(url, data, callback) {
  this.ajax(url, 'POST', 'json', data, callback)
}

udmpAjax.prototype.postJson = function(url, data, callback) {
  this.ajax(url, 'POST', 'json', data, callback,"application/json;charset=utf-8")
}

udmpAjax.prototype.put = function(url, data, callback) {
  this.ajax(url, 'PUT', 'json', data, callback)
}

udmpAjax.prototype.delete = function(url, data, callback) {
  this.ajax(url, 'DELETE', 'json', data, callback)
}

udmpAjax.prototype.jsonp = function(url, data, callback) {
  this.ajax(url, 'GET', 'jsonp', data, callback)
}

udmpAjax.prototype.constructor = udmpAjax
