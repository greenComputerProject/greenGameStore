let clickSearch = function(link){
    let id = link.getAttribute("id");
    if(id === "selected"){
        return;
    }else {
        let selectedLink = document.querySelectorAll(".navbar-search-links #selected")[0];
        selectedLink.setAttribute("id", "not-selected");
        link.setAttribute("id", "selected"); 
    }

<<<<<<< HEAD
}
=======
}


>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
