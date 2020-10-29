/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package database;

import model.Seed;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import static java.sql.Date.valueOf;

public class DatabaseQueries {


    // Function for clean table
    public static void cleanSeedTable(){

        Database database = new Database();
        String sql;

        try{

            sql = "delete from seed";

            database.setPs(database.getConnection().prepareStatement(sql));
            database.getPs().executeUpdate();
            database.getConnection().commit();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }

    }

    // Function for inserting information about seeds => for DOM Parser
    public static void insertSeedSaxStaxParser(List<Seed> seedNodeList) throws SQLException {



        Database database = new Database();
        String sql;

        try {
            sql = "insert into seed( id, name, code, hibrid_name, patent_number, start_date, end_date, patent_owner, start_register_date)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ? )";


            ListIterator<Seed> seedIterator = seedNodeList.listIterator();

            while(seedIterator.hasNext()){

                Seed temp = seedIterator.next();

                database.setPs(database.getConnection().prepareStatement(sql));

                database.getPs().setInt(1,temp.getID());
                database.getPs().setString(2, temp.getNAME());
                database.getPs().setInt(3, temp.getCODE());
                database.getPs().setString(4,temp.getHIBRID_NAME());
                database.getPs().setString(5, temp.getPATENT_NUMBER());
                database.getPs().setDate(6, valueOf(temp.getSTART_DATE()));
                database.getPs().setDate(7, valueOf(temp.getEND_DATE()));
                database.getPs().setString(8, temp.getPATENT_OWNER());
                database.getPs().setString(9, temp.getSTART_REGISTER_DATE());



                database.getPs().executeUpdate();
                database.getConnection().commit();
            }

            }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
    }


    // Function for inserting information about seeds => for DOM Parser
    public static void insertSeedDomParser(NodeList seedNodeList) throws SQLException {


        Database database = new Database();
        String sql;

        try {
            sql = "insert into seed( id, name, code, hibrid_name, patent_number, start_date, end_date, patent_owner, start_register_date)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ? )";

            for (int i = 0; i < seedNodeList.getLength(); i++) {
                Element seedElement = (Element) seedNodeList.item(i);

                database.setPs(database.getConnection().prepareStatement(sql));

                database.getPs().setInt(1, Integer.valueOf(seedElement.getElementsByTagName("ID").item(0).getTextContent()));
                database.getPs().setString(2, seedElement.getElementsByTagName("NAME").item(0).getTextContent());
                database.getPs().setInt(3, Integer.valueOf(seedElement.getElementsByTagName("CODE").item(0).getTextContent()));
                database.getPs().setString(4, seedElement.getElementsByTagName("HIBRID_NAME").item(0).getTextContent());
                database.getPs().setString(5, seedElement.getElementsByTagName("PATENT_NUMBER").item(0).getTextContent());
                database.getPs().setDate(6, valueOf(seedElement.getElementsByTagName("START_DATE").item(0).getTextContent()));
                database.getPs().setDate(7, valueOf(seedElement.getElementsByTagName("END_DATE").item(0).getTextContent()));
                database.getPs().setString(8, seedElement.getElementsByTagName("PATENT_OWNER").item(0).getTextContent());
                database.getPs().setString(9, seedElement.getElementsByTagName("START_REGISTER_DATE").item(0).getTextContent());



                database.getPs().executeUpdate();
                database.getConnection().commit();
            }


        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
    }

    // Function for select and printing information about seeds from database
    public static void selectSeed() throws SQLException {


        Database database = new Database();
        String sql;

        try{
            sql = "select id, name, code, hibrid_name, patent_number, start_date, end_date, patent_owner, start_register_date " +
                    "from seed order by id ";

            database.setPs(database.getConnection().prepareStatement(sql));

            database.setRs(database.getPs().executeQuery());

            while (database.getRs().next()) {

                Seed seed = new Seed();

                seed.setID(database.getRs().getInt("id"));
                seed.setNAME(database.getRs().getString("name"));
                seed.setCODE(database.getRs().getInt("code"));
                seed.setHIBRID_NAME(database.getRs().getString("hibrid_name"));
                seed.setPATENT_NUMBER(database.getRs().getString("patent_number"));
                seed.setPATENT_OWNER(database.getRs().getString("patent_owner"));
                seed.setSTART_DATE(database.getRs().getDate("start_date").toString());
                seed.setEND_DATE(database.getRs().getDate("end_date").toString());
                seed.setSTART_REGISTER_DATE(database.getRs().getString("start_register_date"));

                System.out.println(seed);

            }

            DatabaseQueries.cleanSeedTable();
            database.getConnection().commit();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }

    }

}
