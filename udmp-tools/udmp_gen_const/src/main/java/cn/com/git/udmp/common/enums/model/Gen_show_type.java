
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Gen_show_type implements IType {
    ID_1("input","文本框","10"),
    ID_2("textarea","文本域","20"),
    ID_3("select","下拉框","30"),
    ID_4("checkbox","复选框","40"),
    ID_5("radiobox","单选框","50"),
    ID_6("dateselect","日期选择","60"),
    ID_7("userselect","人员选择\0","70"),
    ID_8("officeselect","部门选择","80"),
    ID_9("areaselect","区域选择","90");


    String id;
    String value;
    String seq;

    Gen_show_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Gen_show_type> map = new LinkedHashMap<String, Gen_show_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Gen_show_type> typeList = Arrays.asList(Gen_show_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Gen_show_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Gen_show_type(String... names) {
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

