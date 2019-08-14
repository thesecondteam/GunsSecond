/**
 * 初始化港口详情对话框
 */
var HarbourInfoDlg = {
    harbourInfoData : {}
};

/**
 * 清除数据
 */
HarbourInfoDlg.clearData = function() {
    this.harbourInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HarbourInfoDlg.set = function(key, val) {
    this.harbourInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HarbourInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HarbourInfoDlg.close = function() {
    parent.layer.close(window.parent.Harbour.layerIndex);
}

/**
 * 收集数据
 */
HarbourInfoDlg.collectData = function() {
    this
    .set('harbourcode')
    .set('harbourname')
    .set('country')
    .set('province')
    .set('city')
    .set('shipnum')
    .set('volume')
    .set('statecode')
    .set('id')
    .set('spare1')
    .set('spare2');
}

/**
 * 提交添加
 */
HarbourInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/harbour/add", function(data){
        Feng.success("添加成功!");
        window.parent.Harbour.table.refresh();
        HarbourInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.harbourInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HarbourInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/harbour/update", function(data){
        Feng.success("修改成功!");
        window.parent.Harbour.table.refresh();
        HarbourInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.harbourInfoData);
    ajax.start();
}

$(function() {

});
