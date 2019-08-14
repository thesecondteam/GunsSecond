/**
 * 初始化详情对话框
 */
var BoxsizeInfoDlg = {
    boxsizeInfoData : {}
};

/**
 * 清除数据
 */
BoxsizeInfoDlg.clearData = function() {
    this.boxsizeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BoxsizeInfoDlg.set = function(key, val) {
    this.boxsizeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BoxsizeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BoxsizeInfoDlg.close = function() {
    parent.layer.close(window.parent.Boxsize.layerIndex);
}

/**
 * 收集数据
 */
BoxsizeInfoDlg.collectData = function() {
    this
    .set('sizecode')
    .set('sizetype')
    .set('inlength')
    .set('inwidth')
    .set('inheight')
    .set('outheight')
    .set('outwidth')
    .set('volume')
    .set('capacity')
    .set('statecode')
    .set('id');
}

/**
 * 提交添加
 */
BoxsizeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/boxsize/add", function(data){
        Feng.success("添加成功!");
        window.parent.Boxsize.table.refresh();
        BoxsizeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.boxsizeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BoxsizeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/boxsize/update", function(data){
        Feng.success("修改成功!");
        window.parent.Boxsize.table.refresh();
        BoxsizeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.boxsizeInfoData);
    ajax.start();
}

$(function() {

});
