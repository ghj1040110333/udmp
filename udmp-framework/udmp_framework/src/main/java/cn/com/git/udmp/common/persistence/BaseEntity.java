

package cn.com.git.udmp.common.persistence;

import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.common.persistence.entity.IBaseEntity;
import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.common.supcan.annotation.treelist.SupTreeList;
import cn.com.git.udmp.common.supcan.annotation.treelist.cols.SupCol;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.common.web.IUDMPContext;
import cn.com.git.udmp.core.config.Global;

/**
 * Entity支持类
 * @author Spring Cao
 * @version 2014-05-16
 */
@SupTreeList
public abstract class BaseEntity<T> extends DataObject implements IBaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 当前用户
	 */
	protected UserEntity currentUser;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;

	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getId()
	 */
	@Override
	@SupCol(isUnique="true", isHide="true")
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getCurrentUser()
	 */
	@Override
	@JsonIgnore
	@XmlTransient
	public UserEntity getCurrentUser() {
		if(currentUser == null){
		    IUDMPContext udmpContext = getBean(Global.UDMP_CONTEXT_NAME);
			currentUser = udmpContext.getUser();
		}
		return currentUser;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#setCurrentUser(cn.com.git.udmp.common.persistence.UserEntity)
	 */
	@Override
	public void setCurrentUser(UserEntity currentUser) {
		this.currentUser = currentUser;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getPage()
	 */
	@Override
	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#setPage(cn.com.git.udmp.common.persistence.Page)
	 */
	@Override
	public Page<T> setPage(Page<?> page) {
		this.page = (Page<T>) page;
		return this.page;
	}
	

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getSqlMap()
	 */
	@Override
	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#setSqlMap(java.util.Map)
	 */
	@Override
	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#preInsert()
	 */
	@Override
	public abstract void preInsert();
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#preUpdate()
	 */
	@Override
	public abstract void preUpdate();
	
    /* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getIsNewRecord()
	 */
	@Override
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#setIsNewRecord(boolean)
	 */
	@Override
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getGlobal()
	 */
	@Override
	@JsonIgnore
	public Global getGlobal() {
		return Global.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.IBaseEntity#getDbName()
	 */
	@Override
	@JsonIgnore
	public String getDbName(){
		return Global.getConfig("jdbc.type");
	}
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        IBaseEntity that = (IBaseEntity) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
    public static <T> T getBean(String name) {
        return (T)SpringContextHolder.getBean(name);
    }
    
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
}
