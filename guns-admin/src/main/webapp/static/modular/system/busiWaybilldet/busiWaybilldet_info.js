/**
 * 初始化运单详情管理详情对话框
 */
var BusiWaybilldetInfoDlg = {
    busiWaybilldetInfoData : {}
};

/**
 * 清除数据
 */
BusiWaybilldetInfoDlg.clearData = function() {
    this.busiWaybilldetInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiWaybilldetInfoDlg.set = function(key, val) {
    this.busiWaybilldetInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiWaybilldetInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiWaybilldetInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiWaybilldet.layerIndex);
}


/**
 * 收集数据
 */
BusiWaybilldetInfoDlg.collectData = function() {
    this
    .set('id')
    .set('waybilldetid')
    .set('waybillid')
    .set('boxid')
    .set('boxtype')
    .set('boxsize')
    .set('boxgoods')
    .set('operation')
    .set('createtime')
    .set('statecode')
    .set('finish');
}

/**
 * 提交添加
 */
BusiWaybilldetInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiWaybilldet/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusiWaybilldet.table.refresh();
        BusiWaybilldetInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiWaybilldetInfoData);
    ajax.start();
}

/**
 * 提交生成
 */
BusiWaybilldetInfoDlg.createSubmit = function() {

    this.clearData();
    this.collectData(); 

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiWaybilldet/create", function(data){
        Feng.success("生成成功!");
        window.parent.BusiWaybilldet.table.refresh();
        BusiWaybilldetInfoDlg.close();
    },function(data){
        Feng.error("生成失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiWaybilldetInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusiWaybilldetInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiWaybilldet/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiWaybilldet.table.refresh();
        BusiWaybilldetInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiWaybilldetInfoData);
    ajax.start();
}
/**
 *动态增加集装箱号option
 */
BusiWaybilldetInfoDlg.getBoxCode = function(){
    var BoxCode = $("select[id=boxid]").val();
    $("select[id=boxid]").empty();      //清空
    $("#boxid").append("<option value='0'>请选择集装箱箱号</option>");
    $.ajax({url:'../box/getBoxCode',
        type:"post",
        data:{
            BoxCode : BoxCode
        },
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listBoxCode){
            if(listBoxCode && listBoxCode.length != 0){
                for(var i=0; i<listBoxCode.length; i++){
                    if(listBoxCode[i]!=""){
                        var option="<option value=\""+listBoxCode[i]+"\"";
                        option += ">"+listBoxCode[i]+"</option>";  //动态添加数据
                        $("select[id=boxid]").append(option);
                    }
                }
            }
        }
    });
}
/**
 *动态增加运单号option
 */
BusiWaybilldetInfoDlg.getWaybillId = function () {
    var WaybillId = $("select[id=waybillid]").val();
    $("select[id=waybillid]").empty();      //清空
    $("#waybillid").append("<option value='0'>请选择运单号</option>");
    $.ajax({url:'../busiWaybill/getWaybillId',
        type:"post",
        data:{
            WaybillId : WaybillId
        },
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listWaybillId){
            if(listWaybillId && listWaybillId.length != 0){
                for(var i=0; i<listWaybillId.length; i++){
                    if(listWaybillId[i]!=""){
                        var option="<option value=\""+listWaybillId[i]+"\"";
                        option += ">"+listWaybillId[i]+"</option>";  //动态添加数据
                        $("select[id=waybillid]").append(option);
                    }
                }
            }
        }
    });
}

BusiWaybilldetInfoDlg.boxOnchange=function (x) {
    console.log(x);
}
function Onchange(x){
    console.log(x);
}
$(function() {

});
