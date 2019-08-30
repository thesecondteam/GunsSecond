/**
 * 初始化操作记录详情对话框
 */
var BusiRecordInfoDlg = {
    busiRecordInfoData : {}
};

/**
 * 清除数据
 */
BusiRecordInfoDlg.clearData = function() {
    this.busiRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiRecordInfoDlg.set = function(key, val) {
    this.busiRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiRecord.layerIndex);
}

/**
 * 收集数据
 */
BusiRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('oldcontent')
    .set('newcontent')
    .set('oprman')
    .set('optime')
    .set('optype');
}

/**
 * 提交添加
 */
BusiRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusiRecord.table.refresh();
        BusiRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusiRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiRecord.table.refresh();
        BusiRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiRecordInfoData);
    ajax.start();
}

$(function() {

});
