
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum CLOSE_START_CD implements IType {
    ID_1("01","3天","1"),
    ID_2("03","一个月","3"),
    ID_3("05","半年","5"),
    ID_4("06","一年","6"),
    ID_5("02","一周","2"),
    ID_6("04","季度","4"),
    ID_7("07","永远","7");


    String id;
    String value;
    String seq;

    CLOSE_START_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, CLOSE_START_CD> map = new LinkedHashMap<String, CLOSE_START_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<CLOSE_START_CD> typeList = Arrays.asList(CLOSE_START_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),   (CLOSE_START_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    CLOSE_START_CD(String... names) {
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

