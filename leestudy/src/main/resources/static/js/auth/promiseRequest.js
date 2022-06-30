async function request(url, options) {
	const response = await fetch(url, options);
	if(response.ok) { // 200번 return된 경우
		return response.json()
	} else if(response.json().then(result => {
		return result.code;
	}) == -1 ){ // CustomValidationApiException.java 에서의 -1이 포함되어 return된 경우
		return response.json()
	} else {
		throw new Error("response Error : " + response);
	}
}