/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package javastory.club.stage2.step42.domain;

import javastory.club.stage2.util.DateUtil;

public class ClubMember extends Identity {
	//
	private String name; 
	private String nickname; 
	private String phoneNumber; 
	private String birthDay; 
	private RoleInClub role; 
	
	public ClubMember(String email, String name, String phoneNumber) {
		// 
		super(email); 
		this.name = name; 
		this.nickname = name; 
		this.phoneNumber = phoneNumber;
		this.birthDay = DateUtil.today(); 
		this.role = RoleInClub.Member; 
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("Name:").append(name); 
		builder.append(", email:").append(getId()); 
		builder.append(", nickname:").append(nickname); 
		builder.append(", phone number:").append(phoneNumber); 
		builder.append(", birthDay:").append(birthDay); 
		builder.append(", role:").append(role); 
		
		return builder.toString(); 
	}
	
	public String getEmail() {
		return getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public RoleInClub getRole() {
		return role;
	}

	public void setRole(RoleInClub role) {
		this.role = role;
	}
}
