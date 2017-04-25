

package cn.com.git.udmp.common.web;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.git.udmp.common.beanvalidator.BeanValidators;
import cn.com.git.udmp.common.utils.DateUtils;
import cn.com.git.udmp.core.base.IController;

/**
 * 控制器支持类
 * @description *Controller的基类，为其提供基础的操作，继承BaseDefaultExceptionHandleController，并实现IController接口，
 * @author Spring Cao
 * @date 2016年8月25日 下午4:30:27
 */
public abstract class BaseController extends BaseDefaultExceptionHandleController implements IController{

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;

	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;

	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @title 服务端参数有效性验证
	 * @description
	 * 
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 
	 * @title 服务端参数有效性验证，异常抛出
	 * @description
	 * 
	 * @param validator
	 * @param object
	 * @param groups
	 */
	protected void validateWithException(Validator validator, Object object, Class<?>... groups){
	    BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 
	 * @title 属性抽取及消息队列化
	 * @description
	 * 
	 * @param e ConstraintViolationException类型异常
	 * @param separator 分隔符号
	 * @return
	 */
	protected List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
	    return BeanValidators.extractPropertyAndMessageAsList(e, separator);
	}

	/**
	 * 
	 * @title 添加Model消息
	 * @description
	 * 
	 * @param model
	 * @param messages
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}

	/**
	 * 
	 * @title 添加Flash消息
	 * @description
	 * 
	 * @param redirectAttributes
	 * @param messages
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 
	 * @title 初始化数据绑定 
	 * @description 
	 *     1. 将所有传递进来的String进行HTML编码，防止XSS攻击 
	 *     2. 将字段中Date类型转换为String类型
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
	}

	/**
	 * 
	 * @title 移动端结果返回值对象
	 * @description 用于与厦门实验室的移动开发框架的通讯层报文接口对接
	 * 
	 * @param result
	 * @param message
	 * @param remark
	 * @return
	 */
	public MobileResultVO<Object> setResult(String result, String message, String remark) {
		MobileResultVO<Object> mobileResultVO = new MobileResultVO<Object>();
		mobileResultVO.setResult(result);
		mobileResultVO.setMessage(message);
		mobileResultVO.setRemark(remark);
		return mobileResultVO;
	}
}
