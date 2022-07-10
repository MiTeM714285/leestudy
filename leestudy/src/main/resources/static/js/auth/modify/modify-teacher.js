let phoneNumCheckFlag = false; // 전화번호 변경 완료에 대한
let passwordModifyingFlag = false; // 패스워드 변경을 원하는지에 대한
let phonenumModifyingFlag = false; // 전화번호 변경을 원하는지에 대한
let noUniversityFlag = false;
let sendSMSNum; // 인증번호 저장변수
let infoUserCommon; // 세션값 유저-공통값
let infoUserTeacher; // 세션값 유저-선생값

// 회원가입과는 다르게 라디오버튼, 체크박스, 콤보박스에 대한 value 변수 필요
let username // 1. 아이디 값
const inputOldPassword = document.querySelector('.inputOldPassword') // 2-1. 현재 비밀번호 입력란
const inputNewPassword = document.querySelector('.inputNewPassword') // 2-2. 새 비밀번호 입력란
const inputNewPasswordAgain = document.querySelector('.inputNewPasswordAgain') // 2-3. 새 비밀번호 재입력란
const inputNickname = document.querySelector('.inputNickname') // 3. 닉네임 입력란
const inputEmail = document.querySelector('.inputEmail') // 4. 이메일 입력란
const radioAvailableRemote = document.getElementsByName('radioAvailableRemote'); // 5-1. 과외원격여부 라디오버튼
let radioAvailableRemoteValue = "" // 5-2. 과외원격여부 라디오버튼 값
const radioAvailableDemonstration = document.getElementsByName('radioAvailableDemonstration'); //  6-2. 시범과외 가능여부 라디오버튼
let radioAvailableDemonstrationValue = "" // 6-2. 시범과외 가능여부 라디오버튼 값
const selectTeacherAddr1 = document.querySelector('.selectTeacherAddr1'); // 7-1. 주소(시) 콤보박스
let selectTeacherAddr1Value = "" // 7-2. 주소(시) 값
const selectTeacherAddr2 = document.querySelector('.selectTeacherAddr2'); // 7-3. 주소(구) 콤보박스
let selectTeacherAddr2Value = "" // 7-4. 주소(구) 값
const radioGender = document.getElementsByName('radioGender'); // 8-1. 성별 라디오버튼
let radioGenderValue = 0 // 8-2. 성별 라디오버튼 값
const selectTeacherAge = document.querySelector('.selectTeacherAge'); // 9-1. 나이 콤보박스
let selectTeacherAgeValue = ""; // 9-2. 나이 값
let selectTeacherPersonality = []; // 10-1. 선택한 성격 배열
let selectTeacherPersonalityValue = "" // 10-2. 선택한 성격 값
const checkboxPersonality = document.getElementsByName('checkboxPersonality'); // 10-3. 성격 체크박스
const selectTeacherPrice = document.querySelector('.selectTeacherPrice'); // 11-1. 최대 예산 콤보박스
let selectTeacherPriceValue = ""; // 11-2. 최소 페이 값
const inputDetailPrice = document.querySelector('.inputDetailPrice') // 12. 자세한 수업료 기준 입력란
let selectTeacherSubject = []; // 13-1. 선택한 과목 배열
let selectTeacherSubjectValue = "" // 13-2. 과목 값
const checkboxSubject = document.getElementsByName('checkboxSubject'); // 13-3. 과목 체크박스
const selectUniversity = document.querySelector('.selectUniversity'); // 14-1. 출신대학 콤보박스
let selectUniversityValue = "" // 14-2. 출신대학 값
const inputUniversityMajor = document.querySelector('.inputUniversityMajor') // 15. 대학전공 입력란
const selectUniversityStudentnum = document.querySelector('.selectUniversityStudentnum'); // 16-1. 대학학번 콤보박스
let selectUniversityStudentnumValue = "" // 16-2. 대학학번 값
const selectUniversityIsgraduate = document.querySelector('.selectUniversityIsgraduate'); // 17-1. 졸업여부 콤보박스
let selectUniversityIsgraduateValue = "" // 17-2. 졸업여부 값
const inputIntroduction = document.querySelector('.inputIntroduction') // 18. 한줄소개 입력란
const inputAvailableTime = document.querySelector('.inputAvailableTime') // 19. 과외 가능 요일/시간 입력란
const inputTeachingDetail = document.querySelector('.inputTeachingDetail') // 20. 과목별 수업내용 입력란
const inputTeachingStyle = document.querySelector('.inputTeachingStyle') // 21. 과외 스타일 입력란
const inputPhonenum1 = document.querySelector('.inputPhonenum1') // 22-1. 전화번호 입력란
const inputPhonenum2 = document.querySelector('.inputPhonenum2') // 22-2. 인증번호 입력란

const btnPhoneNumCheck1 = document.querySelector('.btnPhoneNumCheck1') // 인증번호 요청 버튼
const btnPhoneNumCheck2 = document.querySelector('.btnPhoneNumCheck2') // 인증완료 버튼
const btnTeacherModifyCancel = document.querySelector('.btnTeacherModifyCancel') // 수정취소 버튼
const btnTeacherModifyComplete = document.querySelector('.btnTeacherModifyComplete') // 수정완료 버튼
const btnTeacherDelete = document.querySelector('.btnTeacherDelete') // 회원탈퇴 버튼
const checkboxNoUniversity = document.querySelector('.checkboxNoUniversity') // 출신대학교 없음 체크박스
const fileInput = document.querySelector(".file-input"); // 이미지 파일인풋
const profileImgUrl = document.querySelector(".profile-img-url") // 이미지 변경기

loadUserCommon();
loadUserTeacher();

async function loadUserCommon() { // 세션으로부터 회원정보를 가져와서 각 기입란에 채우는 함수
	await getInfoUserCommon() // principal.js 의 함수, promise로 return된 값
		.then(result => {
			infoUserCommon = result.data.userCommon;

            username = infoUserCommon.username
            inputNickname.value = infoUserCommon.nickname
            radioGenderValue = infoUserCommon.gender // 해당 사항에 대한 for문 필요
            selectTeacherAgeValue = infoUserCommon.age // 해당 사항에 대한 for문 필요
            selectTeacherAddr1Value = infoUserCommon.address_part1 // 해당 사항에 대한 for문 필요
            selectTeacherAddr2Value = infoUserCommon.address_part2 // 해당 사항에 대한 for문 필요
            pictureValue = infoUserCommon.picture
            
            if(pictureValue != null && pictureValue != "") { // 이미지 등록을 했다면
				profileImgUrl.src = "/picture/custom/" + infoUserCommon.picture; // 프로필이미지 파일. /picture/custom/ 는 WebMvcConfig.java에서 설정
			} else { // 이미지 등록을 안했다면
				profileImgUrl.src = "/picture/profile.png"; // 기본이미지로
			}
		})
		.catch(error => {
			console.log(error)
		});

    for(let i of radioGender) { // 성별에 대한 정보 채우기
        if (i.value == radioGenderValue) {
            i.checked = true;
        }
    }
    for (let i=0; i<selectTeacherAge.options.length; i++){ // 나이에 대한 정보 채우기
        if(selectTeacherAge.options[i].value == selectTeacherAgeValue){
        selectTeacherAge.options[i].selected = true;
        }
    }
    for (let i=0; i<selectTeacherAddr1.options.length; i++){ // 주소(시)에 대한 정보 채우기
        if(selectTeacherAddr1.options[i].value == selectTeacherAddr1Value){
        selectTeacherAddr1.options[i].selected = true;
        }
    }
    await getSelectTeacherAddr1Value()
    for (let i=0; i<selectTeacherAddr2.options.length; i++){ // 주소(구)에 대한 정보 채우기
        if(selectTeacherAddr2.options[i].value == selectTeacherAddr2Value){
        selectTeacherAddr2.options[i].selected = true;
        }
    }
}

async function loadUserTeacher() { // 세션으로부터 회원정보를 가져와서 각 기입란에 채우는 함수
	await getInfoUserDetail() // principal.js 의 함수, promise로 return된 값
		.then(result => {
			infoUserTeacher = result.data;

            inputEmail.value = infoUserTeacher.teacher_email
            inputIntroduction.value = infoUserTeacher.teacher_introduction
            selectUniversityValue = infoUserTeacher.teacher_university // 해당 사항에 대한 for문 필요
            selectUniversityIsgraduateValue = infoUserTeacher.teacher_university_isgraduate // 해당 사항에 대한 for문 필요
            inputUniversityMajor.value = infoUserTeacher.teacher_university_major
            selectUniversityStudentnumValue = infoUserTeacher.teacher_university_studentnum // 해당 사항에 대한 for문 필요
            selectTeacherPriceValue = infoUserTeacher.teacher_price // 해당 사항에 대한 for문 필요
            inputDetailPrice.value = infoUserTeacher.teacher_detailprice
            selectTeacherPersonalityValue = infoUserTeacher.teacher_personality // 해당 사항에 대한 for문 필요
            radioAvailableRemoteValue = infoUserTeacher.teacher_available_remote // 해당 사항에 대한 for문 필요
            selectTeacherSubjectValue = infoUserTeacher.teacher_subject // 해당 사항에 대한 for문 필요
            inputAvailableTime.value = infoUserTeacher.teacher_available_time
            inputTeachingDetail.value = infoUserTeacher.teacher_teaching_detail
            inputTeachingStyle.value = infoUserTeacher.teacher_teaching_style
            radioAvailableDemonstrationValue = infoUserTeacher.teacher_available_demonstration // 해당 사항에 대한 for문 필요
		})
		.catch(error => {
			console.log(error)
		});
    
    if (selectUniversityValue == 0) { // 출신대학이 없다면 NoUniversity 체크박스 체크했을 때랑 똑같은 처리
        checkboxNoUniversity.checked = true;
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
    } else { // 출신대학이 있다면
        for (let i=0; i<selectUniversity.options.length; i++){ // 출신대학에 대한 정보 채우기
            if(selectUniversity.options[i].value == selectUniversityValue){
                selectUniversity.options[i].selected = true;
            }
        }
        for (let i=0; i<selectUniversityIsgraduate.options.length; i++){ // 졸업여부 대한 정보 채우기
            if(selectUniversityIsgraduate.options[i].value == selectUniversityIsgraduateValue){
                selectUniversityIsgraduate.options[i].selected = true;
            }
        }
        for (let i=0; i<selectUniversityStudentnum.options.length; i++){ // 학번에 대한 정보 채우기
            if(selectUniversityStudentnum.options[i].value == selectUniversityStudentnumValue){
                selectUniversityStudentnum.options[i].selected = true;
            }
        }
    }

    for (let i=0; i<selectTeacherPrice.options.length; i++){ // 최소 페이에 대한 정보 채우기
        if(selectTeacherPrice.options[i].value == selectTeacherPriceValue){
            selectTeacherPrice.options[i].selected = true;
        }
    }
    selectTeacherPersonality = selectTeacherPersonalityValue.split(",")
    for(let personalityName of selectTeacherPersonality) { // 성격 대한 정보 채우기
        for(let i of checkboxPersonality) {
            if (i.value == personalityName) {
                i.checked = true;
            }
        }
    }
    for(let i of radioAvailableRemote) { // 원격여부에 대한 정보 채우기
        if (i.value == radioAvailableRemoteValue) {
            i.checked = true;
        }
    }
    selectTeacherSubject = selectTeacherSubjectValue.split(",")
    for(let subjectName of selectTeacherSubject) { // 과목에 대한 정보 채우기
        for(let i of checkboxSubject) {
            if (i.value == subjectName) {
                i.checked = true;
            }
        }
    }
    for(let i of radioAvailableDemonstration) { // 시범과외 여부에 대한 정보 채우기
        if (i.value == radioAvailableDemonstrationValue) {
            i.checked = true;
        }
    }
}


btnTeacherModifyCancel.onclick = () => { // 수정취소 버튼 클릭시
    history.back();
}

btnTeacherModifyComplete.onclick = async () => { // 수정완료 버튼 클릭시
	let checker = [1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] // 입력사항 검사기. 모두 1이 되어야 가입 완료 가능

    let checkPasswordResult = false;
    if(passwordModifyingFlag == true) { // 패스워드를 바꾸려한다면
        await checkPassword(inputOldPassword.value) // 이전 비밀번호가 맞는지부터 체크
		.then(() => {
			checkPasswordResult = true;
		})
		.catch(error => {
			checkPasswordResult = false;
		});
    }

	if ((checkPasswordResult == true && inputOldPassword.value != inputNewPassword.value && inputNewPassword.value.length > 7 && inputNewPassword.value == inputNewPasswordAgain.value ) || passwordModifyingFlag == false) { checker[2] = 1 } else { checker[2] = 0 } // 2. 비밀번호 입력란
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
	if (phoneNumCheckFlag == true || phonenumModifyingFlag == false) { checker[22] = 1 } else { checker[22] = 0 } // 22. 전화번호 입력란
	
	const formModifyTeachers = document.querySelectorAll('.form-modify-teacher');
	let allIsOK = true;
	
	for (let i in checker) { // 입력사항들 검사
		if(checker[i]==0) {
			formModifyTeachers[i].style.borderColor = "red"; // 입력사항을 완료하지 못한 곳 빨간테두리
			allIsOK = false;
		} else {
			formModifyTeachers[i].style.borderColor = "lightgrey"; // 입력사항을 완료한 곳 회색테두리
		}
	}
	
	if (allIsOK == false) { // 빨간 테두리가 하나라도 있다면
		alert("입력사항이 모두 완료되지 않았습니다.\n입력사항중 테두리가 빨간색인것을 찾아 완료하세요.")
	} else {
		if(confirm("입력사항 대로 수정을 진행하시겠습니까?")) {
            selectTeacherPersonalityValue = arrayToString(selectTeacherPersonality);
			selectTeacherSubjectValue = arrayToString(selectTeacherSubject);
			await modifyUserCommon("USER_TEACHER");
		}
	}
}

btnTeacherDelete.onclick = async () => { // 탈퇴 버튼 클릭시
	if (confirm("정말로 탈퇴하시겠습니까?")) {
		deleteUserCommon();
	 }
}

async function checkPassword(password) { // 이전 비밀번호 체크함수
	const url = `/api/v1/account/checkpassword`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": username,
			"password": password
		})
	};
	const response = await fetch(url, option);
	if (response.ok) {
		return response.json(); // promise로 return
	} else {
		throw new Error("Failed to get Authentication." + response);
	}
}

btnPhoneNumCheck1.onclick = async () => { // 인증번호 요청 버튼 클릭시
	if (inputPhonenum1.value.length != 11) {
		alert('전화번호는 숫자 11자로 입력 바랍니다.')
	} else {
		if(confirm("이 전화번호가 확실합니까?\n'확인' 을 누른 뒤 바꾸려면 회원가입을 다시 진행해 주십시오.")) {
			inputPhonenum1.disabled = true;
			alert("인증번호가 발송되었습니다.\n인증번호 입력란에 입력후 인증완료 버튼을 눌러주세요.")
			inputPhonenum2.disabled = false; // 인증번호 입력란 잠금해제
			btnPhoneNumCheck2.disabled = false; // 인증완료 버튼 잠금해제
			sendSMSNum = await sendSMS(inputPhonenum1.value);
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

async function modifyUserCommon() { // 회원정보 수정하기 - 공통사항 DB업데이트 함수
	let url = `/api/v1/account/modify-common`;
	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": username,
			"password": inputNewPassword.value,
			"nickname": inputNickname.value,
			"gender": radioGenderValue,
            "age": selectTeacherAgeValue,
            "address_part1": selectTeacherAddr1Value,
            "address_part2": selectTeacherAddr2Value
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
            modifyUserTeacher(); // modifyUserTeacher DB업데이트 함수 실행
         })
		.catch(error => console.log(error));
}

async function modifyUserTeacher() { // 회원정보 수정하기 - 학생전용사항 DB업데이트 함수
    let url = `/api/v1/account/modify-teacher`;
	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": username,
			"teacher_email": inputEmail.value,
            "teacher_introduction": inputIntroduction.value,
            "teacher_university": selectUniversityValue,
            "teacher_university_isgraduate": selectUniversityIsgraduateValue,
            "teacher_university_major": inputUniversityMajor.value,
            "teacher_university_studentnum": selectUniversityStudentnumValue,
            "teacher_phonenum": inputPhonenum1.value,
            "teacher_price": selectTeacherPriceValue,
            "teacher_detailprice": inputDetailPrice.value,
            "teacher_personality": selectTeacherPersonalityValue,
            "teacher_available_remote": radioAvailableRemoteValue,
            "teacher_subject": selectTeacherSubjectValue,
            "teacher_available_time": inputAvailableTime.value,
            "teacher_teaching_detail": inputTeachingDetail.value,
            "teacher_teaching_style": inputTeachingStyle.value,
            "teacher_available_demonstration": radioAvailableDemonstrationValue,

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
            alert('회원정보 수정이 완료되었습니다.')
			window.location.replace("/auth/search");
         })
		.catch(error => console.log(error));
}

async function deleteUserCommon() { // 회원탈퇴 함수
	let url = `/api/v1/account/delete`;
	let option = {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": username,
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
			alert('탈퇴가 완료되었습니다')
			location.replace("/logout");
		})
		.catch(error => { console.log(error) });
}


function getRadioAvailableRemoteValue(event) { // 화상과외(온라인과외) 가능여부 값 get 함수
	radioAvailableRemoteValue = event.target.value;
}

function getRadioAvailableDemonstrationValue(event) { // 시범과외 가능여부 값 get 함수
	radioAvailableDemonstrationValue = event.target.value;
}

async function getSelectTeacherAddr1Value() { // 주소(시) 값 저장 함수 및 주소(구) 조회 함수
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
	let index = selectUniversity.options.selectedIndex;
	selectUniversityValue = selectUniversity.options[index].value;
}

function getSelectUniversityStudentnumValue() { // 학번 값 get 함수
	let index = selectUniversityStudentnum.options.selectedIndex;
	selectUniversityStudentnumValue = selectUniversityStudentnum.options[index].value;
}

function getSelectUniversityIsgraduateValue() { // 대학 졸업여부 값 get 함수
	let index = selectUniversityIsgraduate.options.selectedIndex;
	selectUniversityIsgraduateValue = selectUniversityIsgraduate.options[index].value;
}

function PasswordModifying(event) { // 비밀번호 변경하고자 할때의 체크박스
	if(event.target.checked) { // 체크를 했다면
        passwordModifyingFlag = true;
        inputOldPassword.disabled = false;
        inputNewPassword.disabled = false;
        inputNewPasswordAgain.disabled = false;
	} else { // 체크를 해제했다면
        passwordModifyingFlag = false;
        inputOldPassword.disabled = true;
        inputOldPassword.value=""
        inputNewPassword.disabled = true;
        inputNewPassword.value=""
        inputNewPasswordAgain.disabled = true;
        inputNewPasswordAgain.value=""
	}
}

function PhonenumModifying(event) { // 전화번호 변경하고자 할때의 체크박스
	if(event.target.checked) { // 체크를 했다면
        phonenumModifyingFlag = true;
        phoneNumCheckFlag = false
        inputPhonenum1.disabled = false;
        inputPhonenum1.value=""
        btnPhoneNumCheck1.disabled = false;
        inputPhonenum2.disabled = true;
        inputPhonenum2.value=""
        btnPhoneNumCheck2.disabled = true;
	} else { // 체크를 해제했다면
        phonenumModifyingFlag = false;
        inputPhonenum1.disabled = true;
        inputPhonenum1.value=""
        btnPhoneNumCheck1.disabled = true;
        inputPhonenum2.disabled = true;
        inputPhonenum2.value=""
        btnPhoneNumCheck2.disabled = true;
	}
}

async function imgSubmit(e) { // 이미지 제출 함수
	let formData = new FormData(document.querySelector("form")); // 함수가 호출되면 해당 form을 저장
	
	const url = `/api/v1/account/modify-common/picture`;
	const option = {
		method : "PUT",
		headers : {},
		body:formData
	};
	const response = await fetch(url, option);
	if(response.ok) {
		profileImgUrl.src = e.target.result // 매개변수로 받았던 새 이미지파일을 profileImgUrl의 src로 지정
		alert("프로필 이미지 변경이 되었습니다.")
		return response.json();
	} else {
		alert("이미지 용량이 너무 큽니다.\n1MB 이하 용량으로 선정해 주세요.")
		return response.json();
	}
}

fileInput.onchange = () => { // 이미지 파일을 새로 등록할시
	let reader = new FileReader();
	reader.onload = (e) => {
		if (confirm("이미지를 변경하시겠습니까?")) {
			const result = imgSubmit(e); // 막 넣은 이미지를 매개변수로 imgSubmit호출
			console.log(JSON.stringify(result));
		}
	}
	reader.readAsDataURL(fileInput.files[0]); // 업로드 중 맨 처음의 파일로 url을 지정
}
