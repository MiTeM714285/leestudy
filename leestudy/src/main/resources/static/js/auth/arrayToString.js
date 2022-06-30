function arrayToString(array) { // 배열을 쉼표가 있는 문자열로 변환하는 함수
	let string = ""
	for(let i of array) {
		string += i;
		string += ","
	}
	string = string.slice(0, -1);
	return string;
}