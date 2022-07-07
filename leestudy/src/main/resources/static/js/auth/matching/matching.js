const checkCircleStudent = document.querySelectorAll('.checkCircleStudent')
const checkCircleTeacher = document.querySelectorAll('.checkCircleTeacher')
const formMatchingContent = document.querySelectorAll('.form-matching-content')
/*
for(let i=0; i<matchinglist.length; i++) {
	if(matchinglist[i].matching_enddate != null) {
		console.log(formMatchingContent[i])
		const btnWriteReview = formMatchingContent[i].querySelector('.btnWriteReview')
		btnWriteReview.style.display = "none";
	}
}
*/
// 각각 isready == 1인 항목들 체크박스 색칠
for(let i=0; i<checkCircleStudent.length; i++) {
	if(matchinglist[i].student_isready == 1) {
		checkCircleStudent[i].style.borderColor = 'green'
		checkCircleStudent[i].style.color = 'black'
	}
}
for(let i=0; i<checkCircleTeacher.length; i++) {
	if(matchinglist[i].teacher_isready == 1) {
		checkCircleTeacher[i].style.borderColor = 'green'
		checkCircleTeacher[i].style.color = 'black'
	}
}

// 학생 및 선생에 따라 자기의 체크박스 클릭이벤트 정의
if (role == "USER_STUDENT") {
	for(let i=0; i<checkCircleStudent.length; i++) {
		if(matchinglist[i].student_isready == 0) {
			checkCircleStudent[i].style.cursor="pointer"
			checkCircleStudent[i].onclick = () => {
				if(confirm("확실합니까? 이후 다시 체크를 해제할 수 없습니다.")) {
					updateIsReady(matchinglist[i].student_name, matchinglist[i].teacher_name);
				}
			}
		}
	}

} else {
	for(let i=0; i<checkCircleTeacher.length; i++) {
		if(matchinglist[i].teacher_isready == 0) {
			checkCircleTeacher[i].style.cursor="pointer"
			checkCircleTeacher[i].onclick = () => {
				if(confirm("확실합니까? 이후 다시 체크를 해제할 수 없습니다.")) {
					updateIsReady(matchinglist[i].student_name, matchinglist[i].teacher_name);
				}
			}
		}
	}
}



async function updateIsReady(student_name, teacher_name) { // 회원정보 수정하기 - 공통사항 DB업데이트 함수
	let url = `/api/v1/matching/updateisready`;
	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"student_name": student_name,
			"teacher_name": teacher_name,
		})
	};
	fetch(url, option)
		.then(response => { // 응답을 받음
			if (response.ok) { // 200~299 응답일시
				return response.json();
			} else {
				throw new Error(response.json());
			}
		})
		.then((result) => { 
            if(result.data.length == 2) { // 학생, 선생 둘다 체크를 하였을경우
				alert("양 측 준비가 모두 완료되었습니다.\n지금부터 과외를 진행하오니\n꼭 상대측에 연락을 해주세요.")
			}
			window.location.reload()
         })
		.catch(error => console.log(error));
}

async function updateEndDate(student_name, teacher_name) {
	if(confirm("과외 성사가 정말로 종료된 것이 확실합니까?")) {
		let url = `/api/v1/matching/updateenddate`;
		let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"student_name": student_name,
			"teacher_name": teacher_name,
		})
	};
	fetch(url, option)
		.then(response => { // 응답을 받음
			if (response.ok) { // 200~299 응답일시
				return response.json();
			} else {
				throw new Error(response.json());
			}
		})
		.then(() => { 
            if(role == "USER_STUDENT") {
				alert("과와 성사가 종료되었습니다.\n소중한 리뷰 남겨주시면 감사하겠습니다.")
			} else {
				alert("과와 성사가 종료되었습니다.\n학생분 측에서 리뷰를 남겨주신다면, 이곳에서 리뷰확인 버튼이 등장합니다.")
			}
			window.location.reload()
         })
		.catch(error => console.log(error));
	}
}

async function deleteMatchingByStudentTeacherName(student_name, teacher_name) {
	if(confirm("정말 삭제하시겠습니까?")) {
		let url = `/api/v1/matching/deletematching`;
		let option = {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				"student_name": student_name,
				"teacher_name": teacher_name
			})
		};
		await fetch(url, option)
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					throw new Error("비동기 처리 오류");
				}
			})
			.then(() => {
				alert("삭제가 완료되었습니다.")
				window.location.reload()
			})
			.catch(error => { console.log(error) });
	}

}

function gotoTeacherDetail(teacher_name) {
	location.href = `/auth/detail/teacher?username=${username}`
}

function gotoStudentDetail(student_name) {
	location.href = `/auth/detail/student?username=${username}`
}

function reviewWrite(matching_code) {
	location.href = `/auth/review/write?matching_code=${matching_code}`
}
function reviewSee(matching_code) {
	//location.href = `/auth/reviewone?matching_code=${matching_code}`
	window.open(`/auth/reviewone?matching_code=${matching_code}`,'window_name','width=800,height=710,location=no,status=no,scrollbars=yes')
}