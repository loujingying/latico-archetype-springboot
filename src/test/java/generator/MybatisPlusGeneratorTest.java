package generator;

import com.latico.commons.orm.mybatisplus.MybatisPlusUtils;
import org.junit.Test;

/**
 * <PRE>
 *  Mybatis-plus代码生成器
 *
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-06-06 15:04:09
 * @Version: 1.0
 */
public class MybatisPlusGeneratorTest {

    /**
     * 直接执行，可以生成mybatis的代码到程序当前目录
     */
    @Test
    public void execGeneratorByResourcesConfigFile() {
        try {
            MybatisPlusUtils.execGeneratorByResourcesPropertiesFile("generator/mybatis-plus-generator.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}