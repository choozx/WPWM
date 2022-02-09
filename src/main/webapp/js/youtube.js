function YoutuberDel(n){
	
	let ok = confirm('삭제 하시겠습니까?');
	
	if(ok){
		location.href='YoutuberDel?no=' + n
	}
}