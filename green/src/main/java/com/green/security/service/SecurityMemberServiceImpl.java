package com.green.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.security.domain.SecurityMemberVO;
import com.green.security.mapper.SecurityMemberMapper;

@Service
public class SecurityMemberServiceImpl implements SecurityMemberService {
	
	@Autowired
	private SecurityMemberMapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void signUp(SecurityMemberVO vo) {
		// TODO Auto-generated method stub
		
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		mapper.insert(vo);
	}

	@Override
	public SecurityMemberVO getMember(String email) {
		// TODO Auto-generated method stub
		return mapper.getOne(email);
	}
	
	@Override
	public boolean delete(String userid) {
		// TODO Auto-generated method stub
		return mapper.delete(userid) == 1 ? true: false;
	}

	@Override
	public boolean updateName(String name, String email) {
		// TODO Auto-generated method stub
		SecurityMemberVO vo = getMember(email);
		vo.setName(name);
		return mapper.update(vo) == 1 ? true : false;
	}

	@Override
	public boolean updatePassword(String password, String email) {
		// TODO Auto-generated method stub
		SecurityMemberVO vo = getMember(email);
		vo.setPassword(passwordEncoder.encode(password));
		return mapper.update(vo) == 1 ? true : false;
	}

}
