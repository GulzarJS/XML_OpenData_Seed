/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package parser.jaxb;


import database.DatabaseQueries;
import model.SeedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

public class JAXBParser{
    public static void main(String[] args) {

        try {
            JAXBContext context = JAXBContext.newInstance(SeedList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            String xml = "open_data_seed.xml";
            SeedList seedList = (SeedList) unmarshaller.unmarshal(new FileReader(xml));

            System.out.println("Number of SEEDS = " + seedList.getSeeds().size());


//            seedList.getSeeds().forEach( seed -> System.out.println(seed.toString()));

            DatabaseQueries.insertSeedSaxStaxParser(seedList.getSeeds());
            DatabaseQueries.selectSeed();

        } catch (JAXBException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}