
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Schoolstyle implements IType {
    ID_1("4","四年制","40"),
    ID_2("2","二年制","20"),
    ID_3("6","六年制","60"),
    ID_4("3","三年制","30"),
    ID_5("5","五年制","50"),
    ID_6("8","八年制","80"),
    ID_7("7","七年制","70"),
    ID_8("1","一年制","10");


    String id;
    String value;
    String seq;

    Schoolstyle(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Schoolstyle> map = new LinkedHashMap<String, Schoolstyle>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Schoolstyle> typeList = Arrays.asList(Schoolstyle.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Schoolstyle) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Schoolstyle(String... names) {
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

