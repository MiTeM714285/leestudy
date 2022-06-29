let phoneNumCheckFlag = false;
let noUniversityFlag = false;
let sendSMSNum;

const inputUsername = document.querySelector('.inputUsername') // 1. 아이디 입력란
const inputPassword = document.querySelector('.inputPassword') // 2-1. 비밀번호 입력란
const inputPasswordAgain = document.querySelector('.inputPasswordAgain') // 2-2. 비밀번호 재입력란
const inputNickname = document.querySelector('.inputNickname') // 3. 닉네임 입력란
const inputEmail = document.querySelector('.inputEmail') // 4. 이메일 입력란
let radioAvailableRemoteValue = 0 // 5. 과외원격여부 라디오버튼 값
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

const btnPhoneNumCheck1 = document.querySelector('.btnPhoneNumCheck1') // 인증번호 요청 버튼
const btnPhoneNumCheck2 = document.querySelector('.btnPhoneNumCheck2') // 인증완료 버튼
const btnTeacherModifyComplete = document.querySelector('.btnTeacherModifyComplete') // 수정완료 버튼