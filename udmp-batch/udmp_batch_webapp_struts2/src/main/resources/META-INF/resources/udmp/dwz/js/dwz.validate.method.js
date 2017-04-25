/**
 * @requires jquery.validate.js
 * @author ZhangHuihua@msn.com
 */
(function($){
	if ($.validator) {
		$.validator.addMethod("alphanumeric", function(value, element) {
			return this.optional(element) || /^\w+$/i.test(value);
		}, "Letters, numbers or underscores only please");
		
		$.validator.addMethod("lettersonly", function(value, element) {
			return this.optional(element) || /^[a-z]+$/i.test(value);
		}, "Letters only please"); 
		
		$.validator.addMethod("phone", function(value, element) {
			return this.optional(element) || /^[0-9 \(\)]{7,30}$/.test(value);
		}, "Please specify a valid phone number");
		
		$.validator.addMethod("postcode", function(value, element) {
			return this.optional(element) || /^[1-9][0-9]{5}$/.test(value);
		}, "Please specify a valid postcode");
		
		$.validator.addMethod("date", function(value, element) {
			value = value.replace(/\s+/g, "");
			if (String.prototype.parseDate){
				var $input = $(element);
				var pattern = $input.attr('dateFmt') || 'yyyy-MM-dd';
	
				return !$input.val() || $input.val().parseDate(pattern);
			} else {
				return this.optional(element) || value.match(/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/);
			}
		}, "Please enter a valid date.");
		
		
		/*自定义js函数验证
		 * <input type="text" name="xxx" customvalid="xxxFn(element)" title="xxx" />
		 */
		$.validator.addMethod("customvalid", function(value, element, params) {
			try{
				return eval('(' + params + ')');
			}catch(e){
				return false;
			}
		}, "Please fix this field.");
		//ex yp.huo
		//自定义银行卡号
		$.validator.addMethod("exbankcard", function(value, element) {
			return this.optional(element) ||isBankCard(value);
		},"Please specify a valid band card number");
		//自定义电话号码
		$.validator.addMethod("exphone", function(value, element) {
			return this.optional(element) ||/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{6,18})(-(\d{0,4}))?$/.test(value);
		},"Please specify a valie phone num");
		//自定义手机号码
		$.validator.addMethod("exmobile", function(value, element) {
			return this.optional(element) ||/^\d{11}$/.test(value);
		}, "Please specify a valid mobile num");
		//自定义字符校验/^([a-zA-Z])+([a-zA-Z0-9_]*)+$/
		$.validator.addMethod("exchar", function(value, element) {
			return this.optional(element) ||/^([a-zA-Z])+([a-zA-Z0-9_]*)+$/.test(value);
		}, "Please specify a valid char");
		//自定义身份证号码验证
		$.validator.addMethod("excerticode", function(value, element) {
			return this.optional(element) ||idCardNoUtil.checkIdCardNo(value);
		}, "Please specify a valid certicode");
		//自定义日期校验
        $.validator.addMethod("exdate", function(value, element) {
        	return this.optional(element) ||isRightDate(value);
 		}, "Please specify a valid date");
        //自定义最大值校验
        $.validator.addMethod("exmin", function( value, element, param ) {
			return this.optional(element) || parseInt(value) >= param;
		}, $.validator.format("Please enter a value greater than or equal to {0}."));
        //自定义最小值校验
        $.validator.addMethod("exmax", function( value, element, param ) {
			return this.optional(element) || parseInt(value) <= param;
		},$.validator.format("Please enter a value less than or equal to {0}."));
        //自定义范围校验
        $.validator.addMethod("exrange", function( value, element, param ) {
			return this.optional(element) ||parseInt(value) >= param[0] && parseInt(value) <= param[1];
		}, $.validator.format("Please enter a value between {0} and {1}."));
		$.validator.addClassRules({
			date: {date: true},
			alphanumeric: { alphanumeric: true },
			lettersonly: { lettersonly: true },
			phone: { phone: true },
			postcode: {postcode: true},
			
			exbankcard:{exbankcard:true},
			exmobile:{exmobile:true},
			excerticode:{excerticode:true},
			exdate:{exdate:true},
			exchar:{exchar:true},
			exmax:{exmax:true},
			exmin:{exmin:true},
			exrange:{exrange:true}
		});
		$.validator.setDefaults({errorElement:"span"});
		$.validator.autoCreateRanges = true;
		
	}

})(jQuery);