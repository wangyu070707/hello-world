package com.martin;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZXY
 * @ClassName: com.martin.CodeUtil
 * @Description: TODO
 * @date 2016/4/8 15:07
 */
public class CodeUtil {

    private Configuration cfg;
    private String project_path = "";
    private String center = "";
    private String modelName = "";
    private Map root = new HashMap();
    private Map xmlMap = null;

    public CodeUtil() throws Exception {
        XmlRead xmlRead = new XmlRead();
        xmlMap = xmlRead.getXml();
        this.project_path = xmlMap.get("project").toString();
        this.center = xmlMap.get("center").toString();
        this.modelName = xmlMap.get("model").toString();

        String tableName = xmlMap.get("table").toString();
        String centerCode = xmlMap.get("centerCode").toString();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = df.format(new Date());

        String author = xmlMap.get("author").toString();
        String bizName = xmlMap.get("bizName").toString();
        String tomcatPort = xmlMap.get("tomcatPort").toString();

        root.put("project_path", checkPath(project_path));
        System.out.println(root.get("project_path").toString());
        root.put("center", center);
        root.put("centerCode", centerCode);
        root.put("modelName", modelName);
        root.put("tableName", tableName.toUpperCase());
        root.put("curTime", curTime);
        root.put("author", author);
        root.put("bizName", bizName);
        root.put("tomcatPort", tomcatPort);

        // 初始化FreeMarker配置  创建一个Configuration实例
        cfg = new Configuration();
        // 设置FreeMarker的模版文件位置
        cfg.setClassForTemplateLoading(this.getClass(), "/template");
        Map<String, Object> map = TableUtil.transferTable(tableName);
        root.put("keyList", map.get("keyList"));
        root.put("columnList", map.get("columnList"));
    }

    /**
     * 建Controller
     *
     * @throws Exception
     */
    public void createController() throws Exception {
        Template template = cfg.getTemplate("createController.ftl");
        String fileName = modelName + "Controller.java";
        String savePath = "/axp-acl/src/main/java/com/zhiduan/axp/acl/" + center + "/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建Bean
     *
     * @throws Exception
     */
    public void createBean() throws Exception {
        Template template = cfg.getTemplate("createBean.ftl");
        String fileName = modelName + "Bean.java";
        String savePath = "/axp-idl/src/main/java/com/zhiduan/axp/idl/" + center + "/dao/bean/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建Info
     *
     * @throws Exception
     */
    public void createInfo() throws Exception {
        Template template = cfg.getTemplate("createInfo.ftl");
        String fileName = modelName + "Info.java";
        String savePath = "/axp-idl/src/main/java/com/zhiduan/axp/idl/" + center + "/service/entity/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建Entity
     *
     * @throws Exception
     */
    public void createEntity() throws Exception {
        Template template = cfg.getTemplate("createEntity.ftl");
        String fileName = modelName + "Entity.java";
        String savePath = "";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建SV
     *
     * @throws Exception
     */
    public void createSV() throws Exception {
        Template template = cfg.getTemplate("createSV.ftl");
        String fileName = modelName + "Service.java";
        String savePath = "/axp-idl/src/main/java/com/zhiduan/axp/idl/" + center + "/service/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建*SVImpl
     *
     * @throws Exception
     */
    public void createSVImpl() throws Exception {
        Template template = cfg.getTemplate("createSVImpl.ftl");
        String fileName = modelName + "ServiceImpl.java";
        String savePath = "/axp-bll/src/main/java/com/zhiduan/axp/bll/" + center + "/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建DAO
     *
     * @throws Exception
     */
    public void createDAO() throws Exception {
        Template template = cfg.getTemplate("createDAO.ftl");
        String fileName = modelName + "Dao.java";
        String savePath = "/axp-idl/src/main/java/com/zhiduan/axp/idl/" + center + "/dao/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建*DAOImpl
     *
     * @throws Exception
     */
    public void createDAOImpl() throws Exception {
        Template template = cfg.getTemplate("createDAOImpl.ftl");
        String fileName = modelName + "DaoImpl.java";
        String savePath = "/axp-dal/src/main/java/com/zhiduan/axp/dal/" + center + "/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建Mapper
     *
     * @throws Exception
     */
    public void createMapper() throws Exception {
        Template template = cfg.getTemplate("createMapper.ftl");
        String fileName = modelName + "Mapper.java";
        String savePath = "/axp-idl/src/main/java/com/zhiduan/axp/idl/" + center + "/dao/mapper/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 建MapperXML
     *
     * @throws Exception
     */
    public void createMapperXml() throws Exception {
        Template template = cfg.getTemplate("createMapperXml.ftl");
        String fileName = modelName + "Mapper.xml";
        String savePath = "/axp-dal/src/main/resources/mappings/";
        createFile(root, project_path, savePath, fileName, template);
    }

    /**
     * 生成url
     *
     * @throws Exception
     */
    public void createUrl() throws Exception {
        Template template = cfg.getTemplate("createUrl.ftl");
        StringWriter tmpOut = new StringWriter();
        template.process(root, tmpOut);

        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 截取路径中的jar包名
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        System.out.println(filePath + "/URL.txt");

        BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "/URL.txt", false));
        out.write(tmpOut.toString());
        out.close();
        tmpOut.close();
    }

    /**
     * 更新acl消费者xml文件
     *
     * @throws Exception
     */
    public void updateACLConsumerXml() throws Exception {
        Template template = cfg.getTemplate("createACLConsumer.ftl");
        StringWriter tmpOut = new StringWriter();
        template.process(root, tmpOut);
        tmpOut.close();

        String filePath = xmlMap.get("aclConsumer").toString().replace("%", xmlMap.get("center").toString());
        BufferedWriter out = new BufferedWriter(new FileWriter(project_path + "/" + filePath, true));
        out.write(tmpOut.toString());
        out.close();
    }

    /**
     * 更新bll生产者xml文件
     *
     * @throws Exception
     */
    public void updateBLLProviderXml() throws Exception {
        Template template = cfg.getTemplate("createBLLProvider.ftl");
        StringWriter tmpOut = new StringWriter();
        template.process(root, tmpOut);
        tmpOut.close();

        String filePath = xmlMap.get("bllProvider").toString().replace("%", xmlMap.get("center").toString());
        BufferedWriter out = new BufferedWriter(new FileWriter(project_path + "/" + filePath, true));
        out.write(tmpOut.toString());
        out.close();
    }

    /**
     * 更新bll消费者xml文件
     *
     * @throws Exception
     */
    public void updateBLLConsumerXml() throws Exception {
        Template template = cfg.getTemplate("createBLLConsumer.ftl");
        StringWriter tmpOut = new StringWriter();
        template.process(root, tmpOut);
        tmpOut.close();

        String filePath = xmlMap.get("bllConsumer").toString().replace("%", xmlMap.get("center").toString());
        BufferedWriter out = new BufferedWriter(new FileWriter(project_path + "/" + filePath, true));
        out.write(tmpOut.toString());
        out.close();
    }

    /**
     * 更新dal生产者xml文件
     *
     * @throws Exception
     */
    public void updateDALProviderXml() throws Exception {
        Template template = cfg.getTemplate("createDALProvider.ftl");
        StringWriter tmpOut = new StringWriter();
        template.process(root, tmpOut);
        tmpOut.close();

        String filePath = xmlMap.get("dalProvider").toString().replace("%", xmlMap.get("center").toString());
        BufferedWriter out = new BufferedWriter(new FileWriter(project_path + "/" + filePath, true));
        out.write(tmpOut.toString());
        out.close();
    }

    /**
     * 根据模版创建文件
     *
     * @param root
     * @param projectPath
     * @param savePath
     * @param fileName
     * @param template
     */
    private void createFile(Map root, String projectPath, String savePath, String fileName, Template template) throws Exception {
        String realFileName = projectPath + savePath + fileName;
        String realSavePath = projectPath + "/" + savePath;

        File newsDir = new File(realSavePath);
        if (!newsDir.exists()) {
            newsDir.mkdirs();
        }
        System.out.println(realFileName);

        Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
        template.process(root, out);
    }

    /**
     * 替换 \ 为 /
     */
    private String checkPath(String s) throws Exception {
        if (!s.endsWith("/")) {
            s = s + "/";
        }
        return s.replaceAll("\\\\", "/");
    }
}
