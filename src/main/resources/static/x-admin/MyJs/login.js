/**
 * login.html对应的JS
 */
$(function  () {
    layui.use('form', function(){
      var form = layui.form;
      //监听提交
      form.on('submit(login)', function(data){
    	  //打印一下填写的值然后区后台进行登陆
        $.post("doLogin.html",data.field,function(result){
        	if(result!=null && result.success == true){
        		window.location = result.data;
        	}else{
        		layer.msg(result.msg);
        	}
        },'json');
        return false;
      });
    });
})

function getRandomSrc() {
    var rnd = Math.ceil(Math.random() * 8);
    return rnd;
}

+ function() {
    //设置初始化设置
    var width = window.innerWidth > 0 ? window.innerWidth : document.body.clientWidth; //宽度
    var height = window.innerHeight > 0 ? window.innerHeight : document.body.clientHeight; //高度
    $("#img1").attr("width", width);
    $("#img1").attr("height", height);
    //动画效果
    var changeSize = 100;
    var time = 3000;
    var heightChangeSize = changeSize * height / width;
    var bigWidth = width + changeSize;
    var bigHeight = height + heightChangeSize;
    $("#img1").animate({
        width: bigWidth,
        height: bigHeight,
        left: -changeSize / 2,
        top: -heightChangeSize / 2
    }, time);
    var flag = 0;
    setInterval(function() {
        if (flag == 1) {
            flag = 0;
            $("#img1").animate({
                width: bigWidth,
                height: bigHeight,
                left: -changeSize / 2,
                top: -heightChangeSize / 2
            }, time);
        } else {
            flag = 1;
            $("#img1").animate({
                width: width,
                height: height,
                left: "0",
                top: "0"
            }, time);
        }
    }, time);
}();