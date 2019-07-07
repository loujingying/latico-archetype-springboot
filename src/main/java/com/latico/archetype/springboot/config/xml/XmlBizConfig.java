package com.latico.archetype.springboot.config.xml;

import com.latico.commons.common.config.AbstractConfig;
import com.latico.commons.common.config.FieldConfigNameAnnotation;
import com.latico.commons.common.util.other.PathUtils;
import com.latico.commons.common.util.xml.Dom4jUtils;
import org.dom4j.Element;

import javax.sql.DataSource;
import java.util.Map;

/**
 * <PRE>
 * 业务配置，XML方式的配置，跟config/config.xml和./config/config.xml对应，
 * 编写配置加载的时候，请按照文件系统文件配置复写资源配置文件配置的方式。
 * 对于复杂的配置项，可以使用这个配置类进行处理
 * 字段自动注入方法:
 * 可以使用注解 {@link FieldConfigNameAnnotation} 对字段跟配置文件中的字段关联，
 * 这样用反射进行字段值写入，可以参考示例{@link XmlBizConfig}
 * <p>
 * <p>
 * <p>
 * 配置文件定义，注意：该类没有配置name4，会被过滤忽略
 *
 * <config hint="提供给Config类进行配置加载">
 * <common commonId="commonIdValue" commonName="commonNameValue">
 * <name1>value1</name1>
 * <name2 a="1" ag="3">value2</name2>
 * <name3>3</name3>
 * <name4>3</name4>
 * <name5>5</name5>
 * <name6></name6>
 * </common>
 * </config>
 *
 * <p>
 * <p>
 * //定义字段和配置文件的映射
 *
 * @FieldConfigNameAnnotation("name1") private String nameAnnotation;
 * <p>
 * private String name2;
 * @FieldConfigNameAnnotation("name3") private int nameInt;
 * <p>
 * //反射注入
 * Element rootElement = Dom4jUtils.getRootElement(fileContent);
 * <p>
 * Map<String, String> childsNameValueMap = Dom4jUtils.getChildsNameValueMap(rootElement);
 * <p>
 * //通过反射写入字段的值到对象字段
 * writeFieldValue(this, childsNameValueMap);
 * //或者下面的写法
 * writeFieldValueToCurrentInstance(childsNameValueMap);
 *
 * </PRE>
 * @Author: latico
 * @Date: 2019-02-19 15:55
 * @Version: 1.0
 */
public class XmlBizConfig extends AbstractConfig {
    /**
     * instance 单例实例
     */
    private static volatile XmlBizConfig instance = null;

    /**
     * datanet数据源
     */
    private volatile DataSource dataSource;

    /**
     * 默认配置文件
     */
    private final String resourcesConfigFile = RESOURCES_CONFIG_FILE_ROOT_DIR + "config.xml";

    /**
     * 配置文件，会覆盖默认配置文件，该文件是实际项目需要使用的时候添加,adapterFilePathSupportWebContainer的作用是增加识别WEB-INF目录（war包部署方式）
     */
    private final String configFile = PathUtils.adapterFilePathSupportWebContainer(CONFIG_FILE_ROOT_DIR + "config.xml");

    private int id;

    /**
     * 读取common节点上面的commonId属性
     */
    private String commonId;
    /**
     * 读取common节点上面的commonName属性
     */
    private String commonName;
    /**
     * 使用注解转换字段名称
     */
    @FieldConfigNameAnnotation("name1")
    private String nameAnnotation;

    /**
     * 不适用注解，直接匹配跟配置文件的一样
     */
    private String name2;
    /**
     * int类型，使用注解转换字段名称
     */
    @FieldConfigNameAnnotation("name3")
    private int nameInt;

    /**
     * 这里是name5
     */
    private String name5;

    private XmlBizConfig() {
        initOrRefreshConfig();
    }

    /**
     * 获取单例，同步双重校验的好处在于，兼顾了效率、支持延迟加载、可以再创建对象后，
     * 调用方法进行初始化，而不需要在构造方法初始化，因为有些参数的加载，需要对象创建成功后进行
     *
     * @return
     */
    public static XmlBizConfig getInstance() {
        if (instance == null) {
            synchronized (XmlBizConfig.class) {
                if (instance == null) {
                    instance = new XmlBizConfig();
                }
            }
        }
        return instance;
    }

    @Override
    protected void initOtherConfig() {

        //有些数据源不能重复初始化
        if (this.dataSource == null) {
            synchronized (this) {
                if (this.dataSource == null) {
                    initDataSource();
                }
            }
        }
    }

    /**
     * 初始化数据源
     */
    private void initDataSource() {
    }

    /**
     * 获取资源配置文件路径
     *
     * @return 资源配置文件路径
     */
    @Override
    protected String getResourcesConfigFilePath() {
        return resourcesConfigFile;
    }

    /**
     * @return 工程配置文件路径
     */
    @Override
    protected String getConfigFilePath() {
        return configFile;
    }

    /**
     * 初始化配置，默认情况下initResourcesConfigFile和initConfigFile都调用该方法
     * 因为一般情况下，资源配置文件的内容跟工程配置文件内容一样或者资源配置文件内容更多，
     * 而只开放了很小一部分配置给工程配置文件；
     *
     * @param fileContent 配置文件内容
     * @return 是否成功
     * @throws Exception
     */
    @Override
    protected boolean initConfig(String fileContent) throws Exception {
        Element rootElement = Dom4jUtils.getRootElement(fileContent);

        Element commonEle = rootElement.element("common");

//        把属性写进本对象
        Map<String, String> allAttributeNameValueMap = Dom4jUtils.getAllAttributeNameValueMap(commonEle);
        writeFieldValue(this, allAttributeNameValueMap);

        //        把子列表的值写进本对象
        Map<String, String> childsNameValueMap = Dom4jUtils.getChildsNameValueMap(commonEle);
        //通过反射写入字段的值
//        writeFieldValue(this, childsNameValueMap);
        writeFieldValueToCurrentInstance(childsNameValueMap);

        return true;
    }

    public String getResourcesConfigFile() {
        return resourcesConfigFile;
    }

    public String getConfigFile() {
        return configFile;
    }

    public String getNameAnnotation() {
        return nameAnnotation;
    }

    public String getName2() {
        return name2;
    }

    public int getNameInt() {
        return nameInt;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String getCommonId() {
        return commonId;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getName5() {
        return name5;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("XmlBizConfig{");
        sb.append("dataSource=").append(dataSource);
        sb.append(", resourcesConfigFile='").append(resourcesConfigFile).append('\'');
        sb.append(", configFile='").append(configFile).append('\'');
        sb.append(", id=").append(id);
        sb.append(", commonId='").append(commonId).append('\'');
        sb.append(", commonName='").append(commonName).append('\'');
        sb.append(", nameAnnotation='").append(nameAnnotation).append('\'');
        sb.append(", name2='").append(name2).append('\'');
        sb.append(", nameInt=").append(nameInt);
        sb.append(", name5='").append(name5).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
