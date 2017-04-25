
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum PRODUCT_CATE_CD implements IType {
    ID_1("02","固定资产贷款","2"),
    ID_2("05","供应链融资","5"),
    ID_3("07","国际业务","7"),
    ID_4("01","流动资金贷款","1"),
    ID_5("03","项目贷款","3"),
    ID_6("04","贸易融资","4"),
    ID_7("06","票据","6"),
    ID_8("08","其它","8");


    String id;
    String value;
    String seq;

    PRODUCT_CATE_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, PRODUCT_CATE_CD> map = new LinkedHashMap<String, PRODUCT_CATE_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<PRODUCT_CATE_CD> typeList = Arrays.asList(PRODUCT_CATE_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (PRODUCT_CATE_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    PRODUCT_CATE_CD(String... names) {
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

