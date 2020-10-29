/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package parser.dom;

import database.DatabaseQueries;
import model.Seed;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public static void main(String[] args) {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("open_data_seed.xml");

            NodeList seedNodeList = document.getElementsByTagName("SEED");

            DatabaseQueries.insertSeedDomParser(seedNodeList);
            DatabaseQueries.selectSeed();

        } catch (ParserConfigurationException | SAXException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}

