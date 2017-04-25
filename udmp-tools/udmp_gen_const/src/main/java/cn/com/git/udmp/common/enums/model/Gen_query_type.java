
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Gen_query_type implements IType {
    ID_1("=","=","10"),
    ID_2("!=","!=","20"),
    ID_3("&gt;","&gt;","30"),
    ID_4("&lt;","&lt;","40"),
    ID_5("between","Between","50"),
    ID_6("like","Like","60"),
    ID_7("left_like","Left Like","70"),
    ID_8("right_like","Right Like","80");


    String id;
    String value;
    String seq;

    Gen_query_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Gen_query_type> map = new LinkedHashMap<String, Gen_query_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Gen_query_type> typeList = Arrays.asList(Gen_query_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Gen_query_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Gen_query_type(String... names) {
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

