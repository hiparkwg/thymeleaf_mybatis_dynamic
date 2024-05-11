/* 검색 */
let btnSearch = document.querySelector('.btnSearch')
btnSearch.addEventListener('click', ()=>{
    let frm = document.frmSearch
   frm.action="/member_search"
   frm.submit();
});