package com.green.security.service;

import com.green.security.domain.SecurityMemberVO;

public interface SecurityMemberService {
	
	public void signUp(SecurityMemberVO vo);
	public SecurityMemberVO getMember(String email);
	public boolean updateName(String name, String userid);
	public boolean updatePassword(String password, String userid);
	public boolean delete(String userid);


}
