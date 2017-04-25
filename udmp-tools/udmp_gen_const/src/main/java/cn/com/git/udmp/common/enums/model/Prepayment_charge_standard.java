
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Prepayment_charge_standard implements IType {
    ID_1("3","待偿还金额","30"),
    ID_2("2","提前还款金额","20"),
    ID_3("1","借款全额","10");


    String id;
    String value;
    String seq;

    Prepayment_charge_standard(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Prepayment_charge_standard> map = new LinkedHashMap<String, Prepayment_charge_standard>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Prepayment_charge_standard> typeList = Arrays.asList(Prepayment_charge_standard.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Prepayment_charge_standard) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Prepayment_charge_standard(String... names) {
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

