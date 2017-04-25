
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Contract_status implements IType {
    ID_1("1","待生效","1"),
    ID_2("4","还款中","4"),
    ID_3("7","待终止","7"),
    ID_4("8","终止","8"),
    ID_5("5","逾期","5"),
    ID_6("2","生效","2"),
    ID_7("0","创建","0"),
    ID_8("9","作废","9"),
    ID_9("6","结清","6"),
    ID_10("3","已支付待确认","3");


    String id;
    String value;
    String seq;

    Contract_status(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Contract_status> map = new LinkedHashMap<String, Contract_status>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Contract_status> typeList = Arrays.asList(Contract_status.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Contract_status) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Contract_status(String... names) {
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

