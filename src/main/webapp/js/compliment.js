function duoRegCheck() {
	let CRegInput1 = document.duoRegForm.name
	let CRegInput2 = document.duoRegForm.CMemo
	let CRegInput3 = document.duoRegForm.CPw

	if(isEmpty(CRegInput1)){
		
		alert('칭찬할 닉네임을 입력하세요')
		
		CRegInput1.focus();
		CRegInput1.value == "";

		return false;
		
	}


	if (isEmpty(CRegInput2) || lessThan(CRegInput2, 3)) {

		alert('칭찬할 내용을 입력하세요')

		CRegInput2.focus();
		CRegInput2.value == "";

		return false;

	}
	if (isEmpty(CRegInput3) || lessThan(CRegInput3, 4) || isNotNumber(CRegInput3)) {

		alert('숫자 4자리를 입력하세요')

		CRegInput3.focus();
		CRegInput3.value == "";

		return false;

	}


	return true;
}

function CUpdateCheck() {

	let CUpdateInput1 = document.duoUpdateForm.CPw

	if (isEmpty(CUpdateInput1) || lessThan(CUpdateInput1, 4) || isNotNumber(CUpdateInput1)) {

		alert('숫자 4자리를 입력하세요')

		CUpdateInput1.focus();
		CUpdateInput1.value == "";

		return false;

	}
}