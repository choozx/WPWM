// JS 라이브러리 완성!


// 일반적으로 유호성 검사하는 것들을 정리해보자!

//  why ? 함수형태로 정리해서 나중에 필요할 때 쓰려고(쓰기 편하게) 만듬
//  .jar 쓰던 것처럼(라이브러리)




// 문제가 있으면 true, 괜찮으면 false

// <input>을 넣으면..
// 거기에 글자가 없으면 true, 있으면 false

function isEmpty(input){
	
//	if(input.value == ""){ 1번째 방법
//		return true; 
//      }
	return !input.value;      // 2번째 방법 -> '값이 없다'라는 뜻
							 // js에서는 '' 랑 false는 같은 의미이니깐 
							 // input이 공백이면 input.value이 false가 되고 그걸 !로 꺾으니까 true가 됨 
	
}
	
// <input>이랑 글자 수를 넣으면
// 그 글자 수보다 적으면 true, 아니면 false
	
function lessThan(input, length){
	return input.value.length < length;
}	



// <input>을 넣으면
// 한글/특수문자 들어있으면 true, 아니면 false  (ex. ID 유효성 검사)

function containKR(input){
	
	//input.value = '3지';
	
	let ok = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890@._";
				// 한글이 안되는 걸 생각하는 거니까 그 반대를 변수로 만들어줬음
				
	for(let i = 0; i < input.value.length; i++){
		
		if(ok.indexOf(input.value[i]) == -1){
			return true;
		}
		
	}

}

// Test 

// <input> x 2 넣으면 
// 내용이 다르면 true, 아니면 false  (ex. 비번확인)

function notEquals(input1, input2){
		return input1.value != input2.value;
}
	

// <input>, 문자열 세트
// 그 문자열 세트가 포함 안되있으면 true
// 들어옸이면 false (ex. 비밀번호 조합시킬 때)

function notContains(input, set){
	// input : 1qwerASD
	// input : ASD
	
	// set : 1234567890  => 숫자를 반드시 포함시키겠다라는 의미
	
	for(let i = 0; i < set.length; i++){
		if(input.value.indexOf(set[i]) != -1){ // -1의 의미는 "없다"
			return false;
	}
	
 }
	return true;
}	

//  <input>을 넣어서
// 숫자가 아니면 true, 숫자면 false

function isNotNumber(input){
	return isNaN(input.value);
}

function isNotType(input, type){
	type = "." + type; // .jpg
	return input.value.indexOf(type) == -1; // Inputdp . jpg
}

