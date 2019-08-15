/**
 * 初始化voyagedet详情对话框
 */
var VoyagedetInfoDlg = {
    voyagedetInfoData : {}
};

/**
 * 清除数据
 */
VoyagedetInfoDlg.clearData = function() {
    this.voyagedetInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyagedetInfoDlg.set = function(key, val) {
    this.voyagedetInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyagedetInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VoyagedetInfoDlg.close = function() {
    parent.layer.close(window.parent.Voyagedet.layerIndex);
}

/**
 * 收集数据
 */
VoyagedetInfoDlg.collectData = function() {
    this
    .set('id')
    .set('voyagedetid')
    .set('voyagenum')
    .set('boxid')
    .set('boxtype')
    .set('boxsize')
    .set('boxgoods')
    .set('statecode')
    .set('createtime')
    .set('operation')
    .set('finish')
    .set('spareone')
    .set('sparetwo');
}

/**
 * 提交添加
 */
VoyagedetInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyagedet/add", function(data){
        Feng.success("添加成功!");
        window.parent.Voyagedet.table.refresh();
        VoyagedetInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyagedetInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VoyagedetInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyagedet/update", function(data){
        Feng.success("修改成功!");
        window.parent.Voyagedet.table.refresh();
        VoyagedetInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyagedetInfoData);
    ajax.start();
}

$(function() {

});
