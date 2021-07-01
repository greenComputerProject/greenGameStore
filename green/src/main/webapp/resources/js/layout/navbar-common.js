let currUrl = window.location.href;

console.log(currUrl);
let links = document.querySelectorAll(".navbar-search-links a");

if(currUrl.includes("browse")){
	links[0].setAttribute("id", "not-selected");
	links[1].setAttribute("id", "selected");
}


console.log("ready!");

let userName  = document.querySelectorAll(".dropdown-button a p")[0];
let dropMenu = document.querySelectorAll(".dropdown-menu ul")[0];

let authorizationRequestBaseUri = "oauth2/authorization";
let listStart = '<li class="dropdown-menu-link">';
let listEnd = "</li>";
let menuHtml = "";

$.ajax({
	

})

let wishlistBox = document.querySelectorAll(".navbar-search-wishlist")[0];
let html = "";

let wishlistCount = function(){

	$.ajax({
		type: 'GET',
		url: '/wishlist/count',
		dataType: 'json',
		success: function(result){
			console.log(result);
			html +=  '<li><a href="/wishlist">위시리스트</a></li>'
					+ '<li><p>'+ result+'</p></li>';
			console.log(wishlistBox);
			wishlistBox.innerHTML = html;
		},
		error: function(error){
			html +=  '<li><a href="/wishlist">위시리스트</a></li>'
			+ '<li><p>'+ 0+'</p></li>';
			wishlistBox.innerHTML = html;
		}
	});
}



let checkUser = function(){
	$.ajax({
		type: 'GET',
	url: '/user',
	dataType: 'json',
	type: 'GET',
	url: '/user',
	dataType: 'json',
	success : function(result){
		userName.innerHTML = result.name;
		menuHtml = '<li class="dropdown-menu-link"><a href="#">계정</a></li>'
<<<<<<< HEAD
		+ '<li class="dropdown-menu-link"><a href="#">코드 사용</a></li>'
		+ '<li class="dropdown-menu-link"><a href="#">상품권</a></li>'
=======
		+ '<li class="dropdown-menu-link"><a href="/library">라이브러리</a></li>'
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
		+ '<li class="dropdown-menu-link"><a href="/wishlist">위시리스트</a></li>'
		+ '<li class="dropdown-menu-link"><a href="/logout">로그아웃</a></li>';
		dropMenu.innerHTML = menuHtml;
		wishlistCount();
	},
	error : function(error){
		userName.innerHTML = '로그인';
		menuHtml = '<li class="dropdown-menu-link" style="margin-bottom: 10%"><a href="' + authorizationRequestBaseUri + '/google' + '">구글</a></li>'
		+ '<li class="dropdown-menu-link" style="margin-top: 10%"><a href="' + authorizationRequestBaseUri + '/facebook' + '">페이스북</a></li>';
		let dropMenuDiv = document.querySelectorAll(".dropdown-menu")[0];
		dropMenuDiv.setAttribute("style", "height: 15%;");
		dropMenu.innerHTML = menuHtml;
	}
	});
}

checkUser();