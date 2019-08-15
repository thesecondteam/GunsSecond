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

$(function() {

});
