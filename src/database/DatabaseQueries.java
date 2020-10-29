/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package database;

import model.Seed;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

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
    public static void insertSeedSaxParser(List<Seed> seedNodeList) throws SQLException {

        DatabaseQueries.cleanSeedTable();

        Database database = new Database();
        String sql;

        try {
            sql = "insert into seed( id, name, code, hibrid_name, patent_number, start_date, end_date, patent_owner, start_register_date)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ? )";


            ListIterator<Seed> seedIterator = seedNodeList.listIterator();

            while(seedIterator.hasNext()){

                Seed temp = seedIterator.next();

                database.setPs(database.getConnection().prepareStatement(sql));

                database.getPs().setInt(1,temp.getId());
                database.getPs().setString(2, temp.getName());
                database.getPs().setInt(3, temp.getCode());
                database.getPs().setString(4,temp.getHibridName());
                database.getPs().setString(5, temp.getPatentNumber());
                database.getPs().setDate(6, Date.valueOf(temp.getStartDate()));
                database.getPs().setDate(7, Date.valueOf(temp.getEndDate()));
                database.getPs().setString(8, temp.getPatentOwner());
                database.getPs().setString(9, temp.getStartRegisterDate());



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

        DatabaseQueries.cleanSeedTable();

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
                database.getPs().setDate(6, Date.valueOf(seedElement.getElementsByTagName("START_DATE").item(0).getTextContent()));
                database.getPs().setDate(7, Date.valueOf(seedElement.getElementsByTagName("END_DATE").item(0).getTextContent()));
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

                seed.setId(database.getRs().getInt("id"));
                seed.setName(database.getRs().getString("name"));
                seed.setCode(database.getRs().getInt("code"));
                seed.setHibridName(database.getRs().getString("hibrid_name"));
                seed.setPatentNumber(database.getRs().getString("patent_number"));
                seed.setPatentOwner(database.getRs().getString("patent_owner"));
                seed.setStartDate(database.getRs().getDate("start_date").toString());
                seed.setEndDate(database.getRs().getDate("end_date").toString());
                seed.setStartRegisterDate(database.getRs().getString("start_register_date"));

                database.getConnection().commit();
                System.out.println(seed);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }

    }

}
