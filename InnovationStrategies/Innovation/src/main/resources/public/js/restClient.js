$(function(){
	$("#nameBtn").bind("click",function(){
		filter("name/" + $("#name").val())
		});
	$("#phoneBtn").bind("click",function(){
		filter("phone/" + $("#phone").val())
		});
	$("#companyBtn").bind("click",function(){
		filter("company/" + $("#company").val())
		});
	$("#ibanBtn").bind("click",function(){
		filter("iban/" + $("#iban").val())
		});
});

function filter(url){
	$.get(url, function(data){
		$("#listado").html(JSON.stringify(data));
	});
}