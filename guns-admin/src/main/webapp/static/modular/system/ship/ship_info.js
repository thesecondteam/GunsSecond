/**
 * 初始化船舶详情对话框
 */
var ShipInfoDlg = {
    shipInfoData : {},
    validateFields: {
        imo: {
            validators: {
                notEmpty: {
                    message: '轮船IMO号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ShipInfoDlg.clearData = function() {
    this.shipInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShipInfoDlg.set = function(key, val) {
    this.shipInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShipInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ShipInfoDlg.close = function() {
    parent.layer.close(window.parent.Ship.layerIndex);
}

/**
 * 收集数据
 */
ShipInfoDlg.collectData = function() {
    this
    .set('imo')
    .set('shipcname')
    .set('shipename')
    .set('country')
    .set('shiplength')
    .set('shipwidth')
    .set('tonnage')
    .set('mmsi')
    .set('contacts')
    .set('telphone')
    .set('statecode')
    .set('id')
    .set('spare1')
    .set('spare2');
}
/**
 * 验证数据是否为空
 */
ShipInfoDlg.validate = function () {
    $('#shipInfoForm').data("bootstrapValidator").resetForm();
    $('#shipInfoForm').bootstrapValidator('validate');
    return $("#shipInfoForm").data('bootstrapValidator').isValid();
}
/**
 * 提交添加
 */
ShipInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ship/add", function(data){
        if(data.code=="2336")
        {
            Feng.error(data.message);
        }
        else {
            Feng.success("添加成功!");
        }
        window.parent.Ship.table.refresh();
        ShipInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shipInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ShipInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ship/update", function(data){
        if(data.code=="2336")
        {
            Feng.error(data.message);
        }
        else {
            Feng.success("修改成功!");
        }
        window.parent.Ship.table.refresh();
        ShipInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shipInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("shipInfoForm", ShipInfoDlg.validateFields);

});
