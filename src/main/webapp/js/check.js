function call(){
	
	
let i1Input = document.myForm.i1;
let i2Input = document.myForm.i2;
let i3Input = document.myForm.i3;
let i4Input = document.myForm.i4;
let i5Input = document.myForm.i5;
	
	
	if(isEmpty(i1Input)
		|| lessThan(i1Input, 3) || containKR(i1Input)){
		alert('필수입력 에러');
		
		i1Input.focus();
		i1Input.value == "";
		
		return false;
		
	}
	
	/*
	if(lessThan(i1Input, 3)){ 
		alert('3글자 이상 입력하세요!');   위에 lessThan이랑 기능쪽으로는 같고, 상세안내만 다를 뿐임
		i1Input.value = "";
		i1Input.focus();
		
		return false;	
	}
	*/
	
	if(lessThan(i2Input, 3)){
		alert('3글자 이상 입력하세요');
		
		i2Input.focus();
		i2Input.value == "";
		
		return false;
	}
	
	if(lessThan(i3Input, 5)){
		alert('비번 양식에 맞춰 주세요');
		
		i3Input.value = "";
		i3Input.focus();
		
		return false;
		
	}
	if(notContains(i3Input, "1234567890")
	 || notContains(i3Input, "QWERTYUIOPLKJHGFDSAZXCVBNM")){
		alert('대문자, 숫자 포함');
		
		i3Input.value = "";
		i3Input.focus();
		
		return false;
	}
	
	if(notEquals(i3Input, i4Input)){
		alert('비밀번호 재확인 실패');
		
		i3Input.value = "";
		i4Input.value = "";
		i4Input.focus();
		
		return false;
	}


	if(isEmpty(i5Input) || isNotNumber(i5Input)){
		alert('숫자만 입력하세요');
		
		i5Input.value = "";
		i5Input.focus();
		return false;
	}
	return false;	 // return true를 쓰면 jsp랑 쪽으로 값이 넘어가게 됨
					 // 값을 넘기고 싶을 때는 true 쓰면 됨
}	
// 추가로 따로 isEmpty라는 fuction을 만들건데 그건 새로 js파일을 만들어서 만들거임
// why? i2,i3,i4,i5도 똑같이 만들어줘야해서 그걸 간단하게 하기 위해서 isEmpty라는 fuction을 만들어준거임
// 순서는 validCheck.js에 있는 isEmpty라는 fuction이 먼저 계산되고 check.js에 있는 if문이 계산됨

function check(){
	
	let ImgInput = document.myForm.img; //document :화면전체 
	
	if(isNotType(ImgInput, "jpg") && isNotType(ImgInput, "png")){
		alert('file error');
		return false;
	}
	return false;
}
function regCheck(){
	
	let regInput1 = document.loginForm.id;
	let regInput2 = document.loginForm.pw;
	
	if(isEmpty(regInput1)){
		alert('아이디를 입력하세요.');
		regInput1.focus();
		regInput1.value == "";
		return false; // 동작이 안넘어가게
	}
	if(isEmpty(regInput2)){
		alert('비밀번호를 입력하세요.');
		regInput2.focus();
		regInput2.value == "";
		return false; // 동작이 안넘어가게
	}
	
	
}
