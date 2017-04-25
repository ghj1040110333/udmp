
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Priod_rate implements IType {
    ID_1("0","天","5"),
    ID_2("1","周","10"),
    ID_3("4","双月","40"),
    ID_4("5","季","50"),
    ID_5("3","月","30"),
    ID_6("2","双周","20");


    String id;
    String value;
    String seq;

    Priod_rate(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Priod_rate> map = new LinkedHashMap<String, Priod_rate>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Priod_rate> typeList = Arrays.asList(Priod_rate.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Priod_rate) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Priod_rate(String... names) {
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

