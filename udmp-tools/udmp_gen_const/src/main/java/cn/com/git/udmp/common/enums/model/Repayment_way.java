
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Repayment_way implements IType {
    ID_1("6","等本等息还款法","6"),
    ID_2("7","按月还本按季还息","7"),
    ID_3("4","等额累进还款法","4"),
    ID_4("8","利随本清还款法","8"),
    ID_5("5","按期还息按期还本还款法","5"),
    ID_6("2","等额本金还款法","2"),
    ID_7("9","一次还本付息法","9"),
    ID_8("3","等比累进还款法","3"),
    ID_9("1","等额本息还款法","1");


    String id;
    String value;
    String seq;

    Repayment_way(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Repayment_way> map = new LinkedHashMap<String, Repayment_way>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Repayment_way> typeList = Arrays.asList(Repayment_way.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Repayment_way) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Repayment_way(String... names) {
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

