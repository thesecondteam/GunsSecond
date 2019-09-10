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
        if(key=="loadtype"){
                this.busiWaybillInfoData[key] = (typeof val == "undefined") ? $("#" + key).id : id;
                return this;
        }
        else if (key=="loadtype"){
            if($("#" + key).text().equals("装货")){
                this.busiWaybillInfoData[key] = 0;
                return this;
            }
            else {
                this.busiWaybillInfoData[key] = 1;
                return this;
            }
        }
        else {
            this.busiWaybillInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
            return this;
        }
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
/*    .set('endtime')*/
    .set('consigncompany')
    .set('recivecompany')
    .set('boxnumber')
    .set('startpoint')
    .set('endpoint')
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
    $.ajax({url:'../train/getTrainId',
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
                    if(!isEmpty(listBoxCode[i])){
                        var option="<option value=\""+i+"\"";
                        option += ">"+listBoxCode[i]+"</option>";  //动态添加数据
                        $("select[id=boxnumber]").append(option);
                    }
                }
            }
        }
    });
}

/**
 *动态增加站点option
 */
BusiWaybillInfoDlg.getStartStationId = function () {
    var StationId = $("select[id=startpoint]").val();
    var StationId_id = $("select[id=startpoint]").val();
    $("select[id=startpoint]").empty();      //清空
    $("#startpoint").append("<option value='0'>请选择起点站点</option>");
    var num = new Array();      //车站id
    var name = new Array();     //车站名
    /**
     * 获得车站名序列
     */
    $.ajax({
        url: '../dictStation/getStationId',
        type: "post",
        data: {
            StationId: StationId
        },
        cache: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (listStationId) {
            if(listStationId && listStationId.length != 0) {
                    for (var i = 0; i < listStationId.length; i++) {
                        if (!isEmpty(listStationId[i])) {
                            name[i] = listStationId[i];
                        }
                    }
            }
            /**
             * 获得车站id序列
             */
            $.ajax({
                url: '../dictStation/getStationId_id',
                type: "post",
                data: {
                    StationId_id: StationId_id
                },
                cache: false,
                processData: false,
                contentType: false,
                error: function () {
                },
                success: function (listStationId_id) {
                    if(listStationId_id && listStationId_id.length != 0) {
                        for (var i = 0; i < listStationId_id.length; i++) {
                            if (!isEmpty(listStationId_id[i])) {
                                num[i] = listStationId_id[i];
                            }
                        }
                        for (var i = 0; i < listStationId_id.length; i++) {
                            var option = "<option value=\"" +num[i]+ "\"";
                            option += ">" + name[i] + "</option>";  //动态添加数据
                            $("select[id=startpoint]").append(option);
                        }
                    }
                }
            });
        }
    });
}

BusiWaybillInfoDlg.getEndStationId = function () {
    var StationId = $("select[id=endpoint]").val();
    var StationId_id = $("select[id=endpoint]").val();
    $("select[id=endpoint]").empty();      //清空
    $("#endpoint").append("<option value='0'>请选择起点站点</option>");
    var num = new Array();      //车站id
    var name = new Array();     //车站名
    /**
     * 获得车站名序列
     */
    $.ajax({
        url: '../dictStation/getStationId',
        type: "post",
        data: {
            StationId: StationId
        },
        cache: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (listStationId) {
            if(listStationId && listStationId.length != 0) {
                for (var i = 0; i < listStationId.length; i++) {
                    if (!isEmpty(listStationId[i])) {
                        name[i] = listStationId[i];
                    }
                }
            }
            /**
             * 获得车站id序列
             */
            $.ajax({
                url: '../dictStation/getStationId_id',
                type: "post",
                data: {
                    StationId_id: StationId_id
                },
                cache: false,
                processData: false,
                contentType: false,
                error: function () {
                },
                success: function (listStationId_id) {
                    if(listStationId_id && listStationId_id.length != 0) {
                        for (var i = 0; i < listStationId_id.length; i++) {
                            if (!isEmpty(listStationId_id[i])) {
                                num[i] = listStationId_id[i];
                            }
                        }
                        for (var i = 0; i < listStationId_id.length; i++) {
                            var option = "<option value=\"" +num[i]+ "\"";
                            option += ">" + name[i] + "</option>";  //动态添加数据
                            $("select[id=endpoint]").append(option);
                        }
                    }
                }
            });
        }
    });
}

    BusiWaybillInfoDlg.boxNumberChange = function(){
    /*    var x = document.getElementById("boxnumber");
        alert(x);*/
/*    this.console("11");
    this.alert("1111");*/
/*    var index = document.getElementById("boxnumber").selectedIndex;
    this.console(index);*/
   /* $.ajax({url:'${ctxPath}/box/boxnumber',
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
    });*/
}
$(function() {
});
