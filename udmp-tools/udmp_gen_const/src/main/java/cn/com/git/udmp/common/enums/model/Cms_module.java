
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Cms_module implements IType {
    ID_1("article","文章模型","10"),
    ID_2("picture","图片模型","20"),
    ID_3("download","下载模型","30"),
    ID_4("link","链接模型","40"),
    ID_5("special","专题模型","50");


    String id;
    String value;
    String seq;

    Cms_module(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Cms_module> map = new LinkedHashMap<String, Cms_module>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Cms_module> typeList = Arrays.asList(Cms_module.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Cms_module) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Cms_module(String... names) {
        id = names[0];
        value = names[1];
        seq = names[2];
    }

    @Override
    public Map<String, String> getType() {
        return valMap;
    }
    
    @Override
    public String getValueById(String id) {
        return valMap.get(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    @Override
    public String getIdByValue(String value) {
        for(String key:valMap.keySet()){
            if(value.equals(valMap.get(key))){
                return key;
            }
        }
        return null;
    }

}

