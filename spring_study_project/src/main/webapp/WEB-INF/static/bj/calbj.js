function pickedID(obj) {
    var buttonID = $(obj).attr('id');
    var splitarray = buttonID.split("_");
    document.getElementById('idUpdate').value=splitarray[1];
}

function origin(){
    document.getElementById('idUpdate').value=-1;
}

function allow(obj){
    var buttonID = $(obj).attr('id');
    location.replace("/allowOk?name="+buttonID)
}
function kick(obj){
    var buttonID = $(obj).attr('id');
    location.replace("/deleteAllow?name="+buttonID)
}
function calDelete(obj){
    var buttonID = $(obj).attr('id');
    var splitArray = buttonID.split("_");
    location.replace("/deleteCal?id="+splitArray[1]);
}
function logout(){
    location.replace("/logout");
}