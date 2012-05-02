package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class gpsLocation {
	/**
	 * @uml.property  name="mapX"
	 */
	private double mapX;
	/**
	 * @uml.property  name="mapY"
	 */
	private double mapY;
	
	public gpsLocation(double _X, double _Y){
		setMapX(_X);
		setMapY(_Y);
	}

	/**
	 * @return
	 * @uml.property  name="mapX"
	 */
	public double getMapX() {
		return mapX;
	}
	
	/**
	 * @param mapX
	 * @uml.property  name="mapX"
	 */
	public void setMapX(double mapX) {
		this.mapX = mapX;
	}
	
	/**
	 * @return
	 * @uml.property  name="mapY"
	 */
	public double getMapY() {
		return mapY;
	}
	
	/**
	 * @param mapY
	 * @uml.property  name="mapY"
	 */
	public void setMapY(double mapY) {
		this.mapY = mapY;
	}
	
	public double[] getXY(){
		double[] loc = { mapX, mapY };
		return loc;
	}
	

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(mapX + " " + mapY);
	}
}
