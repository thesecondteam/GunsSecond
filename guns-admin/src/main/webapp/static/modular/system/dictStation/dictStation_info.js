/**
 * 初始化详情对话框
 */
var DictStationInfoDlg = {
    dictStationInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '站点名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DictStationInfoDlg.clearData = function() {
    this.dictStationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictStationInfoDlg.set = function(key, val) {
    this.dictStationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictStationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DictStationInfoDlg.close = function() {
    parent.layer.close(window.parent.DictStation.layerIndex);
}

/**
 * 收集数据
 */
DictStationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('province')
    .set('statecode')
    .set('stationid');
}
/**
 * 验证数据是否为空
 */
DictStationInfoDlg.validate = function () {
    $('#dictStationInfoForm').data("bootstrapValidator").resetForm();
    $('#dictStationInfoForm').bootstrapValidator('validate');
    return $("#dictStationInfoForm").data('bootstrapValidator').isValid();
}
/**
 * 提交添加
 */
DictStationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dictStation/add", function(data){
        if(data.code=="2333")
        {
            Feng.error(data.message);
        }
        else {
            Feng.success("添加成功!");
        }
        window.parent.DictStation.table.refresh();
        DictStationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dictStationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DictStationInfoDlg.editSubmit = function() {
    console.log("================>");
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dictStation/update", function(data){

        if(data.code=="2333")
        {
            Feng.error(data.message);
        }
        else {
            Feng.success("修改成功!");
        }
        window.parent.DictStation.table.refresh();
        DictStationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dictStationInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("dictStationInfoForm", DictStationInfoDlg.validateFields);

});
