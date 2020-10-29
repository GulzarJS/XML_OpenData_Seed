/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package parser.stax;

import database.DatabaseQueries;
import model.Seed;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {

    public static void main(String[] args) {
        try {
            String xml = "open_data_seed.xml";
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(xml));

            List<Seed> seedList = new ArrayList<>();
            Seed temp = null;
             boolean isSeed = false;
             boolean isId = false;
             boolean isName = false;
             boolean isCode = false;
             boolean isHibridName = false;
             boolean isPatentNumber = false;
             boolean isPatentOwner = false;
             boolean isStartDate = false;
             boolean isEndDate = false;
             boolean isStartRegisterDate = false;
            int counter = 0;
            int limit = 250;

            while (reader.hasNext()) {

                XMLEvent event = reader.nextEvent();

                if (event.getEventType() == XMLStreamConstants.START_DOCUMENT) {
                    System.out.println("Reading XML Document has started");
                } else if (event.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                    System.out.println("Reading XML Document has finished");
                } else if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement element = (StartElement) event;
                    String name = element.getName().toString();
                    switch (name) {
                        case "SEED":
                            temp = new Seed();
                            isSeed = true;
                            break;
                        case "ID":
                            isId = true;
                            break;
                        case "NAME":
                            isName = true;
                            break;
                        case "CODE":
                            isCode = true;
                            break;
                        case "HIBRID_NAME":
                            isHibridName = true;
                            break;
                        case "PATENT_NUMBER":
                            isPatentNumber = true;
                            break;
                        case "PATENT_OWNER":
                            isPatentOwner = true;
                            break;
                        case "START_DATE":
                            isStartDate = true;
                            break;
                        case "END_DATE":
                            isEndDate = true;
                            break;
                        case "START_REGISTER_DATE":
                            isStartRegisterDate = true;
                            break;
                    }
                } else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
                    Characters characters = (Characters) event;
                    if (isId) {
                        temp.setId(Integer.parseInt(characters.getData()));
                    } else if (isName) {
                        temp.setName(characters.getData());
                    } else if (isCode) {
                        temp.setCode(Integer.parseInt(characters.getData()));
                    } else if (isHibridName) {
                        temp.setHibridName(characters.getData());
                    } else if (isPatentNumber) {
                        temp.setPatentNumber(characters.getData());
                    } else if (isPatentOwner) {
                        temp.setPatentOwner(characters.getData());
                    } else if (isStartDate) {
                        temp.setStartDate(characters.getData());
                    } else if (isEndDate) {
                        temp.setEndDate(characters.getData());
                    } else if (isStartRegisterDate) {
                        temp.setStartRegisterDate(characters.getData());
                    }
                } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    EndElement element = (EndElement) event;
                    String name = element.getName().toString();
                    if (name.equals("SEED")) {
                        isSeed = false;

                        if (counter < limit) {
                            seedList.add(temp);
                            counter++;
                        } else {
                            System.out.println(limit + " seeds have beed read");
                            break;
                        }

                        System.out.println("New seed has been read " + temp);
                        temp = null;
                    } else if (name.equals("ID")) {
                    isId = false;
                    } else if (name.equals("NAME")) {
                        isName = false;
                    } else if (name.equals("CODE")) {
                        isCode = false;
                    } else if (name.equals("HIBRID_NAME")) {
                        isHibridName = false;
                    } else if (name.equals("PATENT_NUMBER")) {
                        isPatentNumber = false;
                    } else if (name.equals("PATENT_OWNER")) {
                        isPatentOwner = false;
                    } else if (name.equals("START_DATE")) {
                        isStartDate = false;
                    } else if (name.equals("END_DATE")) {
                        isEndDate = false;
                    } else if (name.equals("START_REGISTER_DATE")) {
                        isStartRegisterDate = false;
                    }
                }
            }



            DatabaseQueries.insertSeedSaxStaxParser(seedList);
            DatabaseQueries.selectSeed();



        } catch (XMLStreamException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
