
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Sys_certificate_type implements IType {
    ID_1("8","其他证件","8"),
    ID_2("5","护照","5"),
    ID_3("7","回乡证","7"),
    ID_4("6","台胞证","6"),
    ID_5("2","军官证","2"),
    ID_6("1","居民身份证","1"),
    ID_7("3","武警警官证","3"),
    ID_8("4","士兵证","4");


    String id;
    String value;
    String seq;

    Sys_certificate_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Sys_certificate_type> map = new LinkedHashMap<String, Sys_certificate_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Sys_certificate_type> typeList = Arrays.asList(Sys_certificate_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Sys_certificate_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Sys_certificate_type(String... names) {
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

