package com.green.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.domain.GameListVO;
import com.green.domain.WishlistListVO;
import com.green.oauth2.domain.SessionUser;
import com.green.service.GameListService;
import com.green.service.WishlistService;
import com.green.utils.Criteria;
import com.green.utils.PageMaker;
<<<<<<< HEAD
=======
import com.green.utils.SortFilterSearch;
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class IndexController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private HttpSession session;
	
	private static final String authorizationRequestBaseUri = "oauth2/authorization";
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("games", gameListService.getListForIndex());
		
		SessionUser user = (SessionUser)session.getAttribute("user");
		if(user != null) {
			String userId = user.getUserid();
<<<<<<< HEAD
			System.out.println("userid========> " + userId);
			System.out.println("wishlistCount  ========> " + wishlistService.getCount(userId));
=======
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
			model.addAttribute("wishlistCount" , wishlistService.getCount(userId));
			
			List<WishlistListVO> wishlist = wishlistService.getList(userId);
			 model.addAttribute("wishlistGames", wishlistService.getList(userId));

		} else {
			String[] registrationIds = {"google", "facebook"};
			
			for(int i=0 ; i<registrationIds.length ; i++) {
				
				model.addAttribute(registrationIds[i], authorizationRequestBaseUri + "/" + registrationIds[i]);
				
			}
		}
		
		
		
		return "index";
	}
	
<<<<<<< HEAD
	//navbar include test
	@GetMapping("/browse")
	public String browse(Model model) {
		Criteria cri = new Criteria();
		List<GameListVO> games = gameListService.getList(cri, null);
		
		int totalCount = gameListService.getCount(cri, null);
=======
	@GetMapping("/browse")
	public String browse(Model model, Criteria cri , SortFilterSearch sort) {
		
		if(cri == null) {
			cri = new Criteria();
		}
		log.info(sort.toString());
		
		List<GameListVO> games = gameListService.getList(cri, sort);
		
		int totalCount = gameListService.getCount(cri, sort);
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("games", games);
		model.addAttribute("pageMaker", pageMaker);
		System.out.println(games.size());
		return "/game/list";
	}
	
<<<<<<< HEAD

=======
	
	@GetMapping("/login")
	public void login() {}
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895

}
