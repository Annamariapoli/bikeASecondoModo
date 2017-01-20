package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import bean.Station;

public class Dao {
	
	public List<Station> getAllStation(){
		Connection conn = DBconnect.getConnection();
		List<Station > all = new LinkedList<>();
		String query ="select * from station s order by s.name ASC";
		try{
			PreparedStatement st =conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while (res.next()){
				Station s = new Station (res.getInt("station_id"), res.getString("name"), 
						    new LatLng(res.getDouble("lat"), res.getDouble("long")), 
						    res.getInt("dockcount"), res.getString("landmark"), res.getDate("installation").toLocalDate());
				all.add(s);
			}
			conn.close();
			return all;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public int contoStart(Station stazione){
		Connection conn = DBconnect.getConnection();
		String query ="select count(t.StartTerminal) as conta "
				+ "from station s , trip t "
				+ "where t.StartTerminal=s.station_id and s.station_id=?";
		int conta =-1;
		try{
			PreparedStatement st =conn.prepareStatement(query);
			st.setInt(1, stazione.getStationID());
			ResultSet res = st.executeQuery();
			if(res.next()){
				conta = res.getInt("conta");
			}
			conn.close();
			return conta;
		}catch(SQLException e ){
			e.printStackTrace();
			return -1;
		}
	}
	
	public int contoEnd(Station stazione){
		Connection conn = DBconnect.getConnection();
		String query ="select count(t.EndTerminal)as conta "
				+ "from station s , trip t "
				+ "where t.EndTerminal=s.station_id and s.station_id=?";
		int conta =-1;
		try{
			PreparedStatement st =conn.prepareStatement(query);
			st.setInt(1, stazione.getStationID());
			ResultSet res = st.executeQuery();
			if(res.next()){
				conta = res.getInt("conta");
			}
			conn.close();
			return conta;
		}catch(SQLException e ){
			e.printStackTrace();
			return -1;
		}
	}
}
