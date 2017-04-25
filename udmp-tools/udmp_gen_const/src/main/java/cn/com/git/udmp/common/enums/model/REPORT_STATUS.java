
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum REPORT_STATUS implements IType {
    ID_1("01","保存","1"),
    ID_2("02","提交","2"),
    ID_3("03","退回","3"),
    ID_4("04","完成","4");


    String id;
    String value;
    String seq;

    REPORT_STATUS(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, REPORT_STATUS> map = new LinkedHashMap<String, REPORT_STATUS>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<REPORT_STATUS> typeList = Arrays.asList(REPORT_STATUS.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (REPORT_STATUS) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    REPORT_STATUS(String... names) {
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

