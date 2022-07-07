history.replaceState({}, null, location.pathname)

const btnReviewWriteCancel = document.querySelector('.btnReviewWriteCancel') // 취소 버튼
const btnReviewWriteSubmit = document.querySelector('.btnReviewWriteSubmit') // 리뷰작성 버튼
const starsProfessionality = document.querySelectorAll('.star-professionality') // 
const starsTeaching = document.querySelectorAll('.star-teaching') // 
const starsReadyness = document.querySelectorAll('.star-readyness') // 
const starsOnTime = document.querySelectorAll('.star-ontime') // 
const inputReviewContent = document.querySelector('.inputReviewContent') // 

let starsProfessionalityPoint = 0;
let starsTeachingPoint = 0;
let starReadynessPoint = 0;
let starOnTimePoint = 0;

btnReviewWriteCancel.onclick = () => {
	history.back();
}

btnReviewWriteSubmit.onclick = () => {
	if (starsProfessionalityPoint == 0 || starsTeachingPoint == 0 || starReadynessPoint == 0 || starOnTimePoint == 0) {
		alert("평점을 모두 매기지 않으셨습니다.")
	} else if (!(inputReviewContent.value.length > 49) || !(inputReviewContent.value.length < 501)){
		alert("리뷰는 50~500자 내로 작성바랍니다.")
	} else {
		if(confirm("리뷰를 작성하시겠습니까?\n작성하신 리뷰는 수정하실 수 없습니다.")) {
			saveReview(matchinginfo, teacherinfo, studentinfo);
		}
	}
}

async function saveReview(matchinginfo, teacherinfo, studentinfo) {
	let url = `/api/v1/review/savereview`;
	let option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			matching_code: matchinginfo.matching_code,
			teacher_name: matchinginfo.teacher_name,
			student_name: matchinginfo.student_name,
			matching_startdate: matchinginfo.matching_startdate,
			teacher_nickname: teacherinfo.teacher_nickname,
			teacher_picture: teacherinfo.teacher_picture,
			teacher_university: teacherinfo.teacher_university,
			university_name: teacherinfo.university_name,
			teacher_university_isgraduate: teacherinfo.teacher_university_isgraduate,
			teacher_university_major: teacherinfo.teacher_university_major,
			teacher_university_studentnum: teacherinfo.teacher_university_studentnum,
			teacher_price: teacherinfo.teacher_price,
			teacher_gender: teacherinfo.teacher_gender,
			student_nickname: studentinfo.student_nickname,
			student_picture: studentinfo.student_nickname,
			student_address_part1: studentinfo.student_address_part1,
			student_address_part2: studentinfo.student_address_part2,
			student_student_grade: studentinfo.student_student_grade,
			review_score_professionality: starsProfessionalityPoint,
			review_score_readyness: starReadynessPoint,
			review_score_teaching: starsTeachingPoint,
			review_score_ontime: starOnTimePoint,
			review_content: inputReviewContent.value
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
			alert("리뷰를 작성하였습니다.")
			location.replace("/auth/matching");
		})
		.catch(error => console.log(error));
}

for (let i=0; i<starsProfessionality.length; i++) { // 전문성 별점에 대한 클릭이벤트
	starsProfessionality[i].onclick = () => {
		starsProfessionalityPoint = i+1 // 점수 부여
		for(let j=0; j<starsProfessionality.length; j++) { // 우선 전체 별을 다 비운 후
			starsProfessionality[j].src="/static/images/review/ic_star_empty.png"
		}
		for(let k=0; k<i+1; k++) { // 해당 점수의 인덱스까지만 별을 채우기
			starsProfessionality[k].src="/static/images/review/ic_star_full.png"
		}
	}
}
for (let i=0; i<starsTeaching.length; i++) { // 강의력 별점에 대한 클릭이벤트
	starsTeaching[i].onclick = () => {
		starsTeachingPoint = i+1 // 점수 부여
		for(let j=0; j<starsTeaching.length; j++) { // 우선 전체 별을 다 비운 후
			starsTeaching[j].src="/static/images/review/ic_star_empty.png"
		}
		for(let k=0; k<i+1; k++) { // 해당 점수의 인덱스까지만 별을 채우기
			starsTeaching[k].src="/static/images/review/ic_star_full.png"
		}
	}
}
for (let i=0; i<starsReadyness.length; i++) { // 준비도 별점에 대한 클릭이벤트
	starsReadyness[i].onclick = () => {
		starReadynessPoint = i+1 // 점수 부여
		for(let j=0; j<starsReadyness.length; j++) { // 우선 전체 별을 다 비운 후
			starsReadyness[j].src="/static/images/review/ic_star_empty.png"
		}
		for(let k=0; k<i+1; k++) { // 해당 점수의 인덱스까지만 별을 채우기
			starsReadyness[k].src="/static/images/review/ic_star_full.png"
		}
	}
}
for (let i=0; i<starsOnTime.length; i++) { // 시간준수 별점에 대한 클릭이벤트
	starsOnTime[i].onclick = () => {
		starOnTimePoint = i+1 // 점수 부여
		for(let j=0; j<starsOnTime.length; j++) { // 우선 전체 별을 다 비운 후
			starsOnTime[j].src="/static/images/review/ic_star_empty.png"
		}
		for(let k=0; k<i+1; k++) { // 해당 점수의 인덱스까지만 별을 채우기
			starsOnTime[k].src="/static/images/review/ic_star_full.png"
		}
	}
}
