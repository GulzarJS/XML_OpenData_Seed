/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package parser.dom;

import model.Seed;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public static void main(String[] args) {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("open_data_seed.xml");

            NodeList seedNodeList = document.getElementsByTagName("SEED");
            List<Seed> seedList = new ArrayList<>();


            for (int i = 0; i < seedNodeList.getLength(); i++) {
                Element seedElement = (Element) seedNodeList.item(i);
                String id = seedElement.getElementsByTagName("ID").item(0).getTextContent();
                String name = seedElement.getElementsByTagName("NAME").item(0).getTextContent();
//                String code = seedElement.getElementsByTagName("CODE").item(0).getTextContent();
                String hibridName = seedElement.getElementsByTagName("HIBRID_NAME").item(0).getTextContent();
                String patentNumber = seedElement.getElementsByTagName("PATENT_NUMBER").item(0).getTextContent();
//                String startDate = seedElement.getElementsByTagName("START_DATE").item(0).getTextContent();
                String patentOwner = seedElement.getElementsByTagName("PATENT_OWNER").item(0).getTextContent();
//                String endDate = seedElement.getElementsByTagName("END_DATE").item(0).getTextContent();
                String startRegisterDate = seedElement.getElementsByTagName("START_REGISTER_DATE").item(0).getTextContent();


                Seed seed = new Seed();
                seed.setId(Long.parseLong(id));
                seed.setName(name);
//                seed.setCode(Long.parseLong(code));
                seed.setHibridName(hibridName);
                seed.setPatentNumber(patentNumber);
//                seed.setStartDate(startDate);
                seed.setPatentOwner(patentOwner);
//                seed.setEndDate(endDate);
                seed.setStartRegisterDate(startRegisterDate);

                seedList.add(seed);
            }

            System.out.println("toxum sayi = " + seedList.size());
            seedList.forEach(seed -> System.out.printf("%d %s %s %s %s\n", seed.getId(), seed.getName(),
                    seed.getHibridName(), seed.getPatentNumber(), seed.getStartRegisterDate()));


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

