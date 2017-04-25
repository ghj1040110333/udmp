
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Loan_use implements IType {
    ID_1("1","偿还贷款/债务","10"),
    ID_2("5","教育培训","50"),
    ID_3("3","投资股权/债券","30"),
    ID_4("2","企业经营","20"),
    ID_5("4","购物","40"),
    ID_6("6","租赁房屋","60"),
    ID_7("10","其他","100"),
    ID_8("8","婚礼/庆典","80"),
    ID_9("7","装修房屋","70"),
    ID_10("9","旅游","90");


    String id;
    String value;
    String seq;

    Loan_use(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Loan_use> map = new LinkedHashMap<String, Loan_use>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Loan_use> typeList = Arrays.asList(Loan_use.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Loan_use) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Loan_use(String... names) {
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

