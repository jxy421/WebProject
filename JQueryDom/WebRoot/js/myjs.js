//jQuery�ô�����������Ƴ��׷���
/**
 * document������dom���Ĳ���
 * $(document).ready()
 * $(function(){
 * 		load�����¼�
 * 		���ƶ�Ԫ��ע���¼�
 * 		$('#d1').bind('click','function(){}');
 * 		$('#d1').click(function(){});
 *      ������
 *          1. ��'d1'Ԫ�ؼ��������¼�
 *          2. �����񵽵����¼����첽�ص�function(){}��������
 * 	 });
 * ���load�¼���ӵ���Ӧ���������ӵ����ready������������
 * jQuery�Ὣ���read()�ϲ���һ��˳��ִ��
 */
$(function (){
    $('#d1').click(function(){
        $(this).html('Hello Ajax');
    });
    $('#d2').click(function(){
    	alert($(this).html());
    });
});
$(function (){
	$('#d21').click(function(e){
		alert($(this).html());
        //��ֹ�¼�ð��
        e.stopPropagation();
        //��ʾ�¼�����ʱ�ĺ�������
        alert(e.pageX+","+ e.pageY);
        //��ʾ�����¼��Ķ�����Ϣ
        /**
         * e.target:ָ���Ƿ����¼��ĸ���Դͷ,�����. �¼�������
         * e.currentTarget:�غ��¼���ð�ݶ��ı䣬�����ĸ�target�����ĸ�target���¼����ն�
         */
        alert($(e.target).attr('id')+" "+ $(e.currentTarget).attr('id'));
        
	});
});
$(function (){
	$('#d3').hover(
			function() {
				//�������ʱ����
				$(this).addClass("s1");
			},
			function() {
				//����Ƴ�ʱ����
                $(this).removeClass("s1");
			});
});