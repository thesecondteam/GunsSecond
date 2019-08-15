/**
 * 初始化voyage详情对话框
 */
var VoyageInfoDlg = {
    voyageInfoData : {}
};

/**
 * 清除数据
 */
VoyageInfoDlg.clearData = function() {
    this.voyageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyageInfoDlg.set = function(key, val) {
    this.voyageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VoyageInfoDlg.close = function() {
    parent.layer.close(window.parent.Voyage.layerIndex);
}

/**
 * 收集数据
 */
VoyageInfoDlg.collectData = function() {
    this
    .set('id')
    .set('voyagenum')
    .set('imo')
    .set('starttime')
    .set('endtime')
    .set('consigncompany')
    .set('recivecompany')
    .set('boxnumber')
    .set('distance')
    .set('statecode')
    .set('loadcode')
    .set('unloadcode')
    .set('loadtype')
    .set('endpoint')
    .set('startpiont')
    .set('spareone')
    .set('sparetwo');
}

/**
 * 提交添加
 */
VoyageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyage/add", function(data){
        Feng.success("添加成功!");
        window.parent.Voyage.table.refresh();
        VoyageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VoyageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyage/update", function(data){
        Feng.success("修改成功!");
        window.parent.Voyage.table.refresh();
        VoyageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyageInfoData);
    ajax.start();
}

$(function() {

});
