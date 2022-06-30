function checkpassword() {
    var x =document.getElementById("f-password").value
    var y = document.getElementById("s-password").value
    if (x!=y){
        alert("前后两次密码不一样，请重新修改！");
        document.getElementById("s-password").value="";
    }
}
function checkPhone(){
    var phone = document.getElementById('phone').value;
    if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))){
        alert("手机号码有误，请重填");
        document.getElementById("phone").value="";
    }
}
var RegEmail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
function check_email(){
    var e= document.getElementById("e-mail").value
    if (!RegEmail.test(e)){
        alert("邮箱格式不正确，请重新修改！")
        document.getElementById("e-mail").value="";
    }
}
function check_all(){
    var username=document.getElementById("username").value;
    var nickname=document.getElementById("nickname").value;
    var fpassword=document.getElementById("f-password").value;
    var spassword=document.getElementById("s-password").value;
    var phone    =document.getElementById("phone").value;
    var email=document.getElementById("e-mail").value;

    if (username == "请输入用户名" ||nickname=="请输入昵称" ||fpassword=="请输入密码"||spassword=="请确认密码"||phone=="请输入电话号码"||email=="请输入邮箱"){
        alert("信息尚未完整，请继续填写👇")
        return false;

    }else {
        return true;
    }
    console.log(check_all())


}