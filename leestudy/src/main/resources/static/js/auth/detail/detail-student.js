history.replaceState({}, null, location.pathname)

const btnGoBack = document.querySelector('.btnGoBack') // 돌아가기 버튼
const addMatchingList = document.querySelector('.addMatchingList') // 성사리스트 추가버튼.

btnGoBack.onclick = () => { // 돌아가기 버튼 클릭시
    history.back();
}

addMatchingList.onclick = () => {
	if (confirm('서로 간 연락을 잘 주고 받으셨습니까?\n무분별한 성사는 자제 바랍니다.')) {
		checkMatchingIsExist();
	}
}

async function checkMatchingIsExist() {
	let url = `/api/v1/matching/checkmatching`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			student_name: studentName,
			teacher_name: teacherName,
		})
	};
	fetch(url, option)
		.then(response => { // 응답을 받음
			console.log(response);
			if (response.ok) { // 200~299 응답일시
				return response.json();
			} else {
				throw new Error(response.json());
			}
		})
		.then((result) => { 
			if (result.data == false) {
				saveMachingList();
			} else {
				alert("이미 서로간 과외 성사리스트에 등록되어 있습니다.")
			}
		})
		.catch(error => console.log(error));
}

async function saveMachingList() { // UserCommon DB저장 함수
	let url = `/api/v1/matching/savematching`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			student_name: studentName,
			teacher_name: teacherName,
		})
	};
	fetch(url, option)
		.then(response => { // 응답을 받음
			console.log(response);
			if (response.ok) { // 200~299 응답일시
				return response.json();
			} else {
				throw new Error(response.json());
			}
		})
		.then(() => { 
			if (confirm('성사리스트에 추가되었습니다.\n바로 확인해 보시겠습니까?')) {
				location.href="/auth/matching"
			}
		})
		.catch(error => console.log(error));
}