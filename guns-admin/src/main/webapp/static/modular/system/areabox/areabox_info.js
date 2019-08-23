/**
 * 初始化详情对话框
 */
var AreaboxInfoDlg = {
    AreaboxInfoData : {}
};

/**
 * 清除数据
 */
AreaboxInfoDlg.clearData = function() {
    this.AreaboxInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaboxInfoDlg.set = function(key, val) {
    this.AreaboxInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaboxInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AreaboxInfoDlg.close = function() {
    parent.layer.close(window.parent.Areabox.layerIndex);
}

/**
 * 收集数据
 */
AreaboxInfoDlg.collectData = function() {
    this
        .set('spare1')
        .set('spare2')
        .set('ordernumber')
        .set('trantype')
        .set('boxcode')
        .set('areaid')
        .set('addtime')
        .set('boxtype')
        .set('boxsize')
        .set('goodstype')
        .set('goodsname')
        .set('startpoint')
        .set('endpoint')
        .set('emptycode')
        .set('statecode')
        .set('id');
}



/**
 * 提交修改
 */
AreaboxInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    console.log("================>"+ this.collectData());
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/box/update", function(data){
        Feng.success("修改成功!");
        window.parent.Areabox.table.refresh();
        AreaboxInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.AreaboxInfoData);
    ajax.start();
}

$(function() {

});
