function getXhr() {
	var xhr = null;
	if(window.XMLHttpRequest){
		xhr =  new XMLHttpRequest();
	}else{
		xhr = ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}
//��ȡdom����
function $(id) {
	return document.getElementById(id);
}
//��ȡdom����ֵ
function $F(id) {
	return $(id).value;
}