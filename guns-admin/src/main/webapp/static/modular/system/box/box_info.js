/**
 * 初始化详情对话框
 */
var BoxInfoDlg = {
    boxInfoData : {},
    validateFields: {
        boxcode: {
            validators: {
                notEmpty: {
                    message: '集装箱编码不能为空'
                }
            }
        },
        addtime: {
            validators: {
                notEmpty: {
                    message: '添加时间不能为空'
                }
            }
        },
        boxtype: {
            validators: {
                notEmpty: {
                    message: '集装箱类型不能为空'
                }
            }
        },
        boxsize: {
            validators: {
                notEmpty: {
                    message: '集装箱尺寸不能为空'
                }
            }
        }
    }
};

BoxInfoDlg.validate = function () {
    $('#boxInfoForm').data("bootstrapValidator").resetForm();
    $('#boxInfoForm').bootstrapValidator('validate');
    return $("#boxInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 清除数据
 */
BoxInfoDlg.clearData = function() {
    this.boxInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BoxInfoDlg.set = function(key, val) {
    this.boxInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BoxInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BoxInfoDlg.close = function() {
    parent.layer.close(window.parent.Box.layerIndex);
}

/**
 * 收集数据
 */
BoxInfoDlg.collectData = function() {
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
 * 提交添加
 */
BoxInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/box/add", function(data){
        Feng.success("添加成功!");
        window.parent.Box.table.refresh();
        BoxInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.boxInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BoxInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/box/update", function(data){
        Feng.success("修改成功!");
        window.parent.Box.table.refresh();
        BoxInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.boxInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("boxInfoForm", BoxInfoDlg.validateFields);
});
