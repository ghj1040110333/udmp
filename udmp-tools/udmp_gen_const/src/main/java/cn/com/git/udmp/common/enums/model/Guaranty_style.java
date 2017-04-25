
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Guaranty_style implements IType {
    ID_1("3","抵押","20"),
    ID_2("2","保证","20"),
    ID_3("4","质押","20"),
    ID_4("1","信用","10");


    String id;
    String value;
    String seq;

    Guaranty_style(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Guaranty_style> map = new LinkedHashMap<String, Guaranty_style>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Guaranty_style> typeList = Arrays.asList(Guaranty_style.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Guaranty_style) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Guaranty_style(String... names) {
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

