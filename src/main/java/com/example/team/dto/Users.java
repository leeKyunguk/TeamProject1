package com.example.team.dto;

import lombok.Data;

@Data
public class Users {
	
	

	 // 사용자 ID
    private String usersId;

    // 비밀번호
    private String password;

    // 사용자 이메일
    private String email;
    
    private String role;

	/*
	 * public enum Role { JOB_SEEKER, // 구직자 COMPANY; // 기업
	 * 
	 * // String 값을 Role로 변환하는 메서드 public static Role fromString(String role) {
	 * switch (role) { case "구직자": return JOB_SEEKER; case "기업": return COMPANY;
	 * default: throw new IllegalArgumentException("Unknown role: " + role); } }
	 * 
	 * // toString 메서드 수정 (옵션)
	 * 
	 * @Override public String toString() { switch (this) { case JOB_SEEKER: return
	 * "구직자"; case COMPANY: return "기업"; default: return super.toString(); } } }
	 * private Role role;
	 */
    
    // 주소 (기업은 기업 주소)
    private String address;

}
