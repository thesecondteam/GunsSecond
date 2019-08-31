

/**
 * 初始化详情对话框
 */
var UpdownInfoDlg = {
    updownInfoData : {}
};

/**
 * 清除数据
 */
UpdownInfoDlg.clearData = function() {
    this.updownInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UpdownInfoDlg.set = function(key, val) {
    this.updownInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UpdownInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UpdownInfoDlg.close = function() {
    parent.layer.close(window.parent.Updown.layerIndex);
}

/**
 * 收集数据
 */
UpdownInfoDlg.collectData = function() {
    this
    .set('ordernumber')
    .set('optime')
    .set('oppeople')
    .set('optype')
    .set('recphone')
    .set('areaid')
    .set('recpeople')
    .set('id');
}

/**
 * 提交添加
 */
UpdownInfoDlg.ploadSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/updown/pload", function(data){
        Feng.success("添加成功!");
        window.parent.Updown.table.refresh();
        UpdownInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    var orders=this.updownInfoData.ordernumber;
    this.updownInfoData.ordernumber=null;
    var obj= this.updownInfoData;
    ajax.set("obj",obj);
    ajax.set("orders",JSON.stringify(orders));
    ajax.start();
}



