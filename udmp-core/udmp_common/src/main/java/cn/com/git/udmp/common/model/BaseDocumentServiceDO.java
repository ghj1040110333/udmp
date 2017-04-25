

package cn.com.git.udmp.common.model;

/** 
 * 文档服务参数对象基类
 * @description 
 * @author Spring Cao
 * @date 2016年11月28日 上午10:59:11  
*/
public class BaseDocumentServiceDO extends DataObject{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量含义) 
    */ 
    private static final long serialVersionUID = 1L;
    
    /** 源文件路径 */
    private static final String SRC_F_PATH = "srcFilePath";
    /** 目标文件路径 */
    private static final String DEST_F_PATH = "destFilePath";
    
    /** 源文件名称(xxx.xx) */
    private static final String SRC_F_NAME = "srcFileName";
    /** 源文件扩展名 */
    private static final String SRC_F_EX_NAME = "srcFileExternalName";
    
    /** 目标文件名称(xxx.xx) */
    private static final String DEST_F_NAME = "destFileName";
    /** 目标文件扩展名 */
    private static final String DEST_F_EX_NAME = "destFileExternalName";
    
    /** 源文件编码(UTF-8) */
    private static final String SRC_F_ENCODE = "srcFileEncode";
    /** 目标文件编码(UTF-8) */
    private static final String DEST_F_ENCODE = "destFileEncode";
    
    private static final String DOC_DATA_PAYLOAD = "docDataPayload";
    
    /**
     * 
     * @title 获得源文件路径
     * @description
     * 
     * @return
     */
    public String getSrcFilePath(){
        return this.getString(SRC_F_PATH);
    }
    
    /**
     * 
     * @title 设置源文件路径
     * @description 必须包含文件名和扩展名的Absolute路径
     * 
     * @param srcFilePath
     */
    public void setSrcFilePath(String srcFilePath){
        this.setString(SRC_F_PATH, srcFilePath);
    }
    
    /**
     * 
     * @title 获得目标文件路径
     * @description
     * 
     * @return
     */
    public String getDestFilePath(){
        return this.getString(DEST_F_PATH);
    }
    
    /**
     * 
     * @title 设置目标文件路径
     * @description 必须包含文件名和扩展名的Absolute路径
     * 
     * @param destFilePath
     */
    public void setDestFilePath(String destFilePath){
        this.setString(DEST_F_PATH, destFilePath);
    }
    
    /**
     * 
     * @title 设置源文件名
     * @description 例如：xxx.xx
     * 
     * @param srcFileName
     */
    public void setSrcFileName(String srcFileName){
        this.setString(SRC_F_NAME, srcFileName);
    }
    
    /**
     * 
     * @title 获得源文件名
     * @description
     * 
     * @return
     */
    public String getSrcFileName(){
        return this.getString(SRC_F_NAME);
    }
    
    /**
     * 
     * @title 设置源文件扩展名
     * @description
     * 
     * @param srcFileExternalName
     */
    public void setSrcFileExternalName(String srcFileExternalName){
        this.setString(SRC_F_EX_NAME, srcFileExternalName);
    }
    
    /**
     * 
     * @title 获得源文件扩展名
     * @description
     * 
     * @return
     */
    public String getSrcFileExternalName(){
        return this.getString(SRC_F_EX_NAME);
    }
    
    /**
     * 
     * @title 设置目标文件名
     * @description 例如：xxx.xx
     * 
     * @param destFileName
     */
    public void setDestFileName(String destFileName){
        this.setString(DEST_F_NAME, destFileName);
    }
    
    /**
     * 
     * @title 获得目标文件名
     * @description
     * 
     * @return
     */
    public String getDestFileName(){
        return this.getString(DEST_F_NAME);
    }
    
    /**
     * 
     * @title 设置目标文件扩展名
     * @description
     * 
     * @param destFileExternalName
     */
    public void setDestFileExternalName(String destFileExternalName){
        this.setString(DEST_F_EX_NAME, destFileExternalName);
    }
    
    /**
     * 
     * @title 获得目标文件扩展名
     * @description
     * 
     * @return
     */
    public String getDestFileExternalName(){
        return this.getString(DEST_F_EX_NAME);
    }
    
    /**
     * 
     * @title 设置源文件编码
     * @description
     * 
     * @param srcFileEncode
     */
    public void setSrcFileEncode(String srcFileEncode){
        this.setString(SRC_F_ENCODE, srcFileEncode);
    }
    
    /**
     * 
     * @title 获得源文件编码
     * @description
     * 
     * @return
     */
    public String getSrcFileEncode(){
        return this.getString(SRC_F_ENCODE);
    }
    
    /**
     * 
     * @title 设置目标文件编码
     * @description
     * 
     * @param destFileEncode
     */
    public void setDestFileEncode(String destFileEncode){
        this.setString(DEST_F_ENCODE, destFileEncode);
    }
    
    /**
     * 
     * @title 获得目标文件编码
     * @description
     * 
     * @return
     */
    public String getDestFileEncode(){
        return this.getString(DEST_F_ENCODE);
    }
    
    /**
     * 
     * @title 设置文档输入输出参数的对象载体
     * @description 例如：设置一个pdf文档所需要的输入输出参数对象
     * 
     * @param data
     */
    public void setDocDataPayload(DataObject data){
        this.setDataObject(DOC_DATA_PAYLOAD,data);
    }
    
    /**
     * 
     * @title 获得文档输入输出参数的对象载体
     * @description
     * 
     * @return
     */
    public DataObject getDocDataPayload(){
        return this.getDataObject(DOC_DATA_PAYLOAD);
    }
}
