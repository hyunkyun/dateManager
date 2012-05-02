package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class subFacility {
	/**
	 * @uml.property  name="seminar"
	 */
	private boolean seminar;
	/**
	 * @uml.property  name="sports"
	 */
	private boolean sports;
	/**
	 * @uml.property  name="sauna"
	 */
	private boolean sauna;
	/**
	 * @uml.property  name="beauty"
	 */
	private boolean beauty;
	/**
	 * @uml.property  name="beverage"
	 */
	private boolean beverage;
	/**
	 * @uml.property  name="karaoke"
	 */
	private boolean karaoke;
	/**
	 * @uml.property  name="barbecue"
	 */
	private boolean barbecue;
	/**
	 * @uml.property  name="campfire"
	 */
	private boolean campfire;
	/**
	 * @uml.property  name="bicycle"
	 */
	private boolean bicycle;
	/**
	 * @uml.property  name="fitness"
	 */
	private boolean fitness;
	/**
	 * @uml.property  name="publicPc"
	 */
	private boolean publicPc;
	/**
	 * @uml.property  name="publicBath"
	 */
	private boolean publicBath;
	
	public subFacility(boolean[] _subFacility){
		if(_subFacility.length >= 12){
			setSeminar(_subFacility[0]);
			setSports(_subFacility[1]);
			setSauna(_subFacility[2]);
			setBeauty(_subFacility[3]);
			setBeverage(_subFacility[4]);
			setKaraoke(_subFacility[5]);
			setBarbecue(_subFacility[6]);
			setCampfire(_subFacility[7]);
			setBicycle(_subFacility[8]);
			setFitness(_subFacility[9]);
			setPublicPc(_subFacility[10]);
			setPublicBath(_subFacility[11]);
		}
		else return;
	}
	
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("seminar : " + seminar + " sports : " + sports + " sauna : " + sauna + 
				" beauty : " + beauty + " beverage : " + beverage + " karaoke : " + karaoke +
				" barbecue : " + barbecue + " campfire : " + campfire + " bicycle : " + bicycle +
				" fitness : " + fitness + " publicPc : " + publicPc + " publicBath : " + publicBath);
	}
	/**
	 * @return
	 * @uml.property  name="seminar"
	 */
	public boolean isSeminar() {
		return seminar;
	}
	/**
	 * @param seminar
	 * @uml.property  name="seminar"
	 */
	public void setSeminar(boolean seminar) {
		this.seminar = seminar;
	}
	/**
	 * @return
	 * @uml.property  name="sports"
	 */
	public boolean isSports() {
		return sports;
	}
	/**
	 * @param sports
	 * @uml.property  name="sports"
	 */
	public void setSports(boolean sports) {
		this.sports = sports;
	}
	/**
	 * @return
	 * @uml.property  name="beauty"
	 */
	public boolean isBeauty() {
		return beauty;
	}
	/**
	 * @param beauty
	 * @uml.property  name="beauty"
	 */
	public void setBeauty(boolean beauty) {
		this.beauty = beauty;
	}
	/**
	 * @return
	 * @uml.property  name="beverage"
	 */
	public boolean isBeverage() {
		return beverage;
	}
	/**
	 * @param beverage
	 * @uml.property  name="beverage"
	 */
	public void setBeverage(boolean beverage) {
		this.beverage = beverage;
	}
	/**
	 * @return
	 * @uml.property  name="karaoke"
	 */
	public boolean isKaraoke() {
		return karaoke;
	}
	/**
	 * @param karaoke
	 * @uml.property  name="karaoke"
	 */
	public void setKaraoke(boolean karaoke) {
		this.karaoke = karaoke;
	}
	/**
	 * @return
	 * @uml.property  name="barbecue"
	 */
	public boolean isBarbecue() {
		return barbecue;
	}
	/**
	 * @param barbecue
	 * @uml.property  name="barbecue"
	 */
	public void setBarbecue(boolean barbecue) {
		this.barbecue = barbecue;
	}
	/**
	 * @return
	 * @uml.property  name="campfire"
	 */
	public boolean isCampfire() {
		return campfire;
	}
	/**
	 * @param campfire
	 * @uml.property  name="campfire"
	 */
	public void setCampfire(boolean campfire) {
		this.campfire = campfire;
	}
	/**
	 * @return
	 * @uml.property  name="bicycle"
	 */
	public boolean isBicycle() {
		return bicycle;
	}
	/**
	 * @param bicycle
	 * @uml.property  name="bicycle"
	 */
	public void setBicycle(boolean bicycle) {
		this.bicycle = bicycle;
	}
	/**
	 * @return
	 * @uml.property  name="fitness"
	 */
	public boolean isFitness() {
		return fitness;
	}
	/**
	 * @param fitness
	 * @uml.property  name="fitness"
	 */
	public void setFitness(boolean fitness) {
		this.fitness = fitness;
	}
	/**
	 * @return
	 * @uml.property  name="publicPc"
	 */
	public boolean isPublicPc() {
		return publicPc;
	}
	/**
	 * @param publicPc
	 * @uml.property  name="publicPc"
	 */
	public void setPublicPc(boolean publicPc) {
		this.publicPc = publicPc;
	}
	/**
	 * @return
	 * @uml.property  name="publicBath"
	 */
	public boolean isPublicBath() {
		return publicBath;
	}
	/**
	 * @param publicBath
	 * @uml.property  name="publicBath"
	 */
	public void setPublicBath(boolean publicBath) {
		this.publicBath = publicBath;
	}
	/**
	 * @return
	 * @uml.property  name="sauna"
	 */
	public boolean isSauna() {
		return sauna;
	}
	/**
	 * @param sauna
	 * @uml.property  name="sauna"
	 */
	public void setSauna(boolean sauna) {
		this.sauna = sauna;
	}
}
