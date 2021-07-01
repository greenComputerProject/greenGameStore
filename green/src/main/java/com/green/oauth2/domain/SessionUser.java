package com.green.oauth2.domain;

import java.io.Serializable;

<<<<<<< HEAD
=======
import com.green.security.domain.SecurityMemberVO;

>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
import lombok.Getter;


@Getter
public class SessionUser implements Serializable{
	
	private String userid;
	private String name;
	private String email;
	private String picture;
	
	public SessionUser(OAuthUserVO user) {
		this.userid = user.getUserid();
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
<<<<<<< HEAD
=======
	
	public SessionUser(SecurityMemberVO user) {
		this.userid = user.getUserid();
		this.name = user.getName();
		this.email = user.getEmail();
	}
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895

}
