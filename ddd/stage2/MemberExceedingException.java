package javastory.club.stage2.step42.domain;

public class MemberExceedingException extends RuntimeException {
	//
	private static final long serialVersionUID = -7813224989470142707L;

	public MemberExceedingException(String message) {
		super(message); 
	}
}