const selectTeacherAddr1 = document.querySelector('.selectTeacherAddr1'); // 주소(시) 콤보박스
const selectTeacherAddr2 = document.querySelector('.selectTeacherAddr2'); // 주소(구) 콤보박스
let selectTeacherAddr1Value = "" // 주소(시) 값
let selectTeacherAddr2Value = "" // 주소(구) 값
const selectSubjectCategory = document.querySelector('.selectSubjectCategory'); // 과목카테고리 콤보박스
const selectSubjectName = document.querySelector('.selectSubjectName'); // 과목명 콤보박스
let selectSubjectCategoryValue = "" // 과목카테고리 값
let selectSubjectNameValue = "" // 과목명 값
const selectIsRemote = document.querySelector('.selectIsRemote'); // 원격여부 콤보박스
let selectIsRemoteValue = "" // 원격여부 값
const selectGender = document.querySelector('.selectGender'); // 성별 콤보박스
let selectGenderValue = "" // 성별 값
const selectPrice = document.querySelector('.selectPrice'); // 월수업료 콤보박스
let selectPriceValue = "" // 월수업료 값
const selectAge = document.querySelector('.selectAge'); // 나이 콤보박스
let selectAgeValue = "" // 나이 값

findTeacherInfoBySearch()

async function getSelectTeacherAddr1Value() { // 주소(시) 값 저장 함수 및 주소(구) 조회 함수
	let index = selectTeacherAddr1.options.selectedIndex;
	selectTeacherAddr1Value = selectTeacherAddr1.options[index].value; // 주소(시) 지정한 값 저장
	await findTeacherInfoBySearch()
	// ↓ 선택한 주소(시)로 주소(구) 조회하기
	let addr2List;
	await getAddressPart2ListByAddressPart1(selectTeacherAddr1Value)
	.then(result => {
		addr2List = result
	})
	.catch(error => {
		console.log(error)
	});
	
	if (addr2List != false) { // '시 전체' 를 선택한 것이 아니라면
		let selectTeacherAddr2add = `<option value="" selected>구 전체</option>`;
		for (let i of addr2List) {
			selectTeacherAddr2add += `
			<option value="${i}">${i}</option>
			`
		}
		selectTeacherAddr2.innerHTML = selectTeacherAddr2add;
	}
}

async function getSelectTeacherAddr2Value() { // 주소(구) 값 저장 함수
	let index = selectTeacherAddr2.options.selectedIndex;
	selectTeacherAddr2Value = selectTeacherAddr2.options[index].value; // 주소(구) 지정한 값 저장	
	await findTeacherInfoBySearch()
}

async function getAddressPart2ListByAddressPart1(address_part1) { // 주소(시) 로 주소(구) 불러오기 함수
	const url = `/api/v1/lists/addresspart2/${address_part1}`;
	let responseData = false;
	
	await request(url)
	.then(result => { // 정상적으로 값 불러올시
		responseData = result.data; // result.data -> 주소(구)
	})
	.catch(error => { // '시 선택'을 다시 선택했을시
		let selectTeacherAddr2add = `<option value="" selected>구 전체</option>`;
		selectTeacherAddr2.innerHTML = selectTeacherAddr2add;
		selectTeacherAddr1Value = ""
		selectTeacherAddr2Value = ""
		findTeacherInfoBySearch()
	})
	return responseData;
}

async function getSelectSubjectCategoryValue() { // 과목카테고리 값 저장 함수 및 과목명 조회 함수
	let index = selectSubjectCategory.options.selectedIndex;
	selectSubjectCategoryValue = selectSubjectCategory.options[index].value; // 과목카테고리 지정한 값 저장
	if (selectSubjectCategoryValue == "") {
		await findTeacherInfoBySearch()
	}
	// ↓ 선택한 과목카테고리로 과목명 조회하기
	let subjectNameList;
	await getSubjectNameListBySubjectCategory(selectSubjectCategoryValue)
	.then(result => {
		subjectNameList = result;
	})
	.catch(error => {
		console.log(error)
	});
	
	if (subjectNameList != false) { // '선택하세요' 를 선택한 것이 아니라면
		let selectSubjectNameadd = `<option value="" selected>선택하세요</option>`;
		for (let i of subjectNameList) {
			selectSubjectNameadd += `
			<option value="${i}">${i}</option>
			`
		}
		selectSubjectName.innerHTML = selectSubjectNameadd;
	}
}

async function getSelectSubjectNameValue() { // 과목명 값 저장 함수
	let index = selectSubjectName.options.selectedIndex;
	selectSubjectNameValue = selectSubjectName.options[index].value; // 과목명 지정한 값 저장
	if (selectSubjectNameValue != "") {
		await findTeacherInfoBySearch()
	}
}

async function getSubjectNameListBySubjectCategory(subject_category) { // 과목카테고리 로 과목명 불러오기 함수
	const url = `/api/v1/lists/subjectname/${subject_category}`;
	let responseData = false;
	await request(url)
	.then(result => { // 정상적으로 값 불러올시
		responseData = result.data; // result.data -> 과목명
	})
	.catch(error => { // '선택하세요'을 다시 선택했을시
		let selectSubjectNameadd = `<option value="" selected>선택하세요</option>`;
		selectSubjectName.innerHTML = selectSubjectNameadd;
		selectSubjectCategoryValue = ""
		selectSubjectNameValue = ""
		findTeacherInfoBySearch()
	})
	return responseData;
}

async function getSelectIsRemoteValue() { // 원격여부 값 저장 함수
	let index = selectIsRemote.options.selectedIndex;
	selectIsRemoteValue = selectIsRemote.options[index].value; // 원격여부 지정한 값 저장
	await findTeacherInfoBySearch()
}

async function getSelectGenderValue() { // 성별 값 저장 함수
	let index = selectGender.options.selectedIndex;
	selectGenderValue = selectGender.options[index].value; // 성별 지정한 값 저장
	await findTeacherInfoBySearch()
}

async function getSelectPriceValue() { // 월수업료 값 저장 함수
	let index = selectPrice.options.selectedIndex;
	selectPriceValue = selectPrice.options[index].value; // 월수업료 지정한 값 저장
	await findTeacherInfoBySearch()
}

async function getSelectAgeValue() { // 나이 값 저장 함수
	let index = selectAge.options.selectedIndex;
	selectAgeValue = selectAge.options[index].value; // 나이 지정한 값 저장
	await findTeacherInfoBySearch()
}


async function findTeacherInfoBySearch() { // search 함수
	let url = `/api/v1/auth/search-teacher/`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			address_part1: selectTeacherAddr1Value,
			address_part2: selectTeacherAddr2Value,
			teacher_subject: selectSubjectNameValue,
			teacher_available_remote: selectIsRemoteValue,
			gender: selectGenderValue,
			teacher_price: selectPriceValue,
			age: selectAgeValue
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
		.then(result => { 
			load(result.data)
		})
		.catch(error => console.log(error));
}

function load(data) {
	const teacherCardlistInner = document.querySelector('.teacher-cardlist-inner')
	let teacherCard = ``
	
	for (let i = 0; i < data.length; i++) { // 실질적 글리스트 출력
		teacherCard += `
			<div class="teacher-card" style="padding: 0;" onclick="goToTeacherDetailPage('${data[i].username}')" >
			`
			if (data[i].picture != null && data[i].picture != "") { // 사진 표현의 유무
				teacherCard += `<img src="/picture/custom/${data[i].picture}" class="teacher-picture" alt="teacher_picture">`
			} else{
				teacherCard += `<img src="/picture/profile.png" class="teacher-picture" alt="teacher_picture">`
			}
			teacherCard += `<div class="teacher-card-existreview">`
		if (data[i].review_count != 0) { // 해당 선생의 후기 존재할시 '후기있음' 표기
			teacherCard += `<p>후기있음</p>`
		} else {
			teacherCard += `<p></p>`
		}
		teacherCard += `
	            </div>
	            <div class="teacher-card-info">
	              <p>${data[i].nickname}</p>
	              <div class="teacher-card-university">
	                <img src="../../static/images/ic_sm_study.png" alt="">
	                <div class="university-text">${data[i].university_name}</div>
	              </div>
	              <div class="teacher-card-subject">
	                <img src="../../static/images/ic_sm_class.png" alt="">
	                <div class="subject-text">${data[i].teacher_subject}</div>
	              </div>
	              <div class="teacher-card-location">
	                <img src="../../static/images/ic_sm_location.png" alt="">
	                <div class="location-text">${data[i].address_part1}, ${data[i].address_part2}</div>
	              </div>
	            </div>
          </div>
		`
	}
	teacherCardlistInner.innerHTML = teacherCard;
}

function goToTeacherDetailPage(username) {
	location.href = `/auth/detail/teacher?username=${username}`
}
