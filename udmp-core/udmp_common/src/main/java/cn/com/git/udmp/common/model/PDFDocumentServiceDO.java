

package cn.com.git.udmp.common.model;

/** 
 * PDF文档服务的参数对象父类
 * @description 
 * @author Spring Cao
 * @date 2016年11月28日 上午10:08:32  
*/
public class PDFDocumentServiceDO extends BaseDocumentServiceDO{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量含义) 
    */ 
    private static final long serialVersionUID = 1L;
    
    /** 文件加密类型(PdfWriter.STANDARD_ENCRYPTION_128 */
    private static final String F_ENCRYPTION_TYPE = "fileEncryptionType";
    /** 文件操作权限(PdfWriter.ALLOW_SCREENREADERS) */
    private static final String F_PERMISSION = "filePermission";
    /** 字体名称(STSong-Light) */
    private static final String FONT_NAME = "fontName";
    /** 字体字号 */
    private static final String FONT_SIZE = "fontSize";
    /** 字体样式 */
    private static final String FONT_STYLE = "fontStyle";
    /** 字体编码 */
    private static final String FONT_ENCODING = "fontEncoding";
    /** 是否字体嵌入(BaseFont.NOT_EMBEDDED) */
    private static final String FONT_EMBEDDED = "fontEmbedded";
    /** 图片文件名称(xxx.xx) */
    private static final String IMG_F_NAME = "imageFileName";
    /** 图片文件路径 */
    private static final String IMG_F_PATH = "imageFilePath";
    /** 图片文件扩展名 */
    private static final String IMG_F_EX_NAME = "imageFileExternalName";
    
    /**
     * 
     * @title 设置文档的加密类型
     * @description 例如：pdf文档可以使用，PdfWriter.STANDARD_ENCRYPTION_128
     * 
     * @param encryptionType
     */
    public void setFileEncryptionType(int encryptionType){
        this.setInt(F_ENCRYPTION_TYPE, encryptionType);
    }
    
    /**
     * 
     * @title 获得文档的加密类型
     * @description
     * 
     * @return
     */
    public int getFileEncryptionType(){
        return this.getInt(F_ENCRYPTION_TYPE);
    }
    
    /**
     * 
     * @title 设置文件操作权限
     * @description 例如：PdfWriter.ALLOW_SCREENREADERS
     * 
     * @param permission
     */
    public void setFilePermisson(int permission){
        this.setInt(F_PERMISSION, permission);
    }
    
    /**
     * 
     * @title 获得文件操作权限
     * @description
     * 
     * @return
     */
    public int getFilePermisson(){
        return this.getInt(F_PERMISSION);
    }
    
    /**
     * 
     * @title 设置字体名称
     * @description 例如：STSong-Light
     * 
     * @param fontName
     */
    public void setFontName(String fontName){
        this.setString(FONT_NAME, fontName);
    }
    
    /**
     * 
     * @title 获得字体名称
     * @description
     * 
     * @return
     */
    public String getFontName(){
        return this.getString(FONT_NAME);
    }

    /**
     * 
     * @title 设置字体的字号
     * @description 例如：12
     * 
     * @param fontSize
     */
    public void setFontSize(int fontSize){
        this.setInt(FONT_SIZE, fontSize);
    }
    
    /**
     * 
     * @title 获得字体的字号
     * @description
     * 
     * @return
     */
    public int getFontSize(){
        return this.getInt(FONT_SIZE);
    }
    
    /**
     * 
     * @title 设置字体的样式
     * @description 例如：Font.NORMAL
     * 
     * @param fontStyle
     */
    public void setFontStyle(int fontStyle){
        this.setInt(FONT_STYLE, fontStyle);
    }
    
    /**
     * 
     * @title 获取字体的样式
     * @description
     * 
     * @return
     */
    public int getFontStyle(){
        return this.getInt(FONT_STYLE);
    }
    
    /**
     * 
     * @title 设置字体的编码
     * @description 例如：UniGB-UCS2-H
     * 
     * @param fontEncoding
     */
    public void setFontEncoding(String fontEncoding){
        this.setString(FONT_ENCODING, fontEncoding);
    }
    
    /**
     * 
     * @title 获得字体的编码
     * @description
     * 
     * @return
     */
    public String getFontEncoding(){
        return this.getString(FONT_ENCODING);
    }
    
    /**
     * 
     * @title 设置字体是否嵌入
     * @description 例如：BaseFont.NOT_EMBEDDED 或 BaseFont.EMBEDDED
     * 
     * @param embedded
     */
    public void setFontEmbedded(int embedded){
        this.setInt(FONT_EMBEDDED, embedded);
    }
    
    /**
     * 
     * @title 获取字体是否嵌入
     * @description
     * 
     * @return
     */
    public int getFontEmbedded(){
        return this.getInt(FONT_EMBEDDED);
    }
    
    /**
     * 
     * @title 设置图片文件名称
     * @description 例如：文件名.扩展名
     * 
     * @param imageFileName
     */
    public void setImageFileName(String imageFileName){
        this.setString(IMG_F_NAME, imageFileName);
    }
    
    /**
     * 
     * @title 获得图片文件名
     * @description
     * 
     * @return
     */
    public String getImageFileName(){
        return this.getString(IMG_F_NAME);
    }
    
    /**
     * 
     * @title 设置图片文件路径
     * @description 必须包含件名和文件扩展名的Absolute全路径
     * 
     * @param imageFilePath
     */
    public void setImageFilePath(String imageFilePath){
        this.setString(IMG_F_PATH, imageFilePath);
    }
    
    /**
     * 
     * @title 获得图片文件路径
     * @description
     * 
     * @return
     */
    public String getImageFilePath(){
        return this.getString(IMG_F_PATH);
    }
    
    /**
     * 
     * @title 设置图片文件的扩展名
     * @description
     * 
     * @param imageFileExternalName
     */
    public void setImageFileExternalName(String imageFileExternalName){
        this.setString(IMG_F_EX_NAME, imageFileExternalName);
    }
    
    /**
     * 
     * @title 获得图片文件的扩展名
     * @description
     * 
     * @return
     */
    public String getImageFileExternalName(){
        return this.getString(IMG_F_EX_NAME);
    }
}
