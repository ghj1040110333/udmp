package cn.com.git.udmp.test.gen;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.modules.gen.entity.GenScheme;
import cn.com.git.udmp.modules.gen.entity.GenTable;
import cn.com.git.udmp.modules.gen.service.GenSchemeService;

public class GenTest extends AbstractTest{
    @Autowired
    private GenSchemeService genSchemeService;
    
    //TODO 暂不能运行，还需要调整
    @Test
    public void testGen(){
        GenScheme genScheme = new GenScheme();
        genScheme.setName("test");
        genScheme.setPackageName("cn.com.git");
        genScheme.setModuleName("");
        genScheme.setFunctionAuthor("gen");
        genScheme.setFlag("1");
        genScheme.setReplaceFile(true);
        
        GenTable genTable = new GenTable();
        genTable.setName("");
        genTable.setClassName("");
        
        genScheme.setGenTable(genTable);
        
        
        genSchemeService.generateCode(genScheme);
    }

    public void setGenSchemeService(GenSchemeService genSchemeService) {
        this.genSchemeService = genSchemeService;
    }
}
