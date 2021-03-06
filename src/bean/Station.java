package bean;

import java.time.LocalDate;

import com.javadocmd.simplelatlng.LatLng;


public class Station {


	private int stationID ;
	private String name ;
//	private double lat ;
//	private double lon ;
	private LatLng coords;
	private int dockCount ;
	private String landmark ;
	private LocalDate installation ;
	
	

	public Station(int stationID, String name, LatLng coords, int dockCount, String landmark, LocalDate installation) {
		super();
		this.stationID = stationID;
		this.name = name;
		this.coords = coords;
		this.dockCount = dockCount;
		this.landmark = landmark;
		this.installation = installation;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public double getLat() {
//		return lat;
//	}
//
//	public void setLat(double lat) {
//		this.lat = lat;
//	}
//
//	public double getLon() {
//		return lon;
//	}
//
//	public void setLon(double lon) {
//		this.lon = lon;
//	}
	
	

	public int getDockCount() {
		return dockCount;
	}

	public LatLng getCoords() {
		return coords;
	}

	public void setCoords(LatLng coords) {
		this.coords = coords;
	}

	public void setDockCount(int dockCount) {
		this.dockCount = dockCount;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public LocalDate getInstallation() {
		return installation;
	}

	public void setInstallation(LocalDate installation) {
		this.installation = installation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stationID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (stationID != other.stationID)
			return false;
		return true;
	}
	
	public String toString() {
		String r ;
		r=name+" \n";
		return r;
	}
}
