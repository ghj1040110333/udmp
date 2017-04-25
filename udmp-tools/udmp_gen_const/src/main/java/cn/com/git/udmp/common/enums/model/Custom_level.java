
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Custom_level implements IType {
    ID_1("3","三星","30"),
    ID_2("7","七星","70"),
    ID_3("4","四星","40"),
    ID_4("6","六星","60"),
    ID_5("1","一星","10"),
    ID_6("5","五星","50"),
    ID_7("2","二星","20");


    String id;
    String value;
    String seq;

    Custom_level(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Custom_level> map = new LinkedHashMap<String, Custom_level>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Custom_level> typeList = Arrays.asList(Custom_level.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Custom_level) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Custom_level(String... names) {
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

