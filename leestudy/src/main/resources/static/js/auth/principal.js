/**
 * 세션정보 요청
 */

async function getInfoUserCommon() {
	const url = "/api/v1/auth/info-usercommon";
	
	const response = await fetch(url);
	if(response.ok) {
		return response.json(); // promise로 return
	} else {
		throw new Error("Failed to get Authentication." + response)
	}
}

async function getInfoUserDetail() {
	const url = "/api/v1/auth/info-userdetail";
	
	const response = await fetch(url);
	if(response.ok) {
		return response.json(); // promise로 return
	} else {
		throw new Error("Failed to get Authentication." + response)
	}
}