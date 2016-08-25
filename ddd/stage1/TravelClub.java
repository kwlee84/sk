/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package javastory.club.stage1.step42.domain;

public class TravelClub extends Identity {
	//
	private String name; 
	private String intro; 
	
	public TravelClub(String id) {
		super(id); 
	}
	
	public TravelClub(String name, String intro) {
		//
		super(); 
		this.name = name;
		this.intro = intro;
	}
	
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("id:").append(getId()); 
		builder.append(", name:").append(name); 
		builder.append(", intro:").append(intro); 
		
		return builder.toString(); 
	}
	
	public static TravelClub getSample() {
		// 
		String name = "JavaTravel"; 
		String intro = "We're the club to the Java island."; 
		
		TravelClub sample = new TravelClub(name, intro); 
		
		return sample; 
	}
	
	public String getName() {
		return name; 
	}
	
	public String getIntro() {
		return intro; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setIntro(String intro) {
		this.intro = intro; 
	}
	
	public static void main(String[] args) {
		// 
		System.out.println("Sample: " + TravelClub.getSample()); 
	}
}