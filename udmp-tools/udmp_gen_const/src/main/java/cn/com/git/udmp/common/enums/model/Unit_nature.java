
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Unit_nature implements IType {
    ID_1("1","政府机构/社会团体/事业单位","10"),
    ID_2("6","境外上市股份制企业","60"),
    ID_3("5","境内上市股份制企业","50"),
    ID_4("4","三资企业","40"),
    ID_5("7","其它","70"),
    ID_6("3","民营企业","30"),
    ID_7("2","国营企业","20");


    String id;
    String value;
    String seq;

    Unit_nature(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Unit_nature> map = new LinkedHashMap<String, Unit_nature>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Unit_nature> typeList = Arrays.asList(Unit_nature.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Unit_nature) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Unit_nature(String... names) {
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

