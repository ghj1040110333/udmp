
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Gen_category implements IType {
    ID_1("crud","增删改查","10"),
    ID_2("crud_many","增删改查（包含从表）","20"),
    ID_3("tree","树结构","30"),
    ID_4("dao","仅持久层","40");


    String id;
    String value;
    String seq;

    Gen_category(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Gen_category> map = new LinkedHashMap<String, Gen_category>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Gen_category> typeList = Arrays.asList(Gen_category.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Gen_category) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Gen_category(String... names) {
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

