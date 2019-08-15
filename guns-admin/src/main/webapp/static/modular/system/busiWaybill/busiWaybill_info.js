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
 * 设置对话框中的数据
 *
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

$(function() {

});
