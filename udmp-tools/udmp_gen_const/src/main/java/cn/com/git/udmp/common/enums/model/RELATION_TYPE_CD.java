
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum RELATION_TYPE_CD implements IType {
    ID_1("06","参股关系","6"),
    ID_2("07","供应关系","7"),
    ID_3("10","法人代表关联","10"),
    ID_4("12","配偶","12"),
    ID_5("16","女儿","16"),
    ID_6("18","弟弟","18"),
    ID_7("01","全资关系","1"),
    ID_8("02","控股关系","2"),
    ID_9("03","控制关系","3"),
    ID_10("04","家族关系","4"),
    ID_11("05","第三方控制关系","5"),
    ID_12("08","销售关系","8"),
    ID_13("09","担保关系","9"),
    ID_14("11","其他高管关联","11"),
    ID_15("13","父亲","13"),
    ID_16("14","母亲","14"),
    ID_17("15","儿子","15"),
    ID_18("17","兄长","17"),
    ID_19("19","姐姐","19"),
    ID_20("20","妹妹","20"),
    ID_21("99","其他关系","99");


    String id;
    String value;
    String seq;

    RELATION_TYPE_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, RELATION_TYPE_CD> map = new LinkedHashMap<String, RELATION_TYPE_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<RELATION_TYPE_CD> typeList = Arrays.asList(RELATION_TYPE_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (RELATION_TYPE_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    RELATION_TYPE_CD(String... names) {
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

