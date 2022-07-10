let usernameCheckFlag = false;
let phoneNumCheckFlag = false;
let noUniversityFlag = false;
let sendSMSNum;

const inputUsername = document.querySelector('.inputUsername') // 1. 아이디 입력란
const inputPassword = document.querySelector('.inputPassword') // 2-1. 비밀번호 입력란
const inputPasswordAgain = document.querySelector('.inputPasswordAgain') // 2-2. 비밀번호 재입력란
const inputNickname = document.querySelector('.inputNickname') // 3. 닉네임 입력란
const inputEmail = document.querySelector('.inputEmail') // 4. 이메일 입력란
let radioAvailableRemoteValue = "" // 5. 과외원격여부 라디오버튼 값
let radioAvailableDemonstrationValue = "" // 6. 시범과외 가능여부 라디오버튼 값
let selectTeacherAddr1Value = "" // 7-1. 주소(시) 값
let selectTeacherAddr2Value = "" // 7-2. 주소(구) 값
let radioGenderValue = 0 // 8. 성별 라디오버튼 값
let selectTeacherAgeValue = ""; // 9. 나이 값
let selectTeacherPersonality = []; // 10-1. 선택한 성격 배열
let selectTeacherPersonalityValue = "" // 10-2. 선택한 성격 값
let selectTeacherPriceValue = ""; // 11. 최소 페이 값
const inputDetailPrice = document.querySelector('.inputDetailPrice') // 12. 자세한 수업료 기준 입력란
let selectTeacherSubject = []; // 13-1. 선택한 과목 배열
let selectTeacherSubjectValue = "" // 13-2. 과목 값
let selectUniversityValue = "" // 14. 출신대학 값
const inputUniversityMajor = document.querySelector('.inputUniversityMajor') // 15. 대학전공 입력란
let selectUniversityStudentnumValue = "" // 16. 대학학번 값
let selectUniversityIsgraduateValue = "" // 17. 졸업여부 값
const inputIntroduction = document.querySelector('.inputIntroduction') // 18. 한줄소개 입력란
const inputAvailableTime = document.querySelector('.inputAvailableTime') // 19. 과외 가능 요일/시간 입력란
const inputTeachingDetail = document.querySelector('.inputTeachingDetail') // 20. 과목별 수업내용 입력란
const inputTeachingStyle = document.querySelector('.inputTeachingStyle') // 21. 과외 스타일 입력란
const inputPhonenum1 = document.querySelector('.inputPhonenum1') // 22-1. 전화번호 입력란
const inputPhonenum2 = document.querySelector('.inputPhonenum2') // 22-2. 인증번호 입력란

const btnCheckUsername = document.querySelector('.btnCheckUsername') // 중복 조회하기 버튼
const btnPhoneNumCheck1 = document.querySelector('.btnPhoneNumCheck1') // 인증번호 요청 버튼
const btnPhoneNumCheck2 = document.querySelector('.btnPhoneNumCheck2') // 인증완료 버튼
const btnTeacherJoinComplete = document.querySelector('.btnTeacherJoinComplete') // 가입완료 버튼

btnTeacherJoinComplete.onclick = async () => { // 가입완료 버튼 클릭시
	let checker = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] // 입력사항 검사기. 모두 1이 되어야 가입 완료 가능
	if (usernameCheckFlag == true) { checker[1] = 1 } else { checker[1] = 0 } // 1. 아이디 입력란
	if (inputPassword.value.length > 7 && inputPassword.value == inputPasswordAgain.value) { checker[2] = 1 } else { checker[2] = 0 } // 2. 비밀번호 입력란
	if (inputNickname.value.length > 2 && inputNickname.value.length < 11)  { checker[3] = 1 } else { checker[3] = 0 } // 3. 닉네임 입력란
	if (inputEmail.value.includes('@') && inputEmail.value.length < 46) { checker[4] = 1 } else { checker[4] = 0 } // 4. 이메일 입력란
	if (radioAvailableRemoteValue != "") { checker[5] = 1 } else { checker[5] = 0 } // 5. 과외원격여부 라디오버튼 값
    if (radioAvailableDemonstrationValue != "") { checker[6] = 1 } else { checker[6] = 0 } // 6. 시범과외 여부 라디오버튼 값
    if (selectTeacherAddr1Value != "" && selectTeacherAddr2Value != "") { checker[7] = 1 } else { checker[7] = 0 } // 7. 주소 값
    if (radioGenderValue != 0) { checker[8] = 1 } else { checker[8] = 0 } // 8. 성별 값
    if (selectTeacherAgeValue != "") { checker[9] = 1 } else { checker[9] = 0 } // 9. 나이 값
    if (selectTeacherPersonality.length != 0) { checker[10] = 1 } else { checker[10] = 0 } // 10. 성격 값
    if (selectTeacherPriceValue != "") { checker[11] = 1 } else { checker[11] = 0 } // 11. 최소 페이 값
    if (inputDetailPrice.value.length > 29 && inputDetailPrice.value.length < 301)  { checker[12] = 1 } else { checker[12] = 0 } // 12. 자세한 수업료 기준 입력란
    if (selectTeacherSubject.length != 0) { checker[13] = 1 } else { checker[13] = 0 } // 13. 과목 값
    if (selectUniversityValue != "" || noUniversityFlag == true) { checker[14] = 1 } else { checker[14] = 0 } // 14. 출신대학 값
    if ((inputUniversityMajor.value.length > 3 && inputUniversityMajor.value.length < 21) || noUniversityFlag == true)  { checker[15] = 1 } else { checker[15] = 0 } // 15. 대학전공 입력란
    if (selectUniversityStudentnumValue != "" || noUniversityFlag == true) { checker[16] = 1 } else { checker[16] = 0 } // 16. 대학학번 값
    if (selectUniversityIsgraduateValue != "" || noUniversityFlag == true) { checker[17] = 1 } else { checker[17] = 0 } // 17. 졸업여부 값
    if (inputIntroduction.value.length < 51)  { checker[18] = 1 } else { checker[18] = 0 } // 18. 한줄소개 입력란
    if (inputAvailableTime.value.length > 9 && inputAvailableTime.value.length < 101)  { checker[19] = 1 } else { checker[19] = 0 } // 19. 과외 가능 요일/시간 입력란
    if (inputTeachingDetail.value.length > 99 && inputTeachingDetail.value.length < 301)  { checker[20] = 1 } else { checker[20] = 0 } // 20. 과목별 수업내용 입력란
    if (inputTeachingStyle.value.length > 49 && inputTeachingStyle.value.length < 301)  { checker[21] = 1 } else { checker[21] = 0 } // 21. 과외 스타일 입력란
	if (phoneNumCheckFlag == true) { checker[22] = 1 } else { checker[22] = 0 } // 22. 전화번호 입력란
	
	const formSignupTeachers = document.querySelectorAll('.form-signup-teacher');
	let allIsOK = true;
	
	for (let i in checker) { // 입력사항들 검사
		if(checker[i]==0) {
			formSignupTeachers[i].style.borderColor = "red"; // 입력사항을 완료하지 못한 곳 빨간테두리
			allIsOK = false;
		} else {
			formSignupTeachers[i].style.borderColor = "lightgrey"; // 입력사항을 완료한 곳 회색테두리
		}
	}
	
	if (allIsOK == false) { // 빨간 테두리가 하나라도 있다면
		alert("입력사항이 모두 완료되지 않았습니다.\n입력사항중 테두리가 빨간색인것을 찾아 완료하세요.")
	} else {
		if(confirm("입력사항 대로 가입을 진행하시겠습니까?")) {
            selectTeacherPersonalityValue = arrayToString(selectTeacherPersonality);
			selectTeacherSubjectValue = arrayToString(selectTeacherSubject);
			await saveUserCommon("USER_TEACHER");
		}
	}
}

btnCheckUsername.onclick = async () => { // 아이디 중복조회 버튼 클릭시
	var blank_pattern = /[\s]/g;
	if (inputUsername.value.length < 5) {
		alert("아이디는 6자리 이상 입력 바랍니다.")
	} else if(blank_pattern.test(inputUsername.value) == true) {
		alert("공백 없이 입력 바랍니다.")
	} else {
		let idResult = await usernameCheck(inputUsername.value);
		if (idResult == true) {
			if(confirm("사용할 수 있는 아이디입니다.\n이 아이디를 사용하시겠습니까?\n'확인' 을 누른 뒤 바꾸려면 회원가입을 다시 진행해 주십시오.")) {
				usernameCheckFlag = true;
				inputUsername.disabled=true;
				btnCheckUsername.disabled=true;
			}
		}
	}
}

btnPhoneNumCheck1.onclick = async () => { // 인증번호 요청 버튼 클릭시
	if (inputPhonenum1.value.length != 11) {
		alert('전화번호는 숫자 11자로 입력 바랍니다.')
	} else {
		let phoneResult = await phonenumCheckTeacher(inputPhonenum1.value);
		if (phoneResult == true) {
			if(confirm("이 전화번호가 확실합니까?\n'확인' 을 누른 뒤 바꾸려면 회원가입을 다시 진행해 주십시오.")) {
				inputPhonenum1.disabled = true;
				alert("인증번호가 발송되었습니다.\n인증번호 입력란에 입력후 인증완료 버튼을 눌러주세요.")
				inputPhonenum2.disabled = false; // 인증번호 입력란 잠금해제
				btnPhoneNumCheck2.disabled = false; // 인증완료 버튼 잠금해제
				sendSMSNum = await sendSMS(inputPhonenum1.value);
			}
		}
	}
}

btnPhoneNumCheck2.onclick = () => { // 인증완료 버튼 클릭시
	if (inputPhonenum2.value.length == 0) {
		alert('발송받은 인증번호를 입력해 주세요.')
	} else if (inputPhonenum2.value != sendSMSNum){
		alert('인증번호가 올바르지 않습니다.')
	} else {
		inputPhonenum2.disabled = true; // 인증번호 입력란 잠금
		btnPhoneNumCheck1.disabled = true; // 인증번호 요청 버튼 잠금
		btnPhoneNumCheck2.disabled = true; // 인증완료 버튼 잠금
		phoneNumCheckFlag = true;
		alert("인증이 완료되었습니다.")
	}
}

async function usernameCheck(username) { // 아이디 중복체크 함수
	const url = `/api/v1/auth/join-common/username?username=${username}`;
	let responseData = false;
	
	await request(url)
	.then(result => { // 중복이 아닐시
		responseData = result.data; // result.data가 중복 여부 boolean
	})
	.catch(error => { // 중복일시
		alert("중복된 아이디입니다");
		console.log(error);
	})
	return responseData; // 최종 중복 여부의 boolean
}

async function phonenumCheckTeacher(teacher_phonenum) { // 전화번호 중복체크 함수
	const url = `/api/v1/auth/join-teacher/phonenum?teacher_phonenum=${teacher_phonenum}`;
	let responseData = false;
	
	await request(url)
	.then(result => { // 중복이 아닐시
		responseData = result.data; // result.data가 중복 여부 boolean
	})
	.catch(error => { // 중복일시
		alert("중복된 전화번호입니다");
		console.log(error);
	})
	return responseData; // 최종 중복 여부의 boolean
}

async function sendSMS(phoneNum) { // 인증번호 전송 함수
	const url = `/api/v1/auth/join-common/sendSMS?phoneNumber=${phoneNum}`;
	let responseData = false;
	
	await request(url)
	.then(result => {
		responseData = result;
	})
	.catch(error => {
		alert("인증번호 요청 중 오류가 발생하였습니다.\n다시 한 번 인증번호 요청 버튼을 눌러주세요.");
		console.log(error);
	})
	return responseData; // 최종 중복 여부의 boolean
}

async function saveUserCommon(role) { // UserCommon DB저장 함수
	let url = `/api/v1/auth/join-common/${role}`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			username: inputUsername.value,
			password: inputPassword.value,
			nickname: inputNickname.value,
			gender: radioGenderValue,
			age: selectTeacherAgeValue,
			address_part1: selectTeacherAddr1Value,
			address_part2: selectTeacherAddr2Value
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
			saveUserTeacher(); // UserTeacher DB저장 함수 실행
		})
		.catch(error => console.log(error));
}

async function saveUserTeacher() { // UserTeacher DB저장 함수
	let url = `/api/v1/auth/join-teacher`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			username: inputUsername.value,
            teacher_email: inputEmail.value,
            teacher_introduction: inputIntroduction.value,
            teacher_university: selectUniversityValue,
            teacher_university_isgraduate: selectUniversityIsgraduateValue,
            teacher_university_major: inputUniversityMajor.value,
            teacher_university_studentnum: selectUniversityStudentnumValue,
            teacher_phonenum: inputPhonenum1.value,
            teacher_price: selectTeacherPriceValue,
            teacher_detailprice: inputDetailPrice.value,
            teacher_personality: selectTeacherPersonalityValue,
            teacher_available_remote: radioAvailableRemoteValue,
            teacher_subject: selectTeacherSubjectValue,
            teacher_available_time: inputAvailableTime.value,
            teacher_teaching_detail: inputTeachingDetail.value,
            teacher_teaching_style: inputTeachingStyle.value,
            teacher_available_demonstration: radioAvailableDemonstrationValue,
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
			alert('이과외에 오신것을 환영합니다.')
			window.location.replace("/auth/login");
		})
		.catch(error => console.log(error));
}

function getRadioAvailableRemoteValue(event) { // 화상과외(온라인과외) 가능여부 값 get 함수
	radioAvailableRemoteValue = event.target.value;
}

function getRadioAvailableDemonstrationValue(event) { // 시범과외 가능여부 값 get 함수
	radioAvailableDemonstrationValue = event.target.value;
}

async function getSelectTeacherAddr1Value() { // 주소(시) 값 저장 함수 및 주소(구) 조회 함수
	const selectTeacherAddr1 = document.querySelector('.selectTeacherAddr1');
	let index = selectTeacherAddr1.options.selectedIndex;
	selectTeacherAddr1Value = selectTeacherAddr1.options[index].value; // 주소(시) 지정한 값 저장
	
	// ↓ 선택한 주소(시)로 주소(구) 조회하기
	let addr2List;
	await getAddressPart2ListByAddressPart1(selectTeacherAddr1Value)
	.then(result => {
		addr2List = result
	})
	.catch(error => {
		console.log(error)
	});
	
	if (addr2List != false) { // '시 선택' 를 선택한 것이 아니라면
		const selectTeacherAddr2 = document.querySelector('.selectTeacherAddr2');
		let selectTeacherAddr2add = `<option value="" selected>구 선택</option>`;
		for (let i of addr2List) {
			selectTeacherAddr2add += `
			<option value="${i}">${i}</option>
			`
		}
		selectTeacherAddr2.innerHTML = selectTeacherAddr2add;
	}
	
}

function getSelectTeacherAddr2Value() { // 주소(구) 값 저장 함수
	const selectTeacherAddr2 = document.querySelector('.selectTeacherAddr2');
	let index = selectTeacherAddr2.options.selectedIndex;
	selectTeacherAddr2Value = selectTeacherAddr2.options[index].value; // 주소(구) 지정한 값 저장	
}

async function getAddressPart2ListByAddressPart1(address_part1) { // 주소(시) 로 주소(구) 불러오기 함수
	const url = `/api/v1/lists/addresspart2/${address_part1}`;
	let responseData = false;
	
	await request(url)
	.then(result => { // 정상적으로 값 불러올시
		responseData = result.data; // result.data -> 주소(구)
	})
	.catch(error => { // '시 선택'을 다시 선택했을시
		const selectTeacherAddr2 = document.querySelector('.selectTeacherAddr2');
		let selectTeacherAddr2add = `<option value="" selected>구 선택</option>`;
		selectTeacherAddr2.innerHTML = selectTeacherAddr2add;
		selectTeacherAddr1Value = ""
		selectTeacherAddr2Value = ""
	})
	return responseData;
}

function getRadioGenderValue(event) { // 성별 라디오버튼 값 get 함수
	radioGenderValue = event.target.value;
}

function getSelectTeacherAgeValue() { // 나이 값 get 함수
	const selectTeacherAge = document.querySelector('.selectTeacherAge');
	let index = selectTeacherAge.options.selectedIndex;
	selectTeacherAgeValue = selectTeacherAge.options[index].value;
}

function getSelectPersonalityValue(event) { // 성격 체크시 배열에 저장 함수
	if(event.target.checked) { // 체크를 했다면
		if(selectTeacherPersonality.length < 2) { // 배열 내 갯수가 2개 이하라면
			selectTeacherPersonality.push(event.target.value)
		} else { // 5개 초과시
			alert("성격은 2개까지만 선택 가능합니다.")
			event.target.checked = false // 체크 해제
		}
	} else { // 체크를 해제했다면
		let index = selectTeacherPersonality.indexOf(event.target.value);
		if (index > -1) {
            selectTeacherPersonality.splice(index, 1);
		} // 배열에서 특정 항목 삭제 알고리즘
	}
}

function getSelectTeacherPriceValue() { // 최소 페이 값 get 함수
	const selectTeacherPrice = document.querySelector('.selectTeacherPrice');
	let index = selectTeacherPrice.options.selectedIndex;
	selectTeacherPriceValue = selectTeacherPrice.options[index].value;
}

function getSelectSubjectValue(event) { // 과목명 체크시 배열에 저장 함수
	if(event.target.checked) { // 체크를 했다면
		if(selectTeacherSubject.length < 5) { // 배열 내 갯수가 5개 이하라면
			selectTeacherSubject.push(event.target.value)
		} else { // 5개 초과시
			alert("과목은 5개까지만 선택 가능합니다.")
			event.target.checked = false // 체크 해제
		}
	} else { // 체크를 해제했다면
		let index = selectTeacherSubject.indexOf(event.target.value);
		if (index > -1) {
		  selectTeacherSubject.splice(index, 1);
		} // 배열에서 특정 항목 삭제 알고리즘
	}
}

function NoUniversity(event) { // 출신대학교 없음 체크박스
    const selectUniversity = document.querySelector('.selectUniversity');
    const selectUniversityStudentnum = document.querySelector('.selectUniversityStudentnum');
    const inputUniversityMajor = document.querySelector('.inputUniversityMajor');
    const selectUniversityIsgraduate = document.querySelector('.selectUniversityIsgraduate');
	if(event.target.checked) { // 체크를 했다면
        noUniversityFlag = true;
        selectUniversity.disabled = true;
        selectUniversityStudentnum.disabled = true;
        inputUniversityMajor.disabled = true;
        selectUniversityIsgraduate.disabled = true;
        selectUniversityValue = "" 
        selectUniversity.options.selectedIndex = 0;
        inputUniversityMajor.value = ""
        selectUniversityStudentnumValue = ""
        selectUniversityStudentnum.options.selectedIndex = 0;
        selectUniversityIsgraduateValue = ""
        selectUniversityIsgraduate.options.selectedIndex = 0;
	} else { // 체크를 해제했다면
        noUniversityFlag = false;
        selectUniversity.disabled = false;
        selectUniversityStudentnum.disabled = false;
        inputUniversityMajor.disabled = false;
        selectUniversityIsgraduate.disabled = false;
	}
}

function getSelectUniversityValue() { // 출신 대학교 값 get 함수
	const selectUniversity = document.querySelector('.selectUniversity');
	let index = selectUniversity.options.selectedIndex;
	selectUniversityValue = selectUniversity.options[index].value;
}

function getSelectUniversityStudentnumValue() { // 학번 값 get 함수
	const selectUniversityStudentnum = document.querySelector('.selectUniversityStudentnum');
	let index = selectUniversityStudentnum.options.selectedIndex;
	selectUniversityStudentnumValue = selectUniversityStudentnum.options[index].value;
}

function getSelectUniversityIsgraduateValue() { // 대학 졸업여부 값 get 함수
	const selectUniversityIsgraduate = document.querySelector('.selectUniversityIsgraduate');
	let index = selectUniversityIsgraduate.options.selectedIndex;
	selectUniversityIsgraduateValue = selectUniversityIsgraduate.options[index].value;
}