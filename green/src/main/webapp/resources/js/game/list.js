let order = document.querySelectorAll(".order-form .title")[0];
order.style.border = "1px solid rgb(121, 121, 121)";

let flag = false;

let imageLotateAnimation = function(image){
    
    image.animate(
        [{
            transform : "rotate(0deg)"
        },{
            transform : "rotate(180deg)"
        }], 200
    )
}
let imageLotateAnimationReverse = function(image){
    
    image.animate(
        [{
            transform : "rotate(180deg)"
        },{
            transform : "rotate(0deg)"
        }], 200
    )
}

let clickOrderTitle = function(){
    flag = !flag;
    let order = document.querySelectorAll(".order-form .title")[0];
    let fontAwesome = order.getElementsByTagName("i")[0];
    let dropdown = document.querySelectorAll(".order-form .list")[0];

    if(flag){
        order.style.border = "";
        dropdown.style.display = "block";
        imageLotateAnimation(fontAwesome);
        fontAwesome.setAttribute("id", "selected")
    } else {
        order.style.border = "1px solid rgb(121, 121, 121)";
        dropdown.style.display = "none";
        imageLotateAnimationReverse(fontAwesome);
        fontAwesome.setAttribute("id", "");
    }
    
}
let clickOrderButton = function(button){
    console.log(button.textContent)
    clickOrderTitle();
    let orderDisplay = document.querySelectorAll(".order-form .title span")[0];
    console.log(orderDisplay);
    orderDisplay.textContent = button.textContent;
}

<<<<<<< HEAD


let clickCategory = function(category){
=======
let clickCategory = function(categoryTitle){
    let category = categoryTitle.parentNode;
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
    let image = category.getElementsByTagName("i")[0];
    
    if(category.getAttribute("class") === "category-selected"){
        category.setAttribute("class", "category")
        imageLotateAnimationReverse(image);
    } else {
        category.setAttribute("class", "category-selected");
        
        imageLotateAnimation(image);
    }
}

<<<<<<< HEAD
=======

>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
let addWishlist = function(gno){

    $.ajax({
        type: 'POST',
        url: '/wishlist/' + gno,
        success: function(result){
            location.reload();
        }, 
        error: function(error){
            alert("위시리스트에 이미 게임이 등록돼있어요!");
        }
    })
}

let deleteWishlist = function(gno){

    $.ajax({
        type: 'DELETE',
        url: '/wishlist/delete/' + gno,
        dataType: 'json',
        success: function(result){
            location.reload();
        }
    })
}


let gnoList = document.querySelectorAll(".gno");

let getWishlist = function(){

    console.log("getWishlist")

    $.ajax({
        type: 'GET',
        url: '/wishlist/get',
        dataType: 'json',
        success: function(result){
          
            if( result != null){
                for(i=0 ; i<result.length; i++){
                    for(j=0 ; j<gnoList.length ; j++){
                        if(result[i].gno == gnoList[j].textContent){
                            console.log( result[i].gno + " ?= " + gnoList[j].textContent)

                            let gno = result[i].gno
                            let fontA = document.querySelectorAll(".gno" + gno + " i")[0]
                            
                            fontA.setAttribute("class", "fas fa-check-circle")
                            fontA.setAttribute("onclick", "deleteWishlist(this.id)");
                        } 
                    }
                }
            }
            
        },
        error: function(error){
            alert(error);
        }
    })
}

let fontAwesomeSetting = function(className, funcName){
    let fontAwesome = document.querySelectorAll(".games ul i");
    for(i=0; i< fontAwesome.length ; i++){
        let font = fontAwesome[i];
        font.setAttribute("class", className);
        font.setAttribute("onclick", funcName);
    }
}

let getSessionUser = function(){
    
    $.ajax({
        type: 'GET',
        url: '/user',
        dataType: 'json',
        success: function(result){
            fontAwesomeSetting("fas fa-plus-circle", "addWishlist(this.id)")
            getWishlist();
        }, 
        error : function(error){
            fontAwesomeSetting("fas fa-plus-circle", "alert('위시리스트에 추가하려면 로그인하세요!')")
        }
    })
}

getSessionUser();