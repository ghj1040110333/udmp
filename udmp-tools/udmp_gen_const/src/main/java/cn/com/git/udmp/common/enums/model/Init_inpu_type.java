
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Init_inpu_type implements IType {
    ID_1("1","文本输入","10"),
    ID_2("4","日期段输入","40"),
    ID_3("2","下拉输入","20"),
    ID_4("3","单日期输入","30");


    String id;
    String value;
    String seq;

    Init_inpu_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Init_inpu_type> map = new LinkedHashMap<String, Init_inpu_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Init_inpu_type> typeList = Arrays.asList(Init_inpu_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Init_inpu_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Init_inpu_type(String... names) {
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

