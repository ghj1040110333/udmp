

package cn.com.git.udmp.common.persistence;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.com.git.udmp.common.persistence.entity.DefaultUserEntity;
import cn.com.git.udmp.common.persistence.entity.IDataEntity;
import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.common.utils.IdGen;
import cn.com.git.udmp.common.web.IUDMPContext;
import cn.com.git.udmp.core.config.Global;

/**
 * 数据Entity类
 * @author Spring Cao
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> implements IDataEntity {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注
	protected DefaultUserEntity<?> createBy;	// 创建者
	protected Date createDate;	// 创建日期
	protected DefaultUserEntity<?> updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	
    IUDMPContext udmpContext = getBean(Global.UDMP_CONTEXT_NAME);
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity(String id) {
		super(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#preInsert()
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
		DefaultUserEntity<?> user = (DefaultUserEntity<?>) udmpContext.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#preUpdate()
	 */
	@Override
	public void preUpdate(){
		UserEntity user = udmpContext.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = (DefaultUserEntity<?>) user;
		}
		this.updateDate = new Date();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#getRemarks()
	 */
	@Override
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#setRemarks(java.lang.String)
	 */
	@Override
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#getCreateBy()
	 */
	@Override
	@JsonIgnore
	public DefaultUserEntity<?> getCreateBy() {
		return createBy;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#setCreateBy(cn.com.git.udmp.common.persistence.DefaultUserEntity<?>)
	 */
	@Override
	public void setCreateBy(DefaultUserEntity<?> createBy) {
		this.createBy = createBy;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#getCreateDate()
	 */
	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#getUpdateBy()
	 */
	@Override
	@JsonIgnore
	public DefaultUserEntity<?> getUpdateBy() {
		return updateBy;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#setUpdateBy(cn.com.git.udmp.common.persistence.DefaultUserEntity<?>)
	 */
	@Override
	public void setUpdateBy(DefaultUserEntity<?> updateBy) {
		this.updateBy = updateBy;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#getUpdateDate()
	 */
	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#setUpdateDate(java.util.Date)
	 */
	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#getDelFlag()
	 */
	@Override
	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IDataEntity#setDelFlag(java.lang.String)
	 */
	@Override
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
