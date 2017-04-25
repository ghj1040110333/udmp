
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Unit_type implements IType {
    ID_1("3","制造业","30"),
    ID_2("16","教育","160"),
    ID_3("4","电力、热力、燃气及水生产和供应业","40"),
    ID_4("6","批发和零售业","60"),
    ID_5("8","住宿和餐饮业","80"),
    ID_6("9","信息传输、软件和信息技术服务业","90"),
    ID_7("20","国际组织","200"),
    ID_8("13","科学研究和技术服务业","130"),
    ID_9("14","水利、环境和公共设施管理业","140"),
    ID_10("17","卫生和社会工作","170"),
    ID_11("11","房地产业","110"),
    ID_12("5","建筑业","50"),
    ID_13("18","文化、体育和娱乐业","180"),
    ID_14("12","租赁和商务服务业","120"),
    ID_15("10","金融业","100"),
    ID_16("7","交通运输、仓储和邮政业","70"),
    ID_17("19","公共管理、社会保障和社会组织","190"),
    ID_18("15","居民服务、修理和其他服务业","150"),
    ID_19("2","采矿业","20"),
    ID_20("1","农、林、牧、渔业","10");


    String id;
    String value;
    String seq;

    Unit_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Unit_type> map = new LinkedHashMap<String, Unit_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Unit_type> typeList = Arrays.asList(Unit_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Unit_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Unit_type(String... names) {
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

