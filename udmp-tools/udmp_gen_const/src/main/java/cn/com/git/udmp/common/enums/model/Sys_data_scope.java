
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Sys_data_scope implements IType {
    ID_1("1","所有数据","10"),
    ID_2("2","所在公司及以下数据","20"),
    ID_3("3","所在公司数据","30"),
    ID_4("4","所在部门及以下数据","40"),
    ID_5("5","所在部门数据","50"),
    ID_6("8","仅本人数据","90"),
    ID_7("9","按明细设置","100");


    String id;
    String value;
    String seq;

    Sys_data_scope(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Sys_data_scope> map = new LinkedHashMap<String, Sys_data_scope>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Sys_data_scope> typeList = Arrays.asList(Sys_data_scope.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Sys_data_scope) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Sys_data_scope(String... names) {
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

