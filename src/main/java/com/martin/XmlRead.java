package com.martin;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZXYwww
 * @ClassName: com.martin.XmlRead
 * @Description: TODO
 * @date 2016/4/8 14:58
 */
public class XmlRead {
    private static Map<String, String> xmlMap = new HashMap<String, String>();

    public XmlRead() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
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
        Document doc = db.parse(filePath + "/autocode.xml");
        Element root = doc.getDocumentElement();
        parseXml(root.getChildNodes());
    }

    public void parseXml(NodeList list) throws Exception {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            short nodeType = node.getNodeType();
            if (node != null && nodeType == Node.ELEMENT_NODE) {
                NamedNodeMap map = node.getAttributes();
                Node nameNode = map.getNamedItem("name");
                Node valueNode = map.getNamedItem("value");
                if (nameNode != null && valueNode != null) {
                    xmlMap.put(nameNode.getNodeValue(), valueNode.getNodeValue());
                } else {
                    parseXml(node.getChildNodes());
                }
            }
        }
    }

    public Map<String, String> getXml() throws Exception {
        return xmlMap;
    }
}
