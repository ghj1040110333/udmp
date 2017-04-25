

package cn.com.git.udmp.tool;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.TypeUtils;
import cn.com.git.udmp.common.utils.FileUtils;
import cn.com.git.udmp.common.utils.FreeMarkers;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.modules.gen.entity.GenTemplate;
import cn.com.git.udmp.modules.gen.util.GenUtils;
import cn.com.git.udmp.tool.model.Code;
import cn.com.git.udmp.tool.model.Type;

/**
 * @description 生成当前工程的枚举常量类
 * @author liang
 *
 */
public class GenTypeModel implements ILog {

    /**
     * 生成文件
     * 
     * @description
     */
    public static void gen() {
        // 获取模板对象
        GenTemplate template = getTemplate();
        // 查询codetable结果集
        Map<String, Type> typeMap = Queryer.query();
        for (String key : typeMap.keySet()) {
            // 获取数据
            Map<String, Object> model = getDataModel(TypeUtils.convertToClassName(key), typeMap.get(key));
            // 生成文件
            genToFile(template, model, true);
        }
    }

    /**
     * @description 生成代码表的枚举对象
     */
    public static void genToFile(GenTemplate template, Map<String, Object> model, boolean isReplaceFile) {
        // 获取系统路径
        String packagePath = System.getProperty("user.dir");
        // 获取生成文件
        String fileName = packagePath + File.separator
                + StringUtils.replaceEach(FreeMarkers.renderString(template.getFilePath() + "/", model),
                        new String[] { "//", "/", "." },
                        new String[] { File.separator, File.separator, File.separator })
                + FreeMarkers.renderString(template.getFileName(), model);
        logger.debug(" fileName === " + fileName);

        // 获取生成文件内容
        String content = FreeMarkers.renderString(template.getContent(), model);
        logger.debug(" content === \r\n" + content);

        // 如果选择替换文件，则删除原文件
        if (isReplaceFile) {
            FileUtils.deleteFile(fileName);
        }

        // 创建并写入文件
        if (FileUtils.createFile(fileName)) {
            FileUtils.writeToFile(fileName, content, true);
            logger.debug(" file create === " + fileName);
        } else {
            logger.debug(" file extents === " + fileName);
        }
    }

    /**
     * @param key 类型的类名称
     * @param type 类型对象
     * @description 获取生成方案数据
     * @return 方案数据
     */
    private static Map<String, Object> getDataModel(String key, Type type) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("packageName", "cn.com.git.udmp");
        model.put("ClassName", key);
        model.put("typeMap", type.getCodeList());
        return model;
    }

    /**
     * @description 获取模板对象
     * @return 模板对象
     */
    private static GenTemplate getTemplate() {
        return GenUtils.fileToObject("/templates/", "enum.xml", GenTemplate.class);
    }

    public static void main(String[] args) {
        // FreeMarkers.renderString(templateString, model);
        // System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径

        // GenTemplate obj = GenUtils.fileToObject("/templates/",
        // "enum.xml",GenTemplate.class);
        // System.out.println(obj.getContent());

        GenTypeModel.gen();
    }
}

class Queryer {
    public static Map<String, Type> query() {
//        Map<String, List<Code>> map = new HashMap<>();
//        Code t1 = new Code();
//        t1.setId("1");
//        t1.setValue("test1");
//        t1.setSort("1");
//        Code t2 = new Code();
//        t2.setId("2");
//        t2.setValue("test2");
//        t2.setSort("2");
//        List<Code> value = Arrays.asList(t1, t2);
//        map.put("type1", value);
        // 从数据库中获取对象数据
        return getConnect();
    }
    
    public static Map<String, Type> getConnect(){
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
             con = DriverManager.getConnection("jdbc:oracle:thin:@10.100.3.54:1521:ncl","udmp","udmp");
            ResultSet resultSet = con.createStatement().executeQuery("SELECT A.TYPE,A.DESCRIPTION,A.VALUE,A.LABEL,A.SORT FROM UDMP.SYS_DICT A");
            Map<String, Type> map = new HashMap<String, Type>();
            while(resultSet.next()){
                String typeCode = resultSet.getString(1);
                String typeName= resultSet.getString(2);
                String codeId = resultSet.getString(3);
                String codeValue = resultSet.getString(4);
                String sort = resultSet.getString(5);
                
                if(map.get(typeCode)!=null){
                    Type oldType = map.get(typeCode);
                    List<Code> codeList = new ArrayList<Code>();
                    codeList.addAll(oldType.getCodeList());
                    codeList.add(new Code(codeId,codeValue,sort));
                    oldType.setCodeList(codeList);
                    map.put(typeCode, oldType);
                }else{
                    Type newType = new Type();
                    newType.setTypeCode(typeCode);
                    newType.setTypeName(typeName);
                    newType.setCodeList(Arrays.asList(new Code(codeId,codeValue,sort)));
                    map.put(typeCode, newType);
                }
            }
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
