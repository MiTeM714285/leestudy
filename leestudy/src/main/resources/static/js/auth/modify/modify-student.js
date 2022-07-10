let phoneNumCheckFlag = false; // 전화번호 변경 완료에 대한
let passwordModifyingFlag = false; // 패스워드 변경을 원하는지에 대한
let phonenumModifyingFlag = false; // 전화번호 변경을 원하는지에 대한
let sendSMSNum; // 인증번호 저장변수
let infoUserCommon; // 세션값 유저-공통값
let infoUserStudent; // 세션값 유저-학생값

// 회원가입과는 다르게 라디오버튼, 체크박스, 콤보박스에 대한 객체 저장 변수 필요, 입력란은 value 변수 필요없음 
let username // 1. 아이디 값
const inputOldPassword = document.querySelector('.inputOldPassword') // 2-1. 현재 비밀번호 입력란
const inputNewPassword = document.querySelector('.inputNewPassword') // 2-2. 새 비밀번호 입력란
const inputNewPasswordAgain = document.querySelector('.inputNewPasswordAgain') // 2-3. 새 비밀번호 재입력란
const inputNickname = document.querySelector('.inputNickname') // 3. 닉네임 입력란
const inputEmail = document.querySelector('.inputEmail') // 4. 이메일 입력란
const radioIsurgent = document.getElementsByName('radioIsurgent'); // 5-1. 과외급구여부 라디오버튼
let radioIsurgentValue = 0 // 5-2. 과외급구여부 라디오버튼 값
const selectStudentGrade = document.querySelector('.selectStudentGrade'); // 6-1. 학년 콤보박스
let selectStudentGradeValue = "" // 6-2. 학년 콤보박스 값
const selectStudentAddr1 = document.querySelector('.selectStudentAddr1'); // 7-1. 주소(시) 콤보박스
let selectStudentAddr1Value = "" // 7-2. 주소(시) 값
const selectStudentAddr2 = document.querySelector('.selectStudentAddr2'); // 7-3. 주소(구) 콤보박스
let selectStudentAddr2Value = "" // 7-4. 주소(구) 값
const radioGender = document.getElementsByName('radioGender'); // 8-1. 성별 라디오버튼
let radioGenderValue = 0 // 8-2. 성별 라디오버튼 값
const selectStudentAge = document.querySelector('.selectStudentAge'); // 9-1. 나이 콤보박스
let selectStudentAgeValue = ""; // 9-2. 나이 값
let selectStudentSubject = []; // 10-1. 선택한 과목 배열
let selectStudentSubjectValue = "" // 10-2. 과목 값
const checkboxSubject = document.getElementsByName('checkboxSubject'); // 10-3. 과목 체크박스
const selectStudentPrice = document.querySelector('.selectStudentPrice'); // 11-1. 최대 예산 콤보박스
let selectStudentPriceValue = ""; // 11-2. 최대 예산 값
const inputAvailableTime = document.querySelector('.inputAvailableTime') // 12-1. 과외가능 요일/시간 입력란
let inputAvailableTimeValue = ""; // 12-2. 과외가능 요일/시간 값
const radioAvailableRemote = document.getElementsByName('radioAvailableRemote'); // 13-1. 과외원격여부 라디오버튼
let radioAvailableRemoteValue = "" // 13-2. 과외원격여부 라디오버튼 값
const inputRequest = document.querySelector('.inputRequest') // 14. 요청사항 입력란
const inputPhonenum1 = document.querySelector('.inputPhonenum1') // 15. 전화번호 입력란
const inputPhonenum2 = document.querySelector('.inputPhonenum2') // 인증번호 입력란

const btnPhoneNumCheck1 = document.querySelector('.btnPhoneNumCheck1') // 인증번호 요청 버튼
const btnPhoneNumCheck2 = document.querySelector('.btnPhoneNumCheck2') // 인증완료 버튼
const btnStudentModifyCancel = document.querySelector('.btnStudentModifyCancel') // 수정취소 버튼
const btnStudentModifyComplete = document.querySelector('.btnStudentModifyComplete') // 수정완료 버튼
const btnStudentDelete = document.querySelector('.btnStudentDelete') // 회원탈퇴 버튼
const fileInput = document.querySelector(".file-input"); // 이미지 파일인풋
const profileImgUrl = document.querySelector(".profile-img-url") // 이미지 변경기

loadUserCommon();
loadUserStudent();

async function loadUserCommon() { // 세션으로부터 회원정보를 가져와서 각 기입란에 채우는 함수
	await getInfoUserCommon() // principal.js 의 함수, promise로 return된 값
		.then(result => {
			infoUserCommon = result.data.userCommon;

            username = infoUserCommon.username
            inputNickname.value = infoUserCommon.nickname
            radioGenderValue = infoUserCommon.gender
            selectStudentAgeValue = infoUserCommon.age
            selectStudentAddr1Value = infoUserCommon.address_part1
            selectStudentAddr2Value = infoUserCommon.address_part2
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

    for (let i=0; i<selectStudentAge.options.length; i++){ // 나이에 대한 정보 채우기
        if(selectStudentAge.options[i].value == selectStudentAgeValue){
        selectStudentAge.options[i].selected = true;
        }
    }
    for (let i=0; i<selectStudentAddr1.options.length; i++){ // 주소(시)에 대한 정보 채우기
        if(selectStudentAddr1.options[i].value == selectStudentAddr1Value){
        selectStudentAddr1.options[i].selected = true;
        }
    }
    await getSelectStudentAddr1Value()
    for (let i=0; i<selectStudentAddr2.options.length; i++){ // 주소(구)에 대한 정보 채우기
        if(selectStudentAddr2.options[i].value == selectStudentAddr2Value){
        selectStudentAddr2.options[i].selected = true;
        }
    }
    for(let i of radioGender) { // 성별에 대한 정보 채우기
        if (i.value == radioGenderValue) {
            i.checked = true;
        }
    }
}

async function loadUserStudent() { // 세션으로부터 회원정보를 가져와서 각 기입란에 채우는 함수
	await getInfoUserDetail() // principal.js 의 함수, promise로 return된 값
		.then(result => {
			infoUserStudent = result.data;

            inputEmail.value = infoUserStudent.student_email
            radioIsurgentValue = infoUserStudent.student_isurgent
            selectStudentGradeValue = infoUserStudent.student_student_grade
            selectStudentSubjectValue = infoUserStudent.student_subject
            selectStudentPriceValue = infoUserStudent.student_price
            inputAvailableTime.value = infoUserStudent.student_available_time
            radioAvailableRemoteValue = infoUserStudent.student_available_remote
            inputRequest.value = infoUserStudent.student_request
		})
		.catch(error => {
			console.log(error)
		});
    for(let i of radioIsurgent) { // 과외급구여부에 대한 정보 채우기
        if (i.value == radioIsurgentValue) {
            i.checked = true;
        }
    }
    for (let i=0; i<selectStudentGrade.options.length; i++){ // 학년에 대한 정보 채우기
        if(selectStudentGrade.options[i].value == selectStudentGradeValue){
        selectStudentGrade.options[i].selected = true;
        }
    }
    for (let i=0; i<selectStudentPrice.options.length; i++){ // 최대 예산에 대한 정보 채우기
        if(selectStudentPrice.options[i].value == selectStudentPriceValue){
        selectStudentPrice.options[i].selected = true;
        }
    }
    for(let i of radioAvailableRemote) { // 원격여부에 대한 정보 채우기
        if (i.value == radioAvailableRemoteValue) {
            i.checked = true;
        }
    }
    selectStudentSubject = selectStudentSubjectValue.split(",")
    for(let subjectName of selectStudentSubject) { // 과목에 대한 정보 채우기
        for(let i of checkboxSubject) {
            if (i.value == subjectName) {
                i.checked = true;
            }
        }
    }
}

btnStudentModifyCancel.onclick = () => { // 수정취소 버튼 클릭시
    history.back();
}

btnStudentModifyComplete.onclick = async () => { // 수정완료 버튼 클릭시
	let checker = [1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0] // 입력사항 검사기. 모두 1이 되어야 가입 완료 가능
	
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
	if (radioIsurgentValue != 0) { checker[5] = 1 } else { checker[5] = 0 } // 5. 과외급구여부 라디오버튼 값
	if (selectStudentGradeValue != "") { checker[6] = 1 } else { checker[6] = 0 } // 6. 학년 콤보박스 값
	if (selectStudentAddr1Value != "" && selectStudentAddr2Value != "") { checker[7] = 1 } else { checker[7] = 0 } // 7. 주소 값
	if (radioGenderValue != 0) { checker[8] = 1 } else { checker[8] = 0 } // 8. 성별 값
	if (selectStudentAgeValue != "") { checker[9] = 1 } else { checker[9] = 0 } // 9. 나이 값
	if (selectStudentSubject.length != 0) { checker[10] = 1 } else { checker[10] = 0 } // 10. 과목 값
	if (selectStudentPriceValue != "") { checker[11] = 1 } else { checker[11] = 0 } // 11. 최대 예산 값
	if (inputAvailableTime.value.length > 9 && inputAvailableTime.value.length < 101)  { checker[12] = 1 } else { checker[12] = 0 } // 12. 과외가능 요일/시간 입력란
	if (radioAvailableRemoteValue != "") { checker[13] = 1 } else { checker[13] = 0 } // 5. 과외원격여부 라디오버튼 값
	if (inputRequest.value.length < 301)  { checker[14] = 1 } else { checker[14] = 0 } // 14. 요청사항 입력란
	if (phoneNumCheckFlag == true || phonenumModifyingFlag == false) { checker[15] = 1 } else { checker[15] = 0 } // 15. 전화번호 입력란
	
	const formModifyStudents = document.querySelectorAll('.form-modify-student');
	let allIsOK = true;
	
	for (let i in checker) { // 입력사항들 검사
		if(checker[i]==0) {
			formModifyStudents[i].style.borderColor = "red"; // 입력사항을 완료하지 못한 곳 빨간테두리
			allIsOK = false;
		} else {
			formModifyStudents[i].style.borderColor = "lightgrey"; // 입력사항을 완료한 곳 회색테두리
		}
	}
	
	if (allIsOK == false) { // 빨간 테두리가 하나라도 있다면
		alert("입력사항이 모두 완료되지 않았습니다.\n입력사항중 테두리가 빨간색인것을 찾아 완료하세요.")
	} else {
		if(confirm("입력사항 대로 수정을 진행하시겠습니까?")) {
			selectStudentSubjectValue = arrayToString(selectStudentSubject);
			await modifyUserCommon();
		}
	}
}

btnStudentDelete.onclick = async () => { // 탈퇴 버튼 클릭시
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
            "age": selectStudentAgeValue,
            "address_part1": selectStudentAddr1Value,
            "address_part2": selectStudentAddr2Value
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
            modifyUserStudent(); // modifyUserStudent DB업데이트 함수 실행
         })
		.catch(error => console.log(error));
}

async function modifyUserStudent() { // 회원정보 수정하기 - 학생전용사항 DB업데이트 함수
    let url = `/api/v1/account/modify-student`;
	let option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"username": username,
			"student_email": inputEmail.value,
			"student_phonenum": inputPhonenum1.value,
			"student_isurgent": radioIsurgentValue,
			"student_student_grade": selectStudentGradeValue,
			"student_subject": selectStudentSubjectValue,
			"student_price": selectStudentPriceValue,
			"student_available_time": inputAvailableTime.value,
			"student_available_remote": radioAvailableRemoteValue,
			"student_request": inputRequest.value
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

function getRadioIsurgentValue(event) { // 과외급구여부 라디오버튼 값 get 함수
	radioIsurgentValue = event.target.value;
}

function getRadioAvailableRemoteValue(event) { // 과외원격여부 라디오버튼 값 get 함수
	radioAvailableRemoteValue = event.target.value;
}

function getSelectStudentGradeValue() { // 학년 값 지정시 저장 함수
	let index = selectStudentGrade.options.selectedIndex;
	selectStudentGradeValue = selectStudentGrade.options[index].value;
}

async function getSelectStudentAddr1Value() { // 주소(시) 값 저장 함수 및 주소(구) 조회 함수
	let index = selectStudentAddr1.options.selectedIndex;
	selectStudentAddr1Value = selectStudentAddr1.options[index].value; // 주소(시) 지정한 값 저장
	
	// ↓ 선택한 주소(시)로 주소(구) 조회하기
	let addr2List;
	await getAddressPart2ListByAddressPart1(selectStudentAddr1Value)
	.then(result => {
		addr2List = result
	})
	.catch(error => {
		console.log(error)
	});
	if (addr2List != false) { // '시 선택' 를 선택한 것이 아니라면
		let selectStudentAddr2add = `<option value="" selected>구 선택</option>`;
		for (let i of addr2List) {
			selectStudentAddr2add += `
			<option value="${i}">${i}</option>
			`
		}
		selectStudentAddr2.innerHTML = selectStudentAddr2add;
	}
}

function getSelectStudentAddr2Value() { // 주소(구) 값 저장 함수
	let index = selectStudentAddr2.options.selectedIndex;
	selectStudentAddr2Value = selectStudentAddr2.options[index].value; // 주소(구) 지정한 값 저장	
}

async function getAddressPart2ListByAddressPart1(address_part1) { // 주소(시) 로 주소(구) 불러오기 함수
	const url = `/api/v1/lists/addresspart2/${address_part1}`;
	let responseData = false;
	
	await request(url)
	.then(result => { // 정상적으로 값 불러올시
		responseData = result.data; // result.data -> 주소(구)
	})
	.catch(error => { // '시 선택'을 다시 선택했을시
		let selectStudentAddr2add = `<option value="" selected>구 선택</option>`;
		selectStudentAddr2.innerHTML = selectStudentAddr2add;
		selectStudentAddr1Value = ""
		selectStudentAddr2Value = ""
	})
	return responseData;
}

function getRadioGenderValue(event) { // 과외급구여부 라디오버튼 값 get 함수
	radioGenderValue = event.target.value;
}

function getSelectStudentAgeValue() { // 나이 값 지정시 저장 함수
	let index = selectStudentAge.options.selectedIndex;
	selectStudentAgeValue = selectStudentAge.options[index].value;
}

function getSelectStudentPriceValue() { // 최대 예산 값 지정시 저장 함수
	let index = selectStudentPrice.options.selectedIndex;
	selectStudentPriceValue = selectStudentPrice.options[index].value;
}

function getSelectSubjectValue(event) { // 과목명 체크시 배열에 저장 함수
	if(event.target.checked) { // 체크를 했다면
		if(selectStudentSubject.length < 5) { // 배열 내 갯수가 5개 이하라면
			selectStudentSubject.push(event.target.value)
		} else { // 5개 초과시
			alert("과목은 5개까지만 선택 가능합니다.")
			event.target.checked = false // 체크 해제
		}
	} else { // 체크를 해제했다면
		let index = selectStudentSubject.indexOf(event.target.value);
		if (index > -1) {
		  selectStudentSubject.splice(index, 1);
		} // 배열에서 특정 항목 삭제 알고리즘
	}
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

async function imgSubmit(image) { // 이미지 제출 함수
	let formData = new FormData(document.querySelector("form")); // 함수가 호출되면 해당 form을 저장
	
	const url = `/api/v1/account/modify-common/picture`;
	const option = {
		method : "PUT",
		headers : {},
		body:formData
	};
	const response = await fetch(url, option);
	if(response.ok) {
		profileImgUrl.src = image.target.result // 매개변수로 받았던 새 이미지파일을 profileImgUrl의 src로 지정
		alert("프로필 이미지 변경이 되었습니다.")
		return response.json();
	} else {
		alert("이미지 용량이 너무 큽니다.\n1MB 이하 용량으로 선정해 주세요.")
		return response.json();
	}
}

fileInput.onchange = () => { // 이미지 파일을 새로 등록할시
	let reader = new FileReader();
	reader.onload = (image) => {
		if (confirm("이미지를 변경하시겠습니까?")) {
			const result = imgSubmit(image); // 막 넣은 이미지를 매개변수로 imgSubmit호출
			console.log(JSON.stringify(result));
		}
	}
	reader.readAsDataURL(fileInput.files[0]); // 업로드 중 맨 처음의 파일로 url을 지정
}
