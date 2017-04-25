
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Linkman_relation implements IType {
    ID_1("1","父母","10"),
    ID_2("0","配偶","1"),
    ID_3("99","其他","990"),
    ID_4("5","同事","50"),
    ID_5("2","兄弟/姐妹","20"),
    ID_6("3","子女","30"),
    ID_7("6","朋友","60"),
    ID_8("4","同学","40");


    String id;
    String value;
    String seq;

    Linkman_relation(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Linkman_relation> map = new LinkedHashMap<String, Linkman_relation>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Linkman_relation> typeList = Arrays.asList(Linkman_relation.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Linkman_relation) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Linkman_relation(String... names) {
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

