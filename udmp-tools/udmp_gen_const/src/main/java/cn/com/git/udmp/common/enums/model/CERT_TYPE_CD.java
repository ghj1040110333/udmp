
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum CERT_TYPE_CD implements IType {
    ID_1("01","居民身份证","1"),
    ID_2("02","户口簿","2"),
    ID_3("07","临时身份证","7"),
    ID_4("08","军人身份证","8"),
    ID_5("09","军官证","9"),
    ID_6("12","警官证","12"),
    ID_7("17","外国人永久居留证","17"),
    ID_8("99","其他","99"),
    ID_9("03","文职干部证","3"),
    ID_10("04","武警身份证","4"),
    ID_11("05","武警文职干部证","5"),
    ID_12("06","港澳居民来往内地通行证","6"),
    ID_13("10","军官退休证","10"),
    ID_14("11","文职干部退休证","11"),
    ID_15("13","武警军官退休证","13"),
    ID_16("14","武警文职干部退休证","14"),
    ID_17("15","本国护照","15"),
    ID_18("16","外国护照","16");


    String id;
    String value;
    String seq;

    CERT_TYPE_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, CERT_TYPE_CD> map = new LinkedHashMap<String, CERT_TYPE_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<CERT_TYPE_CD> typeList = Arrays.asList(CERT_TYPE_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),   (CERT_TYPE_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    CERT_TYPE_CD(String... names) {
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

