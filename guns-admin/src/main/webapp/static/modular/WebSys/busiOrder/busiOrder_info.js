/**
 * 初始化order详情对话框
 */
var BusiOrderInfoDlg = {
    busiOrderInfoData : {}
};

/**
 * 清除数据
 */
BusiOrderInfoDlg.clearData = function() {
    this.busiOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiOrderInfoDlg.set = function(key, val) {
    this.busiOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiOrder.layerIndex);
}

/**
 * 收集数据
 */
BusiOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ordernumber')
    .set('trantype')
    .set('goodstype')
    .set('goodsname')
    .set('goodsvolume')
    .set('startpoint')
    .set('recephone')
    .set('receiver')
    .set('endpoint')
    .set('consiphone')
    .set('consignor')
    .set('creationtime')
    .set('ordercode')
    .set('note')
    .set('spare')
    .set('spare1');
}

/**
 * 提交添加
 */
BusiOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusiOrder.table.refresh();
        BusiOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusiOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiOrder.table.refresh();
        BusiOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiOrderInfoData);
    ajax.start();
}

$(function() {

});
