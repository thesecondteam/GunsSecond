/**
 * 初始化场地业务操作模块详情对话框
 */
var BusiAreaMoveInfoDlg = {
    busiAreaMoveInfoData : {}
};

/**
 * 清除数据
 */
BusiAreaMoveInfoDlg.clearData = function() {
    this.busiAreaMoveInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiAreaMoveInfoDlg.set = function(key, val) {
    this.busiAreaMoveInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiAreaMoveInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiAreaMoveInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiAreaMove.layerIndex);
}

/**
 * 收集数据
 */
BusiAreaMoveInfoDlg.collectData = function() {
    this
    .set('id')
    .set('oldArea')
    .set('newArea')
    .set('operTime')
    .set('operPerson')
    .set('boxId');
}

/**
 * 提交添加
 */
BusiAreaMoveInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiAreaMove/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusiAreaMove.table.refresh();
        BusiAreaMoveInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiAreaMoveInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusiAreaMoveInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiAreaMove/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiAreaMove.table.refresh();
        BusiAreaMoveInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiAreaMoveInfoData);
    ajax.start();
}

$(function() {

});
