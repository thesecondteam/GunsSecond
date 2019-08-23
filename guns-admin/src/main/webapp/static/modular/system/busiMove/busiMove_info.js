/**
 * 初始化业务操作记录详情对话框
 */
var BusiMoveInfoDlg = {
    busiMoveInfoData : {}
};

/**
 * 清除数据
 */
BusiMoveInfoDlg.clearData = function() {
    this.busiMoveInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiMoveInfoDlg.set = function(key, val) {
    this.busiMoveInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiMoveInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiMoveInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiMove.layerIndex);
}

/**
 * 收集数据
 */
BusiMoveInfoDlg.collectData = function() {
    this
    .set('id')
    .set('oldArea')
    .set('newArea')
    .set('boxCode')
    .set('operTime')
    .set('operPerson')
    .set('opeType');
}

/**
 * 提交添加
 */
BusiMoveInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiMove/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusiMove.table.refresh();
        BusiMoveInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiMoveInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusiMoveInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiMove/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiMove.table.refresh();
        BusiMoveInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiMoveInfoData);
    ajax.start();
}

$(function() {

});
