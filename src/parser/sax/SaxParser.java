/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package parser.sax;


import database.DatabaseQueries;
import model.Seed;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SaxParser {

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            String xml = "open_data_seed.xml";
            SeedSaxContentHandler handler = new SeedSaxContentHandler(300);
            parser.parse(xml, handler);

            List<Seed> seedList = handler.getSeedList();



            DatabaseQueries.insertSeedSaxStaxParser(seedList);
            DatabaseQueries.selectSeed();


        } catch (ParserConfigurationException | SAXException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
