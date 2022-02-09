function confirmdel() {

	let ok = confirm('정말 삭제하시겠습니까?')
	let duoDelInput1 = document.deleteform.userPw

	if (isEmpty(duoDelInput1) || lessThan(duoDelInput1, 4) || isNotNumber(duoDelInput1)) {

		alert('숫자 4자리를 입력하세요')

		duoDelInput1.focus();
		duoDelInput1.value == "";

		return false;

	}

	if (ok) {

		return true;
	} else {
		return false;
	}


}
function duoRegCheck() {
	let duoRegInput2 = document.duoRegForm.duoMemo
	let duoRegInput3 = document.duoRegForm.duoPw

	

	

	if (isEmpty(duoRegInput2) || lessThan(duoRegInput2, 3)) {

		alert('3글자 이상 입력하세요')

		duoRegInput2.focus();
		duoRegInput2.value == "";

		return false;

	}
	if (isEmpty(duoRegInput3) || lessThan(duoRegInput3, 4) || isNotNumber(duoRegInput3)) {

		alert('숫자 4자리를 입력하세요')

		duoRegInput3.focus();
		duoRegInput3.value == "";

		return false;

	}


	return true;
}

function duoUpdateCheck() {

	let duoUpdateInput1 = document.duoUpdateForm.duoPw

	if (isEmpty(duoUpdateInput1) || lessThan(duoUpdateInput1, 4) || isNotNumber(duoUpdateInput1)) {

		alert('숫자 4자리를 입력하세요')

		duoUpdateInput1.focus();
		duoUpdateInput1.value == "";

		return false;

	}
}
	
function duoSearchCheck() {

	let duoSearchInput1 = document.duoSearchForm.searchNick

	if (isEmpty(duoSearchInput1) || lessThan(duoSearchInput1, 1)) {

		alert('닉네임을 한글자 이상 입력하세요');

		duoSearchInput1.focus();
		duoSearchInput1.value == "";

		return false;

	}	


}




