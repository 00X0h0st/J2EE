//function setErrorTimeout() {
//	
//}

(function() {
	var i = 5;
	setInterval(showSecond, 1000);

	//�ú���ʵ�ָı���ʾ�������������ʵ���Զ���ת
	function showSecond() {
		if(i > 0) {
			i --;
			$("#seconds")[0].innerHTML = i;
		} else {
			location.href = "../common/common_toIndex";
		}	
	}
})();


