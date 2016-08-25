/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package javastory.club.stage2.step42.domain;

import java.util.ArrayList;
import java.util.List;

import javastory.club.stage2.util.DateUtil;

public class TravelClub extends Identity {
	//
	private static final int MAXIMUM_MEMBER_COUNT = 25; 
	private static final int MINIMUM_NAME_LENGTH =  3; 
	private static final int MINIMUM_INTRO_LENGTH =  10; 
	
	private String name; 
	private String intro;
	private String foundationDay; 
	
	private List<ClubMember> members; 
	
	public TravelClub(String id) {
		// 
		super(id); 
		this.members = new ArrayList<ClubMember>(); 
	}
	
	public TravelClub(String name, String intro) {
		// 
		super(); 
		this.setName(name);
		this.setIntro(intro);
		this.foundationDay = DateUtil.today(); 
		this.members = new ArrayList<ClubMember>(); 
	}

	public TravelClub(String name, String intro, String foundationDay) {
		// 
		this(name, intro); 
		this.foundationDay = foundationDay;
	}
	
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("Travel club name:").append(name); 
		builder.append(", intro:").append(intro); 
		builder.append(", foundation day:").append(foundationDay);
		builder.append(", members:").append(members.toString());  
		
		return builder.toString(); 
	}
	
	public ClubMember addMember(String email, String name, String phoneNumber) {
		// 
		if(members.size() > MAXIMUM_MEMBER_COUNT) {
			throw new MemberExceedingException("member count: " + members.size());  
		}
		
		ClubMember member = new ClubMember(email, name, phoneNumber); 
		this.members.add(member); 
		
		return member; 
	}
	
	public ClubMember getMember(String memberId) {
		// 
		ClubMember foundMember = null; 
		
		for(ClubMember member : members) {
			// 
			if (member.getId().equals(memberId)) {
				foundMember = member; 
				break; 
			}
		}
		
		return foundMember; 
	}
	
	public List<ClubMember> getMembers() {
		return members; 
	}
	
	public String getName() {
		return name; 
	}
	
	public String getIntro() {
		return intro; 
	}
	
	public String getFoundationDay() {
		return foundationDay; 
	}
	
	public void setFoundationDay(String foundationDay) {
		//
		this.foundationDay = foundationDay; 
	}
	
	public void setName(String name) {
		// 
		if (name.length() < MINIMUM_NAME_LENGTH) {
			// 
			throw new IllegalArgumentException("Name should be longer than " + MINIMUM_NAME_LENGTH); 
		}
		
		this.name = name; 
	}
	
	public void setIntro(String intro) {
		//
		if (intro.length() < MINIMUM_INTRO_LENGTH) {
			// 
			throw new IllegalArgumentException("Intro should be longer than " + MINIMUM_INTRO_LENGTH); 
		}

		this.intro = intro; 
	}
}