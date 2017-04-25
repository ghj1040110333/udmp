
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Dept_positions implements IType {
    ID_1("9","战士(含武警)","90"),
    ID_2("5","一般员工","50"),
    ID_3("2","中层管理人员/部门经理/处级","20"),
    ID_4("10","个体","100"),
    ID_5("7","农民","70"),
    ID_6("8","军官(含武警)","80"),
    ID_7("6","派遣制员工/临时工","60"),
    ID_8("11","其他","110"),
    ID_9("1","高层管理人员/总监/局级","10"),
    ID_10("3","基层管理人员/主管/科级","30"),
    ID_11("4","高级专业(技术)人员","40");


    String id;
    String value;
    String seq;

    Dept_positions(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Dept_positions> map = new LinkedHashMap<String, Dept_positions>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Dept_positions> typeList = Arrays.asList(Dept_positions.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Dept_positions) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Dept_positions(String... names) {
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

