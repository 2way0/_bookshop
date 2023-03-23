
    // 홈 돌아가기
    const home = document.querySelector('.header-main');
    home.addEventListener("click",function (){
    console.log("홈");
    location.href="/";
});



    // 로그인 팝업창
    const loginBtn = document.querySelector('.login-window');

    loginBtn.addEventListener("click", function(){
    window.open("/members/login", "loginPopup", "width=300, height=400, left=0, top=0");
});





    // 세션
    let loginCheck= document.querySelector(".login-sessionCheck").value;
    let loginCheckSuccess= document.querySelector(".session-on");
    let loginCheckFail=document.querySelector(".session-off");

    console.log(loginCheck);
    if(loginCheck===("")){
        loginCheckSuccess.style.display="none"
    }else{
        loginCheckFail.style.display="none"
    }

    //로그인 체크 끝




    // 사이드바
/*
    function win_open(page, name) {
    // window.open("팝업될 문서 경로","팝업될 문서 이름","옵션(위치, bar표시, 크기 등)");
    window.open(page,name,"width=300, height=400, left=300px, top=100px");
}


    let sidebarOn = document.querySelector('.sidebar-ul');
    let clickedClass = "on";
    function sidebarClick(){
        sidebarOn.classList.toggle(clickedClass);
    }
    sidebarOn.addEventListener("click", sidebarClick);

    // document.querySelector('#sidebar-ul').addEventListener('click', ()=>{
    //     document.querySelector('#sidebar-ul').classList.toggle('on');
    // })*/
