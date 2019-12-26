//记住密码
window.onload = function () {
    var sName = localStorage.getItem("keyName");
    var sPass = localStorage.getItem("keyPass");
    var flag=localStorage.getItem("flag");
    var sId=this.localStorage.getItem("keyId");
    if (sName) {
        document.getElementById("login-name").value = sName;
    }
    if (sPass) {
        document.getElementById("login-password").value = sPass;
    }
    if(flag==1){
        document.getElementById("login-save").checked=true;
        
    }
    if(sId){
        document.getElementById("login-Id").selected=sId;
        
    }

    document.getElementById("login-btn").onclick = function () {
        var sName = document.getElementById("login-name").value;
        var sPass = document.getElementById("login-password").value;
        var sId=document.getElementById("login-id").value;
        if (document.getElementById("login-save").checked) {
            localStorage.setItem("keyName", sName);
            localStorage.setItem("keyPass", sPass);
            localStorage.setItem("flag",1);
            localStorage.setItem("keyId",sId);

        } else {
            localStorage.removeItem("ketName");
            localStorage.removeItem("keyPass");
            localStorage.setItem("flag",0);
            localStorage.removeItem("keyId");
        }
    }
}
