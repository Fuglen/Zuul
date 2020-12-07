package domain;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class NPC {
    private String name;
    private String description;
    private int id;
    private File file = new File("domain/npcs/npcs.xml");

    public NPC() {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = dBuilder.parse(file);


//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            System.out.println(doc.getDocumentElement().getElementsByTagName("option").item(1).getTextContent());

            /* if (doc.hasChildNodes()) {

                printNote(doc.getChildNodes());

            } */

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
