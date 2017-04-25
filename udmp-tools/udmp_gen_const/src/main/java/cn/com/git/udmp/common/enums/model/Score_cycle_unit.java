
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Score_cycle_unit implements IType {
    ID_1("1","天","10"),
    ID_2("4","年","40"),
    ID_3("2","周","20"),
    ID_4("3","月","30");


    String id;
    String value;
    String seq;

    Score_cycle_unit(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Score_cycle_unit> map = new LinkedHashMap<String, Score_cycle_unit>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Score_cycle_unit> typeList = Arrays.asList(Score_cycle_unit.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Score_cycle_unit) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Score_cycle_unit(String... names) {
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

