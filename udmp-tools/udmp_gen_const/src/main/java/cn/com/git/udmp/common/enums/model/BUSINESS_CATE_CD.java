
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum BUSINESS_CATE_CD implements IType {
    ID_1("01","人工预警规则","1"),
    ID_2("08","预警样本库","8"),
    ID_3("02","风险认定依据","2"),
    ID_4("03","应对策略","3"),
    ID_5("04","排查方案","4"),
    ID_6("05","政策制度","5"),
    ID_7("06","绩效评价指标","6"),
    ID_8("07","典型案例分享","7");


    String id;
    String value;
    String seq;

    BUSINESS_CATE_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, BUSINESS_CATE_CD> map = new LinkedHashMap<String, BUSINESS_CATE_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<BUSINESS_CATE_CD> typeList = Arrays.asList(BUSINESS_CATE_CD.values());
//        typeList.stream().sorted((x, y) -> x.seq.compareTo(y.seq)).forEach(x -> {
//            map.put(x.name(), x);
//            valMap.put(x.getId(), x.getValue());
//        });
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),  (BUSINESS_CATE_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    BUSINESS_CATE_CD(String... names) {
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

