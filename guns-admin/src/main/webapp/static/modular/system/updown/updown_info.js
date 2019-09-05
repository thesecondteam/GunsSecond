

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
 * 拼单添加
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
    var orders=$('#ordernumber').val();
    ajax.set("orders",JSON.stringify(orders));
    ajax.set("boxcode",$('#boxcode').val());
    ajax.set("oppeople",$('#oppeople').val());
    ajax.set("optime",$('#optime').val());
    ajax.set("areaid",$('#areaid').val());
    ajax.start();

}

/**
 * 拆单添加
 */
UpdownInfoDlg.cloadSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/updown/cload", function(data){
        Feng.success("添加成功!");
        window.parent.Updown.table.refresh();
        UpdownInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    var boxcodes=$('#boxcode').val();
    ajax.set("order",$('#ordernumber').val());
    ajax.set("boxcodes",JSON.stringify(boxcodes));
    ajax.set("oppeople",$('#oppeople').val());
    ajax.set("optime",$('#optime').val());
    ajax.set("areaid",$('#areaid').val());
    ajax.start();

}

/**
 * 整箱添加
 */
UpdownInfoDlg.loadSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/updown/load", function(data){
        Feng.success("添加成功!");
        window.parent.Updown.table.refresh();
        UpdownInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set("order",$('#ordernumber').val());
    ajax.set("boxcode",$('#boxcode').val());
    ajax.set("oppeople",$('#oppeople').val());
    ajax.set("optime",$('#optime').val());
    ajax.set("areaid",$('#areaid').val());
    ajax.start();

}



