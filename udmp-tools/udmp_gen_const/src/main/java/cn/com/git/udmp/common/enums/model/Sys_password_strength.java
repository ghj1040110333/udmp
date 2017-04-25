
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Sys_password_strength implements IType {
    ID_1("1","用户密码强度","1"),
    ID_2("1","弱密码，6位数字","11"),
    ID_3("2","强密码，8位数字+字母","12");


    String id;
    String value;
    String seq;

    Sys_password_strength(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Sys_password_strength> map = new LinkedHashMap<String, Sys_password_strength>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Sys_password_strength> typeList = Arrays.asList(Sys_password_strength.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Sys_password_strength) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Sys_password_strength(String... names) {
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

