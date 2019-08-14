/**
 * 初始化详情对话框
 */
var AreaInfoDlg = {
    areaInfoData : {}
};

/**
 * 清除数据
 */
AreaInfoDlg.clearData = function() {
    this.areaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaInfoDlg.set = function(key, val) {
    this.areaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AreaInfoDlg.close = function() {
    parent.layer.close(window.parent.Area.layerIndex);
}

/**
 * 收集数据
 */
AreaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('areacode')
    .set('areatype')
    .set('statecode');
}

/**
 * 提交添加
 */
AreaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/area/add", function(data){
        Feng.success("添加成功!");
        window.parent.Area.table.refresh();
        AreaInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.areaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AreaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/area/update", function(data){
        Feng.success("修改成功!");
        window.parent.Area.table.refresh();
        AreaInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.areaInfoData);
    ajax.start();
}

$(function() {

});
