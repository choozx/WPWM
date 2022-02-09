function confirmMessage(){
	
	let ok = confirm('메시지를 보내시겠습니까?')
	let messageContent1 = document.messageform.message
	if (isEmpty(messageContent1)) {

		alert('내용을 입력하세요')

		messageContent1.focus();
		messageContent1.value == "";

		return false;
 }

	if (ok) {

		return true;
	} else {
		return false;
	}
	
	
}