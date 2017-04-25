
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Cms_show_modes implements IType {
    ID_1("0","默认展现方式","10"),
    ID_2("1","首栏目内容列表","20"),
    ID_3("2","栏目第一条内容","30");


    String id;
    String value;
    String seq;

    Cms_show_modes(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Cms_show_modes> map = new LinkedHashMap<String, Cms_show_modes>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Cms_show_modes> typeList = Arrays.asList(Cms_show_modes.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Cms_show_modes) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Cms_show_modes(String... names) {
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

