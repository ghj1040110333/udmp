
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum House_status implements IType {
    ID_1("5","集体宿舍","50"),
    ID_2("2","自有房（有贷款）","20"),
    ID_3("1","自有房（无贷款）","10"),
    ID_4("3","家族房","30"),
    ID_5("4","租赁房","40");


    String id;
    String value;
    String seq;

    House_status(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, House_status> map = new LinkedHashMap<String, House_status>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<House_status> typeList = Arrays.asList(House_status.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (House_status) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    House_status(String... names) {
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

