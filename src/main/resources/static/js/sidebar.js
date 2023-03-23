
    let sidebarOn = document.querySelector('.sidebar-ul');
    let clickedClass = "on";
    function sidebarClick(){
    sidebarOn.classList.toggle(clickedClass);
}
    sidebarOn.addEventListener("click", sidebarClick);

    // document.querySelector('#sidebar-ul').addEventListener('click', ()=>{
    //     document.querySelector('#sidebar-ul').classList.toggle('on');
    // })
