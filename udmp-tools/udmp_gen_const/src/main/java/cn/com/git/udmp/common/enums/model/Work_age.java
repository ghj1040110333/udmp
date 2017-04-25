
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Work_age implements IType {
    ID_1("3","1-3年","30"),
    ID_2("5","7-10年","50"),
    ID_3("1","0-3个月","10"),
    ID_4("4","4-6年","40"),
    ID_5("2","4-11个月","20"),
    ID_6("6","10年以上","60");


    String id;
    String value;
    String seq;

    Work_age(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Work_age> map = new LinkedHashMap<String, Work_age>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Work_age> typeList = Arrays.asList(Work_age.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Work_age) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Work_age(String... names) {
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

