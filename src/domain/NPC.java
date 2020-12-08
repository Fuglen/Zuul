package domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class NPC {
    private String name;
    private String description;
    private int id;
    private File file = new File("src/domain/npcs/npcs.xml");

    public NPC() {

    }

    public void getDialog(String name) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("npc");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("name").equals("Brian")) {
                        System.out.println("NPC: "
                                + eElement.getAttribute("name"));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setMet() {
    }
}
