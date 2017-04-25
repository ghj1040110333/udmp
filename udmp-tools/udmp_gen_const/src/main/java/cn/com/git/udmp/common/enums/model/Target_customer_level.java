
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Target_customer_level implements IType {
    ID_1("5","五星级","50"),
    ID_2("3","三星级","30"),
    ID_3("2","二星级","20"),
    ID_4("4","四星级","40"),
    ID_5("1","一星级","10"),
    ID_6("6","六星级","60"),
    ID_7("7","七星级","70");


    String id;
    String value;
    String seq;

    Target_customer_level(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Target_customer_level> map = new LinkedHashMap<String, Target_customer_level>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Target_customer_level> typeList = Arrays.asList(Target_customer_level.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Target_customer_level) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Target_customer_level(String... names) {
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

