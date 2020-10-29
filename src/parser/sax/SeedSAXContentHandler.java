/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package parser.sax;
import model.Seed;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SeedSAXContentHandler extends DefaultHandler {

    private List<Seed> seedList;
    private Seed temp;
    private boolean isSeed;
    private boolean isId;
    private boolean isName;
    private boolean isCode;
    private boolean isHibridName;
    private boolean isPatentNumber;
    private boolean isPatentOwner;
    private boolean isStartDate;
    private boolean isEndDate;
    private boolean isStartRegisterDate;
    private int limit;
    private int counter = 0;

    public SeedSAXContentHandler() {
        this.seedList = new ArrayList<>();
        this.limit = 0;
    }

    public SeedSAXContentHandler(int limit) {
        this();
        this.limit = limit;
    }

    public List<Seed> getSeedList() {
        return seedList;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Reading XML Document has started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("Has just started to read " + qName + " element");

        if (qName.equals("SEED")) {
            temp = new Seed();
            isSeed = true;
        } else if (qName.equals("ID")) {
            isId = true;
        } else if (qName.equals("NAME")) {
            isName = true;
        } else if (qName.equals("CODE")) {
            isCode = true;
        } else if (qName.equals("HIBRID_NAME")) {
            isHibridName = true;
        } else if (qName.equals("PATENT_NUMBER")) {
            isPatentNumber = true;
        } else if (qName.equals("PATENT_OWNER")) {
            isPatentOwner = true;
        } else if (qName.equals("START_DATE")) {
            isStartDate = true;
        } else if (qName.equals("END_DATE")) {
            isEndDate = true;
        } else if (qName.equals("START_REGISTER_DATE")) {
            isStartRegisterDate = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
//        System.out.println("Data which is read = " + data);

        if (isId) {
            temp.setID(Integer.parseInt(data));
        } else if (isName) {
            temp.setNAME(data);
        } else if (isCode) {
            temp.setCODE(Integer.parseInt(data));
        } else if (isHibridName) {
            temp.setHIBRID_NAME(data);
        } else if (isPatentNumber) {
            temp.setPATENT_NUMBER(data);
        } else if (isPatentOwner) {
            temp.setPATENT_OWNER(data);
        } else if (isStartDate) {
            temp.setSTART_DATE(data);
        } else if (isEndDate) {
            temp.setEND_DATE(data);
        } else if (isStartRegisterDate) {
            temp.setSTART_REGISTER_DATE(data);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("Has just finished to read " + qName + " element");

        if (qName.equals("SEED")) {

            if (counter < limit) {
                seedList.add(temp);
            }

            counter++;
//            System.out.println("One SEED has been read completely => Data = " + temp);
            temp = null;
            isSeed = false;
        } else if (qName.equals("ID")) {
            isId = false;
        } else if (qName.equals("NAME")) {
            isName = false;
        } else if (qName.equals("CODE")) {
            isCode = false;
        } else if (qName.equals("HIBRID_NAME")) {
            isHibridName = false;
        } else if (qName.equals("PATENT_NUMBER")) {
            isPatentNumber = false;
        } else if (qName.equals("PATENT_OWNER")) {
            isPatentOwner = false;
        } else if (qName.equals("START_DATE")) {
            isStartDate = false;
        } else if (qName.equals("END_DATE")) {
            isEndDate = false;
        } else if (qName.equals("START_REGISTER_DATE")) {
            isStartRegisterDate = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Reading XML Document has finished");
    }


}
