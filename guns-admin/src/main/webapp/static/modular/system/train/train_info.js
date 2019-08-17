/**
 * 初始化火车详情对话框
 */
var TrainInfoDlg = {
    trainInfoData : {},
    validateFields: {
        traincode: {
            validators: {
                notEmpty: {
                    message: '火车编号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
TrainInfoDlg.clearData = function() {
    this.trainInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TrainInfoDlg.set = function(key, val) {
    this.trainInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TrainInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TrainInfoDlg.close = function() {
    parent.layer.close(window.parent.Train.layerIndex);
}

/**
 * 收集数据
 */
TrainInfoDlg.collectData = function() {
    this
    .set('traincode')
    .set('carriagenum')
    .set('carriagelen')
    .set('statecode')
    .set('id')
    .set('spare1')
    .set('spare2');
}
/**
 * 验证数据是否为空
 */
TrainInfoDlg.validate = function () {
    $('#trainInfoForm').data("bootstrapValidator").resetForm();
    $('#trainInfoForm').bootstrapValidator('validate');
    return $("#trainInfoForm").data('bootstrapValidator').isValid();
}
/**
 * 提交添加
 */
TrainInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/train/add", function(data){
        if(data.code=="2335")
        {
            Feng.error(data.message);
        }
        else {
            Feng.success("添加成功!");
        }
        window.parent.Train.table.refresh();
        TrainInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.trainInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TrainInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/train/update", function(data){
        if(data.code=="2335")
        {
            Feng.error(data.message);
        }
        else {
            Feng.success("修改成功!");
        }
        window.parent.Train.table.refresh();
        TrainInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.trainInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("trainInfoForm", TrainInfoDlg.validateFields);

});
