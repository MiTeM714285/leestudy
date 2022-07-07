const selectAddr1 = document.querySelector('.selectAddr1'); // 주소(시) 콤보박스
const selectAddr2 = document.querySelector('.selectAddr2'); // 주소(구) 콤보박스
let selectAddr1Value = "" // 주소(시) 값
let selectAddr2Value = "" // 주소(구) 값
const btnReviewListAll = document.querySelector('.btnReviewListAll'); // 전체 리뷰 보기 버튼


findReviewAll("","","","")

if(role == 'USER_STUDENT') { // 학생이라면
	const btnReviewListByStudentName = document.querySelector('.btnReviewListByStudentName'); // 내가 쓴 리뷰 보기 버튼
	btnReviewListByStudentName.onclick = () => {
		findReviewAll("","","",username) // 해당 학생이 쓴 리뷰 위주로 출력
		initializeAddressSelect() // 주소 콤보박스 2개 초기화
		selectAddr1.disabled = true; // 내가 쓴 리뷰 보기를 하면 주소는 조작불가
		selectAddr2.disabled = true; // 내가 쓴 리뷰 보기를 하면 주소는 조작불가
	}
} else { // 선생이라면
	const btnReviewListByTeacherName = document.querySelector('.btnReviewListByTeacherName'); // 나에게 쓰여진 리뷰 보기 버튼
	btnReviewListByTeacherName.onclick = () => {
		findReviewAll("","",username,"") // 자기에게 쓰여진 리뷰 위주로 출력
		initializeAddressSelect() // 주소 콤보박스 2개 초기화
		selectAddr1.disabled = true; // 나에게 쓰여진 리뷰 보기를 하면 주소는 조작불가
		selectAddr2.disabled = true; // 나에게 쓰여진 리뷰 보기를 하면 주소는 조작불가
	}
}

btnReviewListAll.onclick = () => { // 전체 리뷰 보기 클릭시
	findReviewAll("","","","")
	initializeAddressSelect() // 주소 콤보박스 2개 초기화
	selectAddr1.disabled = false; // 주소 조작 활성화
	selectAddr2.disabled = false; // 주소 조작 활성화
}

function initializeAddressSelect() { // 주소 콤보박스를 모두 '시 전체', '구 전체' 로 초기화하는 함수
 	let selectAddr2add = `<option value="" selected>구 전체</option>`;
	selectAddr2.innerHTML = selectAddr2add; // 주소(구) 콤보박스 초기화
	selectAddr1.options[0].selected = true; // 주소(시) 커서는 '시 전체'로
	selectAddr2.options[0].selected = true; // 주소(구) 커서는 '구 전체'로
}


async function getSelectAddr1Value() { // 주소(시) 값 저장 함수 및 주소(구) 조회 함수
	let index = selectAddr1.options.selectedIndex;
	selectAddr1Value = selectAddr1.options[index].value; // 주소(시) 지정한 값 저장
	findReviewAll(selectAddr1Value,"","","") // 해당 조건으로 리뷰리스트 출력
	
	// ↓ 선택한 주소(시)로 주소(구) 조회하기
	let addr2List;
	await getAddressPart2ListByAddressPart1(selectAddr1Value)
	.then(result => {
		addr2List = result
	})
	.catch(error => {
		console.log(error)
	});
	
	if (addr2List != false) { // '시 전체' 를 선택한 것이 아니라면
		let selectAddr2add = `<option value="" selected>구 전체</option>`;
		for (let i of addr2List) {
			selectAddr2add += `
			<option value="${i}">${i}</option>
			`
		}
		selectAddr2.innerHTML = selectAddr2add;
	}
}

async function getSelectAddr2Value() { // 주소(구) 값 저장 함수
	let index = selectAddr2.options.selectedIndex;
	selectAddr2Value = selectAddr2.options[index].value; // 주소(구) 지정한 값 저장	
	findReviewAll(selectAddr1Value,selectAddr2Value,"","") // 해당 조건으로 리뷰리스트 출력
}

async function getAddressPart2ListByAddressPart1(address_part1) { // 주소(시) 로 주소(구) 불러오기 함수
	const url = `/api/v1/lists/addresspart2/${address_part1}`;
	let responseData = false;
	
	await request(url)
	.then(result => { // 정상적으로 값 불러올시
		responseData = result.data; // result.data -> 주소(구)
	})
	.catch(error => { // '시 선택'을 다시 선택했을시
		let selectAddr2add = `<option value="" selected>구 전체</option>`;
		selectAddr2.innerHTML = selectAddr2add;
		selectAddr1Value = ""
		selectAddr2Value = ""
		
	})
	return responseData;
}

async function findReviewAll(student_address_part1, student_address_part2, teacher_name, student_name) { // search 함수
	let url = `/api/v1/review/reviewlist/`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			"student_address_part1": student_address_part1,
			"student_address_part2": student_address_part2,
			"teacher_name": teacher_name,
			"student_name": student_name
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

function datetimeToDate(datetime) { // 날짜 형태에서 시,분,초를 자르는 함수
	date = datetime.substr(0,10)
	return date
}

async function load(data) {
	const formReview = document.querySelector('.form-review')
	let formReviewElement = ``
	
	for (let i = 0; i < data.length; i++) { // 실질적 글리스트 출력
	
	let teacherIsExist = await countUserCommonByUsername(data[i].teacher_name)
	
		formReviewElement += `
			<div class="form-review-element">
		        <div class="form-review-part1">`
		          
		if (data[i].teacher_picture != null && data[i].teacher_picture != "") { // 선생 사진 표현의 유무
			formReviewElement += `<img src="/picture/custom/${data[i].teacher_picture}" class="teacher-picture" alt="teacher_picture">`
		} else{
			formReviewElement += `<img src="/picture/profile.png" class="teacher-picture" alt="teacher_picture">`
		}
		
		formReviewElement += ` 
		          <div class="form-review-part1-info">
		            <div class="form-review-part1-name">
		              ${data[i].teacher_nickname}`
		if (teacherIsExist == 0) { // 선생 탈퇴의 유무 - 탈퇴시(결과0) '탈퇴함' 글귀 출력
			formReviewElement += `
					  <div class="form-review-part1-isresign">
		                (탈퇴함)
		              </div>`
		}

		              
		formReviewElement += `
				 </div>
		            <div class="form-review-part1-teacherproperties">
		              <div class="form-review-part1-teacherproperties-element">
		                <img src="../../static/images/ic_sm_study.png" alt="ic_sm_study">` 
		if (data[i].university_name != null && data[i].university_name != "") { // 출신대학의 유무
			formReviewElement += `<p>${data[i].university_name} ${data[i].teacher_university_major}</p>`
		} else{
			formReviewElement += `<p>출신대학교 없음</p>`
		}
		formReviewElement += ` 
		              </div>
		              <div class="form-review-part1-teacherproperties-element">
		                <img src="../../static/images/ic_sm_class_detail.png" alt="ic_sm_class_detail">
		                <p>${data[i].teacher_university_studentnum}</p>
		              </div>
		              <div class="form-review-part1-teacherproperties-element">
		                <img src="../../static/images/ic_sm_class.png" alt="ic_sm_class">
		                <p>${data[i].teacher_gender} 선생님</p>
		              </div>
		            </div>
		          </div>
		        </div>
		        <div class="form-review-part2">
		          <div class="form-review-part2-1">
		            <div class="form-review-part2-1-top">`
		            
		if (data[i].student_picture != null && data[i].student_picture != "") { // 학생 사진 표현의 유무
			formReviewElement += `<img src="/picture/custom/${data[i].student_picture}" class="student-picture" alt="student_picture">`
		} else{
			formReviewElement += `<img src="/picture/profile.png" class="student-picture" alt="student_picture">`
		}
		formReviewElement += `    
		              <div class="form-review-part2-1-studentinfo">
		                <p>${data[i].student_nickname}</p>
		                <p>${data[i].student_address_part1} ${data[i].student_address_part2} ${data[i].student_student_grade}</p>
		              </div>
		            </div>
		            <div class="form-review-part2-1-bottom">
		              <div class="form-review-part2-1-left">
		                <p>수업시작</p>
		                <p>월 수업료</p>
		              </div>
		              <div class="form-review-part2-1-right">
		                <p>${datetimeToDate(data[i].matching_startdate)}</p>
		                <p>${data[i].teacher_price}</p>
		              </div>
		            </div>
		          </div>
		
		
		          <div class="form-review-part2-2">
		            <div class="form-review-part2-2-half">
		              <div class="form-review-part2-2-element">
		                <p>전문성</p>
		                <div class="form-review-part2-2-rate">
		                  <div class="form-review-part2-2-stars">`
		                  
		for(let j=0; j<5; j++) { // 별점 색칠하기 : 전문성
			if (j < data[i].review_score_professionality) {
				formReviewElement += `<img src="/static/images/review/ic_star_full.png" alt="star_f">`
			} else {
				formReviewElement += `<img src="/static/images/review/ic_star_empty.png" alt="star_f">`
			}
		}
		formReviewElement += `
		                  </div>
		                  <div class="form-review-part2-2-number">
		                    <p>${data[i].review_score_professionality}</p>
		                  </div>
		                </div>
		              </div>
		              <div class="form-review-part2-2-element">
		                <p>강의력</p>
		                <div class="form-review-part2-2-rate">
		                  <div class="form-review-part2-2-stars">`
		for(let j=0; j<5; j++) { // 별점 색칠하기 : 강의력
			if (j < data[i].review_score_teaching) {
				formReviewElement += `<img src="/static/images/review/ic_star_full.png" alt="star_f">`
			} else {
				formReviewElement += `<img src="/static/images/review/ic_star_empty.png" alt="star_f">`
			}
		}    
		formReviewElement += `
		                  </div>
		                  <div class="form-review-part2-2-number">
		                    <p>${data[i].review_score_teaching}</p>
		                  </div>
		                </div>
		              </div>
		            </div>
		            <div class="form-review-part2-2-half">
		              <div class="form-review-part2-2-element">
		                <p>준비도</p>
		                <div class="form-review-part2-2-rate">
		                  <div class="form-review-part2-2-stars">`
		for(let j=0; j<5; j++) { // 별점 색칠하기 : 준비도
			if (j < data[i].review_score_readyness) {
				formReviewElement += `<img src="/static/images/review/ic_star_full.png" alt="star_f">`
			} else {
				formReviewElement += `<img src="/static/images/review/ic_star_empty.png" alt="star_f">`
			}
		}    
		formReviewElement += `
		                  </div>
		                  <div class="form-review-part2-2-number">
		                    <p>${data[i].review_score_readyness}</p>
		                  </div>
		                </div>
		              </div>
		              <div class="form-review-part2-2-element">
		                <p>시간준수</p>
		                <div class="form-review-part2-2-rate">
		                  <div class="form-review-part2-2-stars">`
		for(let j=0; j<5; j++) { // 별점 색칠하기 : 시간준수
			if (j < data[i].review_score_ontime) {
				formReviewElement += `<img src="/static/images/review/ic_star_full.png" alt="star_f">`
			} else {
				formReviewElement += `<img src="/static/images/review/ic_star_empty.png" alt="star_f">`
			}
		}    
		formReviewElement += `
		                  </div>
		                  <div class="form-review-part2-2-number">
		                    <p>${data[i].review_score_ontime}</p>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
		        </div>
		        <div class="form-review-part3">
		          <div class="form-review-part3-date">
		            <p>${datetimeToDate(data[i].review_createdate)}</p>
		          </div>
		          <textarea class="form-control" rows="10" style="background-color: transparent;" readonly>${data[i].review_content}</textarea>
		        </div>
		        <div class="form-review-part4">`
		if(teacherIsExist == 1) { // 선생 탈퇴의 유무, 존재하면(결과1) 프로필보기 버튼 출력
			formReviewElement += `<button class="w-100 btn btn-lg btn-secondary" type="button" onclick="location.href='/auth/detail/teacher?username=${data[i].teacher_name}'">선생님 프로필 보기</button>`
		}
		formReviewElement += `
		        </div>
		      </div>
			`
	}
	formReview.innerHTML = formReviewElement;
}

async function countUserCommonByUsername(teacher_name) {
	const url = `/api/v1/review/checkteacherisexist?username=${teacher_name}`;
	let responseData = false;
	
	await request(url)
	.then(result => {
		responseData = result.data;
	})
	.catch(error => {
		console.log(error);
	})
	return responseData;
}