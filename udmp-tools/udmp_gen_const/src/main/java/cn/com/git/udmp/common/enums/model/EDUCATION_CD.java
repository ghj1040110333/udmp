
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum EDUCATION_CD implements IType {
    ID_1("03","中职/高中","13"),
    ID_2("04","专科","14"),
    ID_3("05","本科","15"),
    ID_4("01","小学","10"),
    ID_5("02","初中","11"),
    ID_6("06","硕士研究生","16"),
    ID_7("07","博士研究生","17"),
    ID_8("99","其他","19");


    String id;
    String value;
    String seq;

    EDUCATION_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, EDUCATION_CD> map = new LinkedHashMap<String, EDUCATION_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<EDUCATION_CD> typeList = Arrays.asList(EDUCATION_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (EDUCATION_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    EDUCATION_CD(String... names) {
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

