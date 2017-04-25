
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Edu_level implements IType {
    ID_1("1","小学","10"),
    ID_2("6","硕士研究生","60"),
    ID_3("3","高中/中专/职业高中","30"),
    ID_4("5","大学本科","50"),
    ID_5("4","大学专科","40"),
    ID_6("99","其他","990"),
    ID_7("7","博士研究生","70"),
    ID_8("2","初中","20");


    String id;
    String value;
    String seq;

    Edu_level(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Edu_level> map = new LinkedHashMap<String, Edu_level>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Edu_level> typeList = Arrays.asList(Edu_level.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Edu_level) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Edu_level(String... names) {
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

