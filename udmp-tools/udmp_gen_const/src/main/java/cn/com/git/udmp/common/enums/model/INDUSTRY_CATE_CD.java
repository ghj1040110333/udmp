
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum INDUSTRY_CATE_CD implements IType {
    ID_1("01","农、林、木、渔业","1"),
    ID_2("03","制造业","3"),
    ID_3("05","建筑业","5"),
    ID_4("07","交通运输、仓储和邮政业","7"),
    ID_5("09","信息传输、软件和信息技术服务业","9"),
    ID_6("11","房地产业","11"),
    ID_7("13","科学研究和技术服务业","13"),
    ID_8("16","教育","16"),
    ID_9("18","文化、体育和娱乐业","18"),
    ID_10("02","采矿业","2"),
    ID_11("04","电力、热力、燃气及水生产和供应业","4"),
    ID_12("06","批发和零售业","6"),
    ID_13("08","住宿和餐饮业","8"),
    ID_14("10","金融业","10"),
    ID_15("12","租赁和商务服务业","12"),
    ID_16("14","水利、环境和公共设施管理业","14"),
    ID_17("15","国民服务、修理和其他服务业","15"),
    ID_18("17","卫生和社会工作","17");


    String id;
    String value;
    String seq;

    INDUSTRY_CATE_CD(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, INDUSTRY_CATE_CD> map = new LinkedHashMap<String, INDUSTRY_CATE_CD>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<INDUSTRY_CATE_CD> typeList = Arrays.asList(INDUSTRY_CATE_CD.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (INDUSTRY_CATE_CD) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    INDUSTRY_CATE_CD(String... names) {
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

