history.replaceState({}, null, location.pathname)

const btnGoBack = document.querySelector('.btnGoBack') // 돌아가기 버튼
btnGoBack.onclick = () => { // 돌아가기 버튼 클릭시
    history.back();
}