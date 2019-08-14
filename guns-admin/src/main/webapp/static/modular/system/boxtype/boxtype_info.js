/**
 * 初始化详情对话框
 */
var BoxtypeInfoDlg = {
    boxtypeInfoData : {}
};

/**
 * 清除数据
 */
BoxtypeInfoDlg.clearData = function() {
    this.boxtypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BoxtypeInfoDlg.set = function(key, val) {
    this.boxtypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BoxtypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BoxtypeInfoDlg.close = function() {
    parent.layer.close(window.parent.Boxtype.layerIndex);
}

/**
 * 收集数据
 */
BoxtypeInfoDlg.collectData = function() {
    this
    .set('typecode')
    .set('type')
    .set('statecode')
    .set('id');
}

/**
 * 提交添加
 */
BoxtypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/boxtype/add", function(data){
        Feng.success("添加成功!");
        window.parent.Boxtype.table.refresh();
        BoxtypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.boxtypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BoxtypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/boxtype/update", function(data){
        Feng.success("修改成功!");
        window.parent.Boxtype.table.refresh();
        BoxtypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.boxtypeInfoData);
    ajax.start();
}

$(function() {

});
