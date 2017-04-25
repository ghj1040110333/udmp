
package cn.com.git.udmp.common.enums.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.enums.IType;

public enum Upload_other_type implements IType {
    ID_1("WorkersCard","工卡/名片","10"),
    ID_2("Deed","房产证","10"),
    ID_3("ProofOfEmployment","工作/学生证明","10"),
    ID_4("SocialSecurityCard","社保卡","10"),
    ID_5("AccountOfThe","户口本","10"),
    ID_6("SalaryCard","工资卡及流水","10");


    String id;
    String value;
    String seq;

    Upload_other_type(String _id, String _value, String _seq) {
        id = _id;
        value = _value;
        seq = _seq;
    }
    
    private static Map<String, Upload_other_type> map = new LinkedHashMap<String, Upload_other_type>();
    private static Map<String, String> valMap = new LinkedHashMap<String, String>();
    
    static {
        List<Upload_other_type> typeList = Arrays.asList(Upload_other_type.values());
        typeList.sort(new Comparator<IType>() {

            @Override
            public int compare(IType x, IType y) {
                return  x.getSeq().compareTo(y.getSeq());
            }
        });
        for(IType x:typeList){
            map.put(x.name(),    (Upload_other_type) x);
            valMap.put(x.getId(), x.getValue());
        }
    }

    Upload_other_type(String... names) {
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

