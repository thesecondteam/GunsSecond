/**
 * 初始化voyage详情对话框
 */
var VoyageInfoDlg = {
    voyageInfoData : {}
};

/**
 * 清除数据
 */
VoyageInfoDlg.clearData = function() {
    this.voyageInfoData = {};
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
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyageInfoDlg.set = function(key, val) {
    this.voyageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VoyageInfoDlg.close = function() {
    parent.layer.close(window.parent.Voyage.layerIndex);
}

/**
 * 收集数据
 */
VoyageInfoDlg.collectData = function() {
    this
    .set('id')
    .set('voyagenum')
    .set('imo')
    .set('starttime')
    .set('endtime')
    .set('consigncompany')
    .set('recivecompany')
    .set('boxnumber')
    .set('distance')
    .set('statecode')
    .set('loadcode')
    .set('unloadcode')
    .set('loadtype')
    .set('endpoint')
    .set('startpoint')
    .set('spareone')
    .set('sparetwo');
}

/**
 * 提交添加
 */
VoyageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyage/add", function(data){
        Feng.success("添加成功!");
        window.parent.Voyage.table.refresh();
        VoyageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyageInfoData);
    ajax.start();
}

/**
 * 提交生成
 */
VoyageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyage/create", function(data){
        Feng.success("生成成功!");
        window.parent.Voyage.table.refresh();
        VoyageInfoDlg.close();
    },function(data){
        Feng.error("生成失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyageInfoData);
    ajax.start();
}
/**
 * 提交修改
 */
VoyageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyage/update", function(data){
        Feng.success("修改成功!");
        window.parent.Voyage.table.refresh();
        VoyageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyageInfoData);
    ajax.start();
}
/**
 * 动态增加轮船号option
 */
VoyageInfoDlg.getVoyageNum = function(){
    var VoyageNum = $("select[id=imo]").val();
    $("select[id=imo]").empty();      //清空
    $("#imo").append("<option value='0'>请选择轮船号</option>");
    $.ajax({url:'/voyage/getImo',
        type:"post",
        data:{
            VoyageNum: VoyageNum
        },
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listVoyageNum){
            if(listVoyageNum && listVoyageNum.length != 0){
                for(var i=0; i<listVoyageNum.length; i++){
                    if(!isEmpty(listVoyageNum[i])){
                        var option="<option value=\""+listVoyageNum[i]+"\"";
                        option += ">"+listVoyageNum[i]+"</option>";  //动态添加数据
                        $("select[id=imo]").append(option);
                    }
                }
            }
        }
    });
}
/**
 *动态增加集装箱号option
 */
VoyageInfoDlg.getBoxCode = function () {
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

/**
 *动态增加港口option
 */
/**
 * StartPoint
 */
VoyageInfoDlg.getStartHarbourName = function () {
    var HarbourName = $("select[id=startpoint]").val();
    $("select[id=startpoint]").empty();      //清空
    $("#startpoint").append("<option value='0'>请选择起点港口</option>");
    $.ajax({
        url: '/harbour/getHarbourName',
        type: "post",
        data: {
            HarbourName: HarbourName
        },
        cache: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (listHarbourName) {
            if (listHarbourName && listHarbourName.length != 0) {
                if(listHarbourName.length > "7"){
                    for (var i = 0; i < listHarbourName.length; i++) {
                        var j = i;
                        if (!isEmpty(listHarbourName[i])) {
                            if(j == "7") {
                                j++;
                            }
                            var option = "<option value=\"" +j+ "\"";
                            option += ">" + listHarbourName[i] + "</option>";  //动态添加数据
                            $("select[id=startpoint]").append(option);
                        }
                    }
                }
                else {
                    for (var i = 0; i < listHarbourName.length; i++) {
                        if (!isEmpty(listHarbourName[i])) {
                            var option = "<option value=\"" +i+ "\"";
                            option += ">" + listHarbourName[i] + "</option>";  //动态添加数据
                            $("select[id=startpoint]").append(option);
                        }
                    }
                }
            }
        }
    });
}

/**
 * EndPoint
 */
VoyageInfoDlg.getEndHarbourName = function(){
    var HarbourName = $("select[id=endpoint]").val();
    $("select[id=endpoint]").empty();      //清空
    $("#endpoint").append("<option value='0'>请选择终点港口</option>");
    $.ajax({
        url: '/harbour/getHarbourName',
        type: "post",
        data: {
            HarbourName: HarbourName
        },
        cache: false,
        processData: false,
        contentType: false,
        error: function () {
        },
        success: function (listHarbourName) {
            if (listHarbourName && listHarbourName.length != 0) {
                if(listHarbourName.length > "7") {
                    for (var i = 0; i < listHarbourName.length; i++) {
                        var j = i;
                        if (!isEmpty(listHarbourName[i])) {
                            if(j == "7"){
                                j++;
                            }
                            var option = "<option value=\"" +j+ "\"";
                            option += ">" + listHarbourName[i] + "</option>";  //动态添加数据
                            $("select[id=endpoint]").append(option);
                        }
                    }
                }
                else {
                    for (var i = 0; i < listHarbourName.length; i++) {
                        if (!isEmpty(listHarbourName[i])) {
                            var option = "<option value=\"" +i+ "\"";
                            option += ">" + listHarbourName[i] + "</option>";  //动态添加数据
                            $("select[id=endpoint]").append(option);
                        }
                    }
                }
            }
        }
    });
}

$(function() {

});
