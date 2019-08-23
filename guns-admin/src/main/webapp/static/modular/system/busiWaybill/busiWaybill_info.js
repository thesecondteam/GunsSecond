/**
 * 初始化运单管理详情对话框
 */
var BusiWaybillInfoDlg = {
    busiWaybillInfoData : {}
};

/**
 * 清除数据
 */
BusiWaybillInfoDlg.clearData = function() {
    this.busiWaybillInfoData = {};
}

/**
* 判断字符串是否为空
*/
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

/**
 * 设置对话框中的数据
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiWaybillInfoDlg.set = function(key, val) {
    this.busiWaybillInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiWaybillInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiWaybillInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiWaybill.layerIndex);
}

/**
 * 收集数据
 */
BusiWaybillInfoDlg.collectData = function() {
    this
    .set('id')
    .set('waybillid')
    .set('trainnum')
    .set('starttime')
    .set('endtime')
    .set('consigncompany')
    .set('recivecompany')
    .set('boxnumber')
    .set('startpoint')
    .set('endpiont')
    .set('distance')
    .set('statecode')
    .set('loadtype')
    .set('loadcode')
    .set('unloadcode');
}

/**
 * 提交添加
 */
BusiWaybillInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiWaybill/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusiWaybill.table.refresh();
        BusiWaybillInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiWaybillInfoData);
    ajax.start();
}
/**
 * 提交生成
 */
BusiWaybillInfoDlg.createSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiWaybill/create", function(data){
        Feng.success("生成成功!");
        window.parent.BusiWaybill.table.refresh();
        BusiWaybillInfoDlg.close();
    },function(data){
        Feng.error("生成失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiWaybillInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusiWaybillInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiWaybill/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiWaybill.table.refresh();
        BusiWaybillInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiWaybillInfoData);
    ajax.start();
}

/**
 *动态增加车次option
 */
BusiWaybillInfoDlg.getTrainId = function () {
    var TrainId = $("select[id=trainnum]").val();
    $("select[id=trainnum]").empty();      //清空
    $("#trainnum").append("<option value='0'>请选择车次</option>");
    $.ajax({url:'/train/getTrainId',
        type:"post",
        data:{
            TrainId : TrainId
        },
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listTrainId){
            if(listTrainId && listTrainId.length != 0){
                for(var i=0; i<listTrainId.length; i++){
                    if(!isEmpty(listTrainId[i])){
                        var option="<option value=\""+listTrainId[i]+"\"";
                        option += ">"+listTrainId[i]+"</option>";  //动态添加数据
                        $("select[id=trainnum]").append(option);
                    }
                }
            }
        }
    });
}

/**
 *动态增加集装箱号option
 */
BusiWaybillInfoDlg.getBoxCode = function () {
    var BoxCode = $("select[id=boxnumber]").val();
    $("select[id=boxnumber]").empty();      //清空
    $("#boxnumber").append("<option value='0'>请选择集装箱箱号</option>");
    $.ajax({url:'/box/getBoxCode',
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
                    if(!isEmpty(listBoxCode[i])){
                        var option="<option value=\""+listBoxCode[i]+"\"";
                        option += ">"+listBoxCode[i]+"</option>";  //动态添加数据
                        $("select[id=boxnumber]").append(option);
                    }
                }
            }
        }
    });
}
$(function() {
});
