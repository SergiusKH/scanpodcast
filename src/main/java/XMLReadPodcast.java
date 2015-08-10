import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergius on 10.08.15.
 * Reader class xml config file podcast.
 */
public class XMLReadPodcast {

   /*
   * return list podcast objects
   */
    public static List<Podcast> xmlReadPodcast() {
        List<Podcast> podcastList = new ArrayList<Podcast>();
        try {
            File fXmlFile = new File("podcast.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("podcast");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    podcastList.add(new Podcast(eElement.getElementsByTagName("name").item(0).getTextContent(),
                                    eElement.getElementsByTagName("rss").item(0).getTextContent(),
                                    eElement.getElementsByTagName("pattern").item(0).getTextContent(),
                                    eElement.getElementsByTagName("proxy").item(0).getTextContent())
                    );
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return podcastList;
    }
}
