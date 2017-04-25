
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum WARN_TYPE_CD implements IType {
    ID_1("01","客户预警","1"),
    ID_2("02","押品预警","2"),
    ID_3("03","业务预警","3"),
    ID_4("06","产品预警","6"),
    ID_5("99","其他预警","99"),
    ID_6("04","机构预警","4"),
    ID_7("05","行业预警","5"),
    ID_8("07","系统预警","7");


    String id;
    String value;
    String seq;

    WARN_TYPE_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, WARN_TYPE_CD> map = new LinkedHashMap<String, WARN_TYPE_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<WARN_TYPE_CD> typeList = Arrays.asList(WARN_TYPE_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (WARN_TYPE_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    WARN_TYPE_CD(String... names) {
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

