
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Theme implements IType {
    ID_1("default","默认主题","10"),
    ID_2("cerulean","天蓝主题","20"),
    ID_3("readable","橙色主题","30"),
    ID_4("united","红色主题","40"),
    ID_5("flat","Flat主题","60");


    String id;
    String value;
    String seq;

    Theme(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Theme> map = new LinkedHashMap<String, Theme>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Theme> typeList = Arrays.asList(Theme.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Theme) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Theme(String... names) {
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

