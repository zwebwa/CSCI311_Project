/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akjsoup;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class AKJSoup {
	public static db db = new db();
        public static int count = 0;
        
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
		processPage("http://www.dmoz.org/");
	}
 
	public static void processPage(String URL) throws SQLException, IOException{
		//check if the given URL is already in database
		String sql = "select * from Record where URL = '"+URL+"'";
		ResultSet rs = db.runSQL(sql);
		if(rs.next()){
                        System.out.print("This is old");
		}else{
			//store the URL to database to avoid parsing again
			sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`,`keywords`) VALUES " + "(?,?)";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
                        Document doc = Jsoup.connect(URL).get();
                        stmt.setString(2, doc.body().text());
			stmt.execute();
 
			//get useful information
			/*Document doc = Jsoup.connect(URL).get();  
                                              
                        String sql1 = "UPDATE `Crawler`.`Record` set keywords='"+doc.body().text()+"' where URL='"+URL+"'";
                        System.out.print(sql1);
                        db.runSql3(sql1);*/
                       // System.out.print(doc.body().text());
                         
		/*	if(doc.text().contains("Career")){
				System.out.println(URL);
			}  */
                        			
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
                       
			for(Element link: questions){                           
                        //         System.out.println(link.attr("href"));
			//	if(link.attr("href").contains("dmoz.org"))
                                       
					processPage(link.attr("abs:href"));
                                        
			}
		}
	}
}
