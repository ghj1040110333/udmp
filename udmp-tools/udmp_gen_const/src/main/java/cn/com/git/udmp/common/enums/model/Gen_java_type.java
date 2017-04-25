
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Gen_java_type implements IType {
    ID_1("java.util.Date","Date","50"),
    ID_2("cn.com.git.udmp.modules.sys.entity.User","User","60"),
    ID_3("cn.com.git.udmp.modules.sys.entity.Office","Office","70"),
    ID_4("cn.com.git.udmp.modules.sys.entity.Area","Area","80"),
    ID_5("Custom","Custom","90"),
    ID_6("String","String","10"),
    ID_7("Long","Long","20"),
    ID_8("Integer","Integer","30"),
    ID_9("Double","Double","40");


    String id;
    String value;
    String seq;

    Gen_java_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Gen_java_type> map = new LinkedHashMap<String, Gen_java_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Gen_java_type> typeList = Arrays.asList(Gen_java_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Gen_java_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Gen_java_type(String... names) {
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

